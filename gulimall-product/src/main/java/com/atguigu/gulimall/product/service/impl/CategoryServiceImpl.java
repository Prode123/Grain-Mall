package com.atguigu.gulimall.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.atguigu.gulimall.product.service.CategoryBrandRelationService;
import com.atguigu.gulimall.product.vo.Category2Vo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.CategoryDao;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;


@Service("categoryService")
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    CategoryDao categoryDao;
    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * @Description
     * @Author LiTong(Prode)
     * @Date 2024/04/20 21:52
     **/
    @Override
    public Map<String, List<Category2Vo>> getCatalogJson() {
        //给缓存中放json字符串，拿出缓存中的json字符串，有就是直接返回，没有再查询数据库

        //1、加入redis缓存，缓存中存的数据是json字符串
        //JSON跨语言，跨平台兼容
        String redisValue = stringRedisTemplate.opsForValue().get("catalogJson");
        if(StringUtils.isEmpty(redisValue)){
            //缓存中没有，去数据库查询
            Map<String, List<Category2Vo>> catalogJsonFromDb = getCatalogJsonFromDbWithRedisLock();
            String s = JSON.toJSONString(catalogJsonFromDb);
            stringRedisTemplate.opsForValue().set("catalogJson",s,7, TimeUnit.DAYS);
            return catalogJsonFromDb;
        }
        return JSON.parseObject(redisValue,new TypeReference<Map<String, List<Category2Vo>>>(){});
    }

    public Map<String, List<Category2Vo>> getCatalogJsonFromDbWithRedisLock(){

        //1、占分布式锁，去redis占坑 设置过期时间，必须和加锁是同步的，原子的
        String uuid = UUID.randomUUID().toString();
        Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent("lock", uuid, 30, TimeUnit.SECONDS);
        Map<String, List<Category2Vo>> dataFromDb = null;
        if (lock){
            log.info("获取分布式锁成功");
            System.out.println("获取分布式锁成功");
            try {
                dataFromDb = getDataFromDb();
                //将数据放入缓存，将对象转为json
                String s = JSON.toJSONString(dataFromDb);
                stringRedisTemplate.opsForValue().set("catalogJson",s,7, TimeUnit.DAYS);
            }finally {
                //释放锁
//            if (uuid.equals(stringRedisTemplate.opsForValue().get("lock"))){
//                stringRedisTemplate.delete("lock");
//            }
                //使用lua脚本
                String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
                Long execute = stringRedisTemplate.execute(new DefaultRedisScript<Long>(script, Long.class), Arrays.asList("lock"), uuid);
            }
            return dataFromDb;
        }else {
            log.info("获取分布式锁失败,等待重试");
            System.out.println("获取分布式锁失败,等待重试");
            //占位失败，重试
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return getCatalogJsonFromDbWithRedisLock();
        }
    }

    private Map<String, List<Category2Vo>> getDataFromDb() {
        //查出所有一级分类
        List<CategoryEntity> level1Categorys = getLevel1Categorys();
        //封装数据
        Map<String, List<Category2Vo>> parent_id = level1Categorys.stream().collect(Collectors.toMap(k ->
                k.getCatId().toString(), v -> {
            List<CategoryEntity> categoryEntities = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", v.getCatId()));
            List<Category2Vo> category2Vos = null;
            if (categoryEntities != null) {
                category2Vos = categoryEntities.stream().map(l2 -> {
                    Category2Vo category2Vo = new Category2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName());
                    //找当前二级分类的三级分类封装成vo
                    List<CategoryEntity> level3Catelog = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", l2.getCatId()));
                    if (level3Catelog != null) {
                        List<Category2Vo.Category3Vo> category3Vos = level3Catelog.stream().map(l3 -> {
                            Category2Vo.Category3Vo category3Vo = new Category2Vo.Category3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName());
                            return category3Vo;
                        }).collect(Collectors.toList());
                        category2Vo.setCatalog3List(category3Vos);
                    }
                    return category2Vo;
                }).collect(Collectors.toList());
            }
            return category2Vos;
        }));
        //放入缓存
        stringRedisTemplate.opsForValue().set("catalogJson",JSON.toJSONString(parent_id),7, TimeUnit.DAYS);
        return parent_id;
    }

    public Map<String, List<Category2Vo>> getCatalogJsonFromDb(){
        //查出所有一级分类
        List<CategoryEntity> level1Categorys = getLevel1Categorys();
        //封装数据
        Map<String, List<Category2Vo>> parent_id = level1Categorys.stream().collect(Collectors.toMap(k ->
                k.getCatId().toString(), v -> {
            List<CategoryEntity> categoryEntities = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", v.getCatId()));
            List<Category2Vo> category2Vos = null;
            if (categoryEntities != null) {
                category2Vos = categoryEntities.stream().map(l2 -> {
                    Category2Vo category2Vo = new Category2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName());
                    //找当前二级分类的三级分类封装成vo
                    List<CategoryEntity> level3Catelog = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", l2.getCatId()));
                    if (level3Catelog != null) {
                        List<Category2Vo.Category3Vo> category3Vos = level3Catelog.stream().map(l3 -> {
                            Category2Vo.Category3Vo category3Vo = new Category2Vo.Category3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName());
                            return category3Vo;
                        }).collect(Collectors.toList());
                        category2Vo.setCatalog3List(category3Vos);
                    }
                    return category2Vo;
                }).collect(Collectors.toList());
            }
            return category2Vos;
        }));
        return parent_id;
    }





    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        //1、查出所有分类
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);

        //2、组装成父子的树形结构
        //2.1、找出所有的一级分类
        List<CategoryEntity> level1Menus = categoryEntities.stream().filter(categoryEntity ->
             categoryEntity.getParentCid() == 0
        ).map(menu -> {
            menu.setChildren(getChildrens(menu,categoryEntities));
            return menu;
        }).sorted((menu1,menu2)->{
            return (menu1.getSort()==null?0:menu1.getSort()) - (menu2.getSort()==null?0:menu2.getSort());
        }).collect(Collectors.toList());

        return level1Menus;
    }
    //递归查找所有菜单的子菜单
    private List<CategoryEntity> getChildrens(CategoryEntity root,List<CategoryEntity> all){
        List<CategoryEntity> children = all.stream().filter(categoryEntity -> {
            return categoryEntity.getParentCid().equals(root.getCatId());
        }).map(categoryEntity -> {
            //找到子菜单
            categoryEntity.setChildren(getChildrens(categoryEntity,all));
            return categoryEntity;
        }).sorted((menu1,menu2)->{
            return (menu1.getSort()==null?0:menu1.getSort()) - (menu2.getSort()==null?0:menu2.getSort());
        }).collect(Collectors.toList());
        return children;
    }


    @Override
    public void removeMenuByIds(List<Long> list) {
        //todo 1.检查当前删除的菜单是否被其他地方引用
        baseMapper.deleteBatchIds(list);
        // 逻辑删除


    }

    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();
        List<Long> parentPath = findParentPath(catelogId, paths);
        Collections.reverse(parentPath);
        return parentPath.toArray(new Long[parentPath.size()]);
    }

    /**
     * @Description  级联更新所有关联数据
     * @Author LiTong(Prode)
     * @Date 2024/03/27 22:46
     **/
    @Transactional
    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(),category.getName());

    }

    @Override
    public List<CategoryEntity> getLevel1Categorys() {
        return baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", 0));
    }

    /**
     * @Description 递归方法寻找path
     * @Author LiTong(Prode)
     * @Date 2024/03/27 21:28
     **/
    private List<Long> findParentPath(Long catelogId,List<Long> paths){
        //1、收集当前节点id
        paths.add(catelogId);
        CategoryEntity byId = this.getById(catelogId);
        if (byId.getParentCid() != 0) {
            findParentPath(byId.getParentCid(),paths);
        }
        return paths;
    }


}
package com.atguigu.gulimall.product.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.atguigu.gulimall.product.dao.AttrAttrgroupRelationDao;
import com.atguigu.gulimall.product.dao.AttrGroupDao;
import com.atguigu.gulimall.product.dao.CategoryDao;
import com.atguigu.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gulimall.product.entity.AttrGroupEntity;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;
import com.atguigu.gulimall.product.vo.AttrRespVo;
import com.atguigu.gulimall.product.vo.AttrVo;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.AttrDao;
import com.atguigu.gulimall.product.entity.AttrEntity;
import com.atguigu.gulimall.product.service.AttrService;
import org.springframework.transaction.annotation.Transactional;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    AttrAttrgroupRelationDao relationDao;

    @Autowired
    AttrGroupDao attrGroupDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CategoryService categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveAttr(AttrVo attrvo) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attrvo, attrEntity);
        //保存基本数据
        this.save(attrEntity);
        //保存关联关系
        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
        attrAttrgroupRelationEntity.setAttrGroupId(attrvo.getAttrGroupId());
        attrAttrgroupRelationEntity.setAttrId(attrEntity.getAttrId());
        relationDao.insert(attrAttrgroupRelationEntity);

    }

    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId) {
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<>();
        if (catelogId != 0) {
            queryWrapper.eq("catelog_id", catelogId);
        }

        String key = (String) params.get("key");
        if (StringUtils.isNotEmpty(key)) {
            queryWrapper.and((wrapper) -> {
                wrapper.eq("attr_id", key).or().like("attr_name", key);
            });
        }

        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),queryWrapper
        );

        PageUtils pageUtils = new PageUtils(page);
        List<AttrEntity> records = page.getRecords();
        List<AttrRespVo> respVos = records.stream().map((attrEntity) -> {

            AttrRespVo attrRespVo = new AttrRespVo();
            BeanUtils.copyProperties(attrEntity, attrRespVo);
            //1.设置分组名称
            AttrAttrgroupRelationEntity attrId = relationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().
                    eq("attr_id", attrEntity.getAttrId()));
            if (attrId != null) {
                attrRespVo.setGroupName(attrGroupDao.selectById(attrId.getAttrGroupId()).getAttrGroupName());
            }
            //2.设置分类名称
            CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatelogId());
            if (categoryEntity != null) {
                attrRespVo.setCatelogName(categoryEntity.getName());
            }
            return attrRespVo;
        }).collect(Collectors.toList());
        pageUtils.setList(respVos);

        return pageUtils;
    }

    @Override
    public AttrRespVo getAttrInfo(Long attrId) {
        AttrEntity attrEntity = this.getById(attrId);
        AttrRespVo attrRespVo = new AttrRespVo();
        BeanUtils.copyProperties(attrEntity, attrRespVo);
        //1.设置分组信息
        AttrAttrgroupRelationEntity attrgroupRelation = relationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().
                eq("attr_id", attrId));
        if (attrgroupRelation != null) {
            attrRespVo.setAttrGroupId(attrgroupRelation.getAttrGroupId());
            AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrgroupRelation.getAttrGroupId());
            if (attrGroupEntity != null) {
                attrRespVo.setGroupName(attrGroupEntity.getAttrGroupName());
            }
        }
        //2.设置分类信息
        Long[] catelogPath = categoryService.findCatelogPath(attrEntity.getCatelogId());
        attrRespVo.setCatelogPath(catelogPath);
        CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatelogId());
        if (categoryEntity != null) {
            attrRespVo.setCatelogName(categoryEntity.getName());
        }
        return attrRespVo;

    }

    @Transactional
    @Override
    public void updateAttr(AttrVo attrvo) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attrvo, attrEntity);
        this.updateById(attrEntity);

        AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
        relationEntity.setAttrGroupId(attrvo.getAttrGroupId());
        relationEntity.setAttrId(attrvo.getAttrId());

        Integer count = relationDao.selectCount(new QueryWrapper<AttrAttrgroupRelationEntity>().
                eq("attr_id", attrvo.getAttrId()));

        if (count > 0) {
            //1.修改分组关联
            relationDao.update(relationEntity, new UpdateWrapper<AttrAttrgroupRelationEntity>().
                    eq("attr_id", attrvo.getAttrId()));
        } else {
            //新增分组关联
            relationDao.insert(relationEntity);
        }

    }

}
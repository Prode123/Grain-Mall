package com.atguigu.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.gulimall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author litong
 * @email 1002411753@qq.com
 * @date 2023-12-03 23:14:00
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listWithTree();

    void removeMenuByIds(List<Long> list);

    /**
     * @Description 找到catelogId的完整路径
     * 【父/子/孙】
     * @param catelogId
     * @Author LiTong(Prode)
     * @Date 2024/03/27 21:12
     **/
    Long[] findCatelogPath(Long catelogId);

    void updateCascade(CategoryEntity category);
}


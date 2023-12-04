package com.atguigu.gulimall.product.dao;

import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author litong
 * @email 1002411753@qq.com
 * @date 2023-12-03 23:14:00
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}

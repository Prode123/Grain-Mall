package com.atguigu.gulimall.coupon.dao;

import com.atguigu.gulimall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author litong
 * @email 1002411753@qq.com
 * @date 2023-12-05 19:44:08
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}

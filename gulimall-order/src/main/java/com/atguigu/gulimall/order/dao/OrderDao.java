package com.atguigu.gulimall.order.dao;

import com.atguigu.gulimall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author litong
 * @email 1002411753@qq.com
 * @date 2023-12-05 20:08:59
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}

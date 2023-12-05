package com.atguigu.gulimall.member.dao;

import com.atguigu.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author litong
 * @email 1002411753@qq.com
 * @date 2023-12-05 19:55:17
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}

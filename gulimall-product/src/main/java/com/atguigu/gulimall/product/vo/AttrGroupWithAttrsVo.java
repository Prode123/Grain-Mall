package com.atguigu.gulimall.product.vo;

import com.atguigu.gulimall.product.entity.AttrEntity;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author LiTong(Prode)
 * @Data 2024/04/04 22:12
 */
@Data
public class AttrGroupWithAttrsVo {

    private Long attrGroupId;

    private String attrGroupName;

    private Integer sort;

    private String descript;

    private String icon;

    private Long catelogId;

//    private Long[] attrIds;

    private List<AttrEntity> attrs;


}

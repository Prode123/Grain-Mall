package com.atguigu.gulimall.product.vo;

import lombok.Data;

/**
 * @Description
 * @Author LiTong(Prode)
 * @Data 2024/03/28 22:08
 */
@Data
public class AttrRespVo extends AttrVo{

    //所属分类名字
    private String catelogName;
    //属性分组名字
    private String groupName;


}

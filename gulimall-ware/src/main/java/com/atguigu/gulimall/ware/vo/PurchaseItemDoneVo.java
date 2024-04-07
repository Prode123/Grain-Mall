package com.atguigu.gulimall.ware.vo;

import lombok.Data;

/**
 * @Description
 * @Author LiTong(Prode)
 * @Data 2024/04/07 22:57
 */
@Data
public class PurchaseItemDoneVo {
    private Long itemId;
    private Integer status;
    private String reason;
}

package com.atguigu.gulimall.ware.vo;

import lombok.Data;

/**
 * @Description
 * @Author LiTong(Prode)
 * @Data 2024/04/14 21:16
 */
@Data
public class SkuHasStockVo {
    private Long skuId;
    private Boolean hasStock;
    private Integer stock;
}

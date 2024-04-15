package com.atguigu.common.to;

import lombok.Data;

/**
 * @Description
 * @Author LiTong(Prode)
 * @Data 2024/04/14 22:13
 */
@Data
public class SkuHasStockTo {
    private Long skuId;
    private Boolean hasStock;
    private Integer stock;
}

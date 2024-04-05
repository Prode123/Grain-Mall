package com.atguigu.gulimall.product.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description
 * @Author LiTong(Prode)
 * @Data 2024/04/05 19:24
 */
@Data
public class Skus {
    private List<Attr> attr;
    private String skuName;
    private String price;
    private String skuTitle;
    private String skuSubtitle;
    private List<Images> images;
    private List<String> descar;
    private int fullCount;
    private double discount;
    private int countStatus;
    private int fullPrice;
    private int reducePrice;
    private int priceStatus;
    private List<MemberPrice> memberPrice;

}

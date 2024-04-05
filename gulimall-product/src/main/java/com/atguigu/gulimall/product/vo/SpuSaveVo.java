package com.atguigu.gulimall.product.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author LiTong(Prode)
 * @Data 2024/04/05 19:25
 */
@Data
public class SpuSaveVo {
    private String spuName;
    private String spuDescription;
    private Long catalogId;
    private Long brandId;
    private String weight;
    private int publishStatus;
    private List<String> decript;
    private List<String> images;
    private Bounds bounds;
    private List<BaseAttrs> baseAttrs;
    private List<Skus> skus;

}

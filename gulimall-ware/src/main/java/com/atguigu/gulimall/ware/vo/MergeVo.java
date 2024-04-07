package com.atguigu.gulimall.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author LiTong(Prode)
 * @Data 2024/04/07 21:15
 */
@Data
public class MergeVo {

    private Long purchaseId;
    private List<Long> items;

}

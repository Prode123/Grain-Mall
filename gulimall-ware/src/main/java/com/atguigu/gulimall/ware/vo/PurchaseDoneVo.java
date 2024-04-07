package com.atguigu.gulimall.ware.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description
 * @Author LiTong(Prode)
 * @Data 2024/04/07 22:55
 */
@Data
public class PurchaseDoneVo {
    /**
     * 采购单id
     */
    @NotNull
    private Long id;
    private List<PurchaseItemDoneVo> items;

}

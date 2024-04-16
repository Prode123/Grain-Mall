package com.atguigu.gulimall.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description
 * @Author LiTong(Prode)
 * @Data 2024/04/16 19:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category2Vo {
    private String catalog1Id;
    private List<Category3Vo> catalog3List;
    private String id;
    private String name;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Category3Vo {
        private String catalog2Id;
        private String id;
        private String name;
    }
}

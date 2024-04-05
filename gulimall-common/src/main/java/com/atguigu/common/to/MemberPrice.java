package com.atguigu.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description
 * @Author LiTong(Prode)
 * @Data 2024/04/05 19:23
 */
@Data
public class MemberPrice {
    private Long id;
    private String name;
    private BigDecimal price;

}

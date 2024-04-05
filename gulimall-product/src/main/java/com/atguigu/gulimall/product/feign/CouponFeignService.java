package com.atguigu.gulimall.product.feign;

import com.atguigu.common.to.SkuReductionTo;
import com.atguigu.common.to.SpuBoundTo;
import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description
 * @Author lt
 * @Data 2024/04/05 22:59
 */

@FeignClient("gulimall-coupon")
public interface CouponFeignService {
    /**
     * @Description 只要json数据模型是兼容的，双方服务无需使用同一个vo
     * @Author LiTong(Prode)
     * @Date 2024/04/06 00:40
     **/
    @PostMapping("/coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundTo spuBoundTo);

    @PostMapping("/coupon/skufullreduction/saveinfo")
    R saveSkuReduction(@RequestBody SkuReductionTo skuReductionTo);
}

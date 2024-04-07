package com.atguigu.gulimall.ware.feign;

import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @Author LiTong(Prode)
 * @Data 2024/04/07 23:41
 */
@FeignClient("gulimall-product")
public interface ProductFeignService {

    /**
     * @Description
     * 1)让所有请求过网关
     *  1、@FeignClient("gulimall-gateway")
     *  2、/api/product/skuinfo/info/{skuId}
     * 2)直接让后台指定服务器
     *  1、@FeignClient("gulimall-product")
     *  2、/product/skuinfo/info/{skuId}
     * @Author LiTong(Prode)
     * @Date 2024/04/07 23:44
     **/
    @RequestMapping("/product/skuinfo/info/{skuId}")
    public R info(@PathVariable("skuId") Long skuId);
}

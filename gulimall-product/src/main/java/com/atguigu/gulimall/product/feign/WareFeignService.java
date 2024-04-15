package com.atguigu.gulimall.product.feign;

import com.atguigu.common.to.SkuHasStockTo;
import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description
 * @Author lt
 * @Data 2024/04/14 21:26
 */

@FeignClient("gulimall-ware")
public interface WareFeignService {

    @PostMapping("/ware/waresku/hasStock")
    List<SkuHasStockTo> getSkuHasStock(@RequestBody List<Long> skuIds);
}

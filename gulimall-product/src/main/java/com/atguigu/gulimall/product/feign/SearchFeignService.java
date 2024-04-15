package com.atguigu.gulimall.product.feign;

import com.atguigu.common.to.es.SkuEsModel;
import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description
 * @Author lt
 * @Data 2024/04/15 21:47
 */

@FeignClient("gulimall-search")
public interface SearchFeignService {

    @RequestMapping("/search/save/product")
    public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels);
}

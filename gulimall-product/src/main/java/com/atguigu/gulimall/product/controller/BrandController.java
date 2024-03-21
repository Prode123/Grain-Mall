package com.atguigu.gulimall.product.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.atguigu.gulimall.product.entity.BrandEntity;
import com.atguigu.gulimall.product.service.BrandService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.R;

import javax.validation.Valid;


/**
 * 品牌
 *
 * @author litong
 * @email 1002411753@qq.com
 * @date 2023-12-05 10:59:22
 */
@Slf4j
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;




    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    public R info(@PathVariable("brandId") Long brandId){
        BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
//    @PostMapping("/save")
//    public R save(@Valid @RequestBody BrandEntity brand, BindingResult result){
//
////        log.info("品牌保存信息：{}", brand);
////        log.info("品牌保存信息：{}", brand.getName());
//        if (result.hasErrors()){
//            Map<String, Object> map = new HashMap<>();
//            result.getFieldErrors().forEach(item->{
//                // 获取错误提示信息
//                String message = item.getDefaultMessage();
//                // 获取错误的字段名字
//                String field = item.getField();
//                map.put(field,message);
//            });
//           return R.error(400,"提交的数据不合法").put("data",map);
//        }
//
//        brandService.save(brand);
//
//        return R.ok();
//    }
    @PostMapping("/save")
    public R save(@Valid @RequestBody BrandEntity brand){


        brandService.save(brand);

        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BrandEntity brand){
        brandService.updateById(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] brandIds){
        brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}

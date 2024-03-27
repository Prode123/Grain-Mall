package com.atguigu.gulimall.product;

import com.atguigu.gulimall.product.entity.BrandEntity;
import com.atguigu.gulimall.product.service.BrandService;
import com.atguigu.gulimall.product.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@Slf4j
@SpringBootTest
class GulimallProductApplicationTests {


    @Autowired
    BrandService brandService;

    @Autowired
    CategoryService categoryService;


    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("xiaomi");
        brandEntity.setDescript("test");
        brandService.save(brandEntity);
        System.out.println("success");
    }

    @Test
    void testFindPath() {
        Long[] catelogPath = categoryService.findCatelogPath(225L);
//        System.out.println("完整路径："+catelogPath);
        log.info("完整路径：{}", Arrays.asList(catelogPath));
    }

}

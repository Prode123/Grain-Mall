package com.atguigu.gulimall.product;

import com.atguigu.gulimall.product.entity.BrandEntity;
import com.atguigu.gulimall.product.service.BrandService;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GulimallProductApplicationTests {


    @Autowired
    BrandService brandService;


    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("xiaomi");
        brandEntity.setDescript("test");
        brandService.save(brandEntity);
        System.out.println("success");
    }

}

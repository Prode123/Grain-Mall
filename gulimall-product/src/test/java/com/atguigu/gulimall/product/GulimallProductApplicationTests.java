package com.atguigu.gulimall.product;

import com.atguigu.gulimall.product.entity.BrandEntity;
import com.atguigu.gulimall.product.service.BrandService;
import com.atguigu.gulimall.product.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Arrays;
import java.util.UUID;

@Slf4j
@SpringBootTest
class GulimallProductApplicationTests {


    @Autowired
    BrandService brandService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("xiaomi");
        brandEntity.setDescript("test");
        brandService.save(brandEntity);
        System.out.println("success");
    }

    @Test
    void testRedis() {
        stringRedisTemplate.opsForValue().set("hello","world");
        String hello = stringRedisTemplate.opsForValue().get("hello");
        System.out.println("hello = " + hello);
    }

    @Test
    public void testRedisTemplate(){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("hello","world"+ UUID.randomUUID().toString());
        String hello = ops.get("hello");
        System.out.println("hello = " + hello);
    }

    @Test
    void testFindPath() {
        Long[] catelogPath = categoryService.findCatelogPath(225L);
//        System.out.println("完整路径："+catelogPath);
        log.info("完整路径：{}", Arrays.asList(catelogPath));
    }

}

package com.atguigu.gulimall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description  整合mybatisplus
 * @Author LiTong(Prode)
 * 1、导入依赖
 * 2、配置
 * (1)配置数据源
 *   1)导入数据库驱动
 *   2)在application.yml中配置数据源
 * (2)配置mybatis-plus
 *   1)使用@MapperScan
 *   2)告诉mybatis-plus sql映射文件的位置
 * (3)逻辑删除
 *   1)配置全局的逻辑删除规则
 *   2)配置逻辑删除的组件bean（省略）
 *   3)给bean的具体字段上加上逻辑删除注解
 **/
@EnableDiscoveryClient
@MapperScan("com.atguigu.gulimall.product.dao")
@SpringBootApplication
public class GulimallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallProductApplication.class, args);
    }

}

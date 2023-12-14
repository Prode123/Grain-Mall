package com.atguigu.gulimall.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description 如何使用nacos作为配置中心统一管理配置
 * 1)引入依赖
 *         <dependency>
 *             <groupId>com.alibaba.cloud</groupId>
 *             <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
 *         </dependency>
 * 2)创建bootstrap.properties
 *    (想让bootstrap.properties文件名生效 还需要添加依赖
 *     <dependency>
 *             <groupId>org.springframework.cloud</groupId>
 *             <artifactId>spring-cloud-starter-bootstrap</artifactId>
 *         </dependency>
 *    )
 *   在bootstrap.properties中添加如下信息
 *   spring.cloud.nacos.config.namespace=3c433216-dafe-4689-81e7-bc373a10b219
 *   spring.cloud.nacos.config.prefix=gulimall-coupon-test
 *   spring.cloud.nacos.config.file-extension=properties
 *   也可以参照一下配置：http://i.heyige.cn:90/cloud/Nacos.html#%E4%B9%9D-nacos%E9%85%8D%E7%BD%AE%E4%B8%AD%E5%BF%83
 * 3)给nacos中的配置中心添加名为spring.cloud.nacos.config.prefix的配置文件名，需要对应配置文件中的namespace和文件名信息等
 * 4)给nacos中的配置文件添加信息
 * 5)动态获取配置
 *     在需要获取配置文件的类上添加@RefreshScope注解
 *     然后写属性
 *     @Value("${coupon.user.name:john}")
 *     private String name;
 *   如果配置中心和当前应用的配置文件使用了相同的配置，优先使用配置中心的 *
 *
 * 可以对配置名添加dev、test、prod来区分开发环境
 *
 * @Author LiTong(Prode)
 * @Date 2023/12/14 19:40
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class GulimalllCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimalllCouponApplication.class, args);
    }

}

package com.atguigu.gulimall.product.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author LiTong(Prode)
 * @Data 2024/12/25 15:35
 */
@Configuration
public class MyRedisConfig {
    /**
     * @Description 所有对redis的使用，都是通过redissionClient
     * @Author LiTong(Prode)
     * @Date 2024/12/26 09:52
     **/
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() {
        Config config = new Config();
//        单节点模式
        config.useSingleServer().setAddress("redis://123.56.181.42:6379");
        config.useSingleServer().setPassword("***");
        return Redisson.create(config);
    }

//    @Bean(destroyMethod = "shutdown")
//    public RedissonClient redisson() {
//        Config config = new Config();
////        集群模式
//        config.useClusterServers().addNodeAddress("redis://192.168.100.100:6379");
//        return Redisson.create(config);
//    }

}

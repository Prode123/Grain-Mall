package com.atguigu.gulimall.search.test;

import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description
 * @Author LiTong(Prode)
 * @Data 2024/04/11 21:50
 */

//@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallSearchApplication {

    @Autowired
    private RestHighLevelClient client;
    @Test
    public void test() {
        System.out.println(client);
    }
}

package com.atguigu.gulimall.search.test;

import com.atguigu.gulimall.search.config.GulimallElasticSearchConfig;
import lombok.Data;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;
import static com.atguigu.gulimall.search.config.GulimallElasticSearchConfig.esRestClient;

/**
 * @Description
 * @Author LiTong(Prode)
 * @Data 2024/04/11 21:50
 */

//@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallSearchApplication {

    @Data
    class User {
        private String username;
        private Integer age;
        private String sex;
    }


    @Autowired
    private RestHighLevelClient client;
    @Test
    public void test() {
        System.out.println(client);
    }

    @Test
    public void indexData() throws IOException {
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1");
        User user = new User();
        user.setUsername("张三");
        user.setAge(18);
        user.setSex("男");
        indexRequest.source(user, XContentType.JSON);
//        GulimallElasticSearchConfig gulimallElasticSearchConfig = new GulimallElasticSearchConfig();
        client = esRestClient();
        IndexResponse index = client.index(indexRequest, GulimallElasticSearchConfig.COMMON_OPTIONS);
        System.out.println(index);

    }
}

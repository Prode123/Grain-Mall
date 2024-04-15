package com.atguigu.gulimall.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.*;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import static com.alibaba.nacos.api.common.Constants.TOKEN;

/**
 * @Description
 * @Author LiTong(Prode)
 * @Data 2024/04/11 21:34
 */
@Configuration
public class GulimallElasticSearchConfig {


    @Value("${es.hostname}")
    private static String hostname;
    @Value("${es.port}")
    private static int port;
    @Value("${es.scheme}")
    private static String scheme;
    public static RequestOptions COMMON_OPTIONS;

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
//        builder.addHeader("Authorization","Bearer "+ TOKEN);
//        builder.setHttpAsyncResponseConsumerFactory(
//                new HttpAsyncResponseConsumerFactory
//                        .HeapBufferedResponseConsumerFactory(30 * 1024 * 1024 * 1024));
        COMMON_OPTIONS = builder.build();
    }

    public static RestHighLevelClient esRestClient() {
       RestHighLevelClient client = new RestHighLevelClient(
               RestClient.builder(
                       new HttpHost("123.56.181.42", Integer.parseInt("9200"), "http")
               )
       );
               return client;
    }
}

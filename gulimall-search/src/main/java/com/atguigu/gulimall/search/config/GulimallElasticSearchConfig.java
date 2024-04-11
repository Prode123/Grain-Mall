package com.atguigu.gulimall.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author LiTong(Prode)
 * @Data 2024/04/11 21:34
 */
@Configuration
public class GulimallElasticSearchConfig {


    @Value("${es.hostname}")
    private String hostname;
    @Value("${es.port}")
    private int port;
    @Value("${es.scheme}")
    private String scheme;


    public RestHighLevelClient esRestClient() {
       RestHighLevelClient client = new RestHighLevelClient(
               RestClient.builder(
                       new HttpHost(hostname, port, scheme)
               )
       );
               return client;
    }
}

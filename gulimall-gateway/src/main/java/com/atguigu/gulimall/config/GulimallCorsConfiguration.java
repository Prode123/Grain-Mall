package com.atguigu.gulimall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @Description 跨域配置
 * @Author LiTong(Prode)
 * @Data 2024/3/4 21:57
 */
@Configuration
public class GulimallCorsConfiguration {
    /**
     * 创建并配置CorsWebFilter，用于处理跨域请求。
     * @return CorsWebFilter 用于跨域请求过滤的web过滤器实例。
     */
    @Bean
    public CorsWebFilter corsWebFilter() {
        // 创建基于URL的跨域配置源
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // 创建跨域配置对象
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // 配置跨域设置
        // 允许所有请求头
        corsConfiguration.addAllowedHeader("*");
        // 允许所有请求方法
        corsConfiguration.addAllowedMethod("*");
        // 允许来自任何来源的跨域请求
        corsConfiguration.addAllowedOrigin("*");
        // 允许匹配任何模式的来源跨域请求
        corsConfiguration.addAllowedOriginPattern("*");
        // 允许请求携带认证信息（如cookies）
        corsConfiguration.setAllowCredentials(true);

        // 将跨域配置注册到URL基础上的配置源，应用到所有路径
        source.registerCorsConfiguration("/**", corsConfiguration);

        // 创建并返回CorsWebFilter实例
        return new CorsWebFilter(source);
    }
}

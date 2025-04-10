package com.wang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
/**
 * @Author wangzhen
 * @Description 跨域请求配置：通过注入filter来解决跨域问题
 * @Date 2025/4/10 20:54
 * @Version 1.0
 */
@Configuration
public class CorsConfig {
    @Bean
    public CorsWebFilter corsWebFilter(){

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*");// 允许所有的请求头
        corsConfiguration.addAllowedOrigin("*");// 允许所有的域
        corsConfiguration.addAllowedMethod("*");// 允许所有的方法

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        // register(对哪些路径进行跨域, 跨域的配置是什么)
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsWebFilter(urlBasedCorsConfigurationSource) ;
    }
}

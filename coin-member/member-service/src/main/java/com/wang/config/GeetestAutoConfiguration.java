package com.wang.config;

import com.wang.geetest.GeetestLib;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/4/10 20:21
 * @Version 1.0
 */
@Configuration
@EnableConfigurationProperties(GeetestProperties.class)
public class GeetestAutoConfiguration {

    private GeetestProperties geetestProperties ;
    // 构造器注入GeetestProperties
    public GeetestAutoConfiguration(GeetestProperties geetestProperties){
        this.geetestProperties = geetestProperties ;
    }

    @Bean
    public GeetestLib geetestLib(){
        GeetestLib geetestLib = new GeetestLib(geetestProperties.getGeetestId(), geetestProperties.getGeetestKey());
        return geetestLib ;
    }
}

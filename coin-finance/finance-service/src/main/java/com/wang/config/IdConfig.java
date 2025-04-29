package com.wang.config;

import cn.hutool.core.lang.Snowflake;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author wangzhen
 * @Description id相关配置
 * @Date 2025/4/29 15:34
 * @Version 1.0
 */
@Configuration
public class IdConfig {
    @Value("${snow.app.id:1}")
    private Integer appId ;// 应用id

    @Value("${snow.data.id:1}")
    private Integer dataId ;// 机器id
    /**
     * 雪花算法
     * @return
     */
    @Bean
    public Snowflake snowflake(){
        Snowflake snowflake = new Snowflake(appId,dataId);
        return snowflake ;
    }
}

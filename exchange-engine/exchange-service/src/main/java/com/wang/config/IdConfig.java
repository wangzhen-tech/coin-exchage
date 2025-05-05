package com.wang.config;

import cn.hutool.core.lang.Snowflake;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @Author wangzhen
 * @Description 雪花算法的配置
 * @Date 2025/5/5 16:07
 * @Version 1.0
 */
@Configuration
public class IdConfig {
    @Value("${id.machine:1}")
    private int machineCode;

    @Value("${id.app:3}")
    private int appCode;

    // 雪花算法
    @Bean
    public Snowflake snowflake() {
        Snowflake snowflake = new Snowflake(appCode, machineCode);
        return snowflake;
    }

}


package com.wang.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author wangzhen
 * @Description 极验配置
 * @Date 2025/4/10 20:17
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "geetest")
public class GeetestProperties {

    /**
     * 极验的ID
     */
    private String geetestId ;

    /**
     * 极验的key
     */
    private String geetestKey ;
}
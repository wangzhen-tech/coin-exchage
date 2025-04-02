package com.wang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @Author wangzhen
 * @Description 资源配置
 * @Date 2025/4/2 10:15
 * @Version 1.0
 */
@EnableResourceServer// 将该服务变成资源服务器
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
}

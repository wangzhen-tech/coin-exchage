package com.wang.config.jetcache;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.context.annotation.Configuration;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/4/2 20:28
 * @Version 1.0
 */

@Configuration
@EnableCreateCacheAnnotation
@EnableMethodCache(basePackages = "com.wang.service.impl")// 缓存的实现
public class JetCacheConfig {
}

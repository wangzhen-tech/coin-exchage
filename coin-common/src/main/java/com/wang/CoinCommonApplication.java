package com.wang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/4/3 10:29
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient // 开启服务注册和发现
public class CoinCommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoinCommonApplication.class, args);

    }
}

package com.wang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author wangzhen
 * @Description 交易系统启动类
 * @Date 2025/5/4 16:56
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ExchangeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExchangeServiceApplication.class ,args) ;
    }
}

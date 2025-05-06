package com.wang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * @Author wangzhen
 * @Description 撮合系统启动类
 * @Date 2025/5/6 11:21
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MatchServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MatchServiceApplication.class ,args) ;
    }
}

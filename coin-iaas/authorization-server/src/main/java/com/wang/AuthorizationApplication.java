package com.wang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author wangzhen
 * @Description 授权服务应用程序启动类
 * @Date 2025/4/1 18:52
 * @Version 1.0
 */
@SpringBootApplication
//@EnableDiscoveryClient  原先没有加该注解，疑惑是如何服务发现的
public class AuthorizationApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationApplication.class, args);
    }
}

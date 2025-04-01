package com.wang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author
 * @Description Gateway网关
 * @Date 2025/2/13 17:13
 * @Version 1.0
 */

@SpringBootApplication
@EnableDiscoveryClient // 服务发现的组件
public class GatewayServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }
}



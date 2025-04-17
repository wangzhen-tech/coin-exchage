package com.wang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
/**
 * @Author wangzhen
 * @Description 财务系统启动类
 * @Date 2025/4/17 15:31
 * @Version 1.0
 */

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class FinanceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceServiceApplication.class ,args) ;
    }
}

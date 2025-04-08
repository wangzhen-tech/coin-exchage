package com.wang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/4/3 16:42
 * @Version 1.0
 */
//@MapperScan("com.wang.mapper")  // 确保扫描到 Mapper 在该模块中等效于MybatisPlusConfig 文件的mappersacn注解
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients // 启用自定义的OAuth2Client(将该对象放入IOC容器中)
public class AdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class ,args) ;
    }
}

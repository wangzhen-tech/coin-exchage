package com.wang;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author wangzhen
 * @Description 会员系统启动类
 * @Date 2025/4/10 14:49
 * @Version 1.0
 */
/**
 * 会员系统的启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MemberServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberServiceApplication.class ,args) ;
    }
}

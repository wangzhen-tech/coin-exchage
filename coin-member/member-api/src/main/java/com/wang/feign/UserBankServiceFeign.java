package com.wang.feign;

import com.wang.config.fegin.OAuth2FeignConfig;
import com.wang.dto.UserBankDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * @Author wangzhen
 * @Description
 * @Date 2025/4/29 20:21
 * @Version 1.0
 */
/**
 * 若FeignClient 里面的name 相同时,spring 创建对象就会报错:它认为它们2 个对象是一样的
 */
@FeignClient(name = "member-service",contextId = "userBankServiceFeign" ,configuration = OAuth2FeignConfig.class ,path = "/userBanks")
public interface UserBankServiceFeign {

    @GetMapping("/{userId}/info")
    UserBankDto getUserBankInfo(@PathVariable Long userId) ;
}

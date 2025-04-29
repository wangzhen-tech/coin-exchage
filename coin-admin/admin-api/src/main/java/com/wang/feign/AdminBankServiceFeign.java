package com.wang.feign;

import com.wang.config.fegin.OAuth2FeignConfig;
import com.wang.dto.AdminBankDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/4/29 14:57
 * @Version 1.0
 */
// name-远程调用的服务名称  path-实现路径
@FeignClient(name = "admin-service",path = "/adminBanks",configuration = OAuth2FeignConfig.class)
public interface AdminBankServiceFeign {
    @GetMapping("/list")
    List<AdminBankDto> getAllAdminBanks() ;
}


package com.wang.feign;

import com.wang.config.fegin.OAuth2FeignConfig;
import com.wang.dto.CoinDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/5/3 16:04
 * @Version 1.0
 */
@FeignClient(name = "finance-service",contextId = "coinServiceFeign" ,configuration = OAuth2FeignConfig.class,path = "/coins")
public interface CoinServiceFeign {

    @GetMapping("/list")
    public List<CoinDto> findCoins(@RequestParam(name = "coinIds") List<Long> coinIds) ;
}

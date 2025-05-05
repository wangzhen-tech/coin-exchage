package com.wang.feign;
import com.wang.config.fegin.OAuth2FeignConfig;
import com.wang.dto.TradeAreaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/5/4 23:27
 * @Version 1.0
 */
@FeignClient(name = "exchange-service",contextId = "tradingAreaServiceClient" ,configuration = OAuth2FeignConfig.class,path = "/tradeAreas" )
public interface TradingAreaServiceClient {

    /**
     * 查询所有的交易区域
     * @return
     */
    @GetMapping("/query/All")
    List<TradeAreaDto> tradeAreas();
}

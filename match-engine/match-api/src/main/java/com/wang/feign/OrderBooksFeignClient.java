package com.wang.feign;

import com.wang.config.fegin.OAuth2FeignConfig;
import com.wang.domain.DepthItemVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Map;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/5/5 20:24
 * @Version 1.0
 */
@FeignClient(name = "match-service", contextId = "orderBooksFeignClient", configuration = OAuth2FeignConfig.class)
public interface OrderBooksFeignClient {

    /**
     * 查询该交易对的盘口数据
     * key :sell:asks   value: List<DepthItemVo>
     * key:buy:bids    value:List<DepthItemVo>
     * @param symbol
     * @return
     */
    @GetMapping("/match/depth")
    Map<String, List<DepthItemVo>> querySymbolDepth(@RequestParam String symbol) ;
}

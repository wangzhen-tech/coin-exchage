package com.wang.match;

import com.wang.disruptor.OrderEvent;
import com.wang.disruptor.OrderEventHandler;
import com.wang.model.OrderBooks;
import com.lmax.disruptor.EventHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;
import java.util.Set;
/**
 * @Author wangzhen
 * @Description
 * @Date 2025/5/6 21:09
 * @Version 1.0
 */
@Configuration
@EnableConfigurationProperties(value = MatchEngineProperties.class)
public class MatchEngineAutoConfiguration {

    private MatchEngineProperties matchEngineProperties;


    public MatchEngineAutoConfiguration(MatchEngineProperties matchEngineProperties) {
        this.matchEngineProperties = matchEngineProperties;
    }


    @Bean("eventHandlers")// 注入时的名称
    public EventHandler<OrderEvent>[] eventHandlers() {Map<String, MatchEngineProperties.CoinScale> symbols = matchEngineProperties.getSymbols();// 获取交易对信息
        Set<Map.Entry<String, MatchEngineProperties.CoinScale>> entries = symbols.entrySet();
        EventHandler<OrderEvent>[] eventHandlers = new EventHandler[symbols.size()];
        int i = 0;
        for (Map.Entry<String, MatchEngineProperties.CoinScale> entry : entries) {// 循环交易对
            String symbol = entry.getKey();
            MatchEngineProperties.CoinScale value = entry.getValue();
            OrderBooks orderBooks = null;
            if (value != null) {
                orderBooks = new OrderBooks(symbol, value.getCoinScale(), value.getBaseCoinScale());
            } else {
                orderBooks = new OrderBooks(symbol);
            }
            eventHandlers[i++] = new OrderEventHandler(orderBooks);
        }
        return eventHandlers;

    }
}

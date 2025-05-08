package com.wang.controller;

import com.wang.disruptor.OrderEvent;
import com.wang.disruptor.OrderEventHandler;
import com.wang.domain.DepthItemVo;
import com.wang.enums.OrderDirection;
import com.wang.feign.OrderBooksFeignClient;
import com.wang.model.MergeOrder;
import com.wang.model.OrderBooks;
import com.lmax.disruptor.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/5/5 20:27
 * @Version 1.0
 */
@RestController
public class MatchController implements OrderBooksFeignClient {

    @Autowired
    private EventHandler<OrderEvent>[] eventHandlers;


    @GetMapping("/match/order")
    public TreeMap<BigDecimal, MergeOrder> getTradeData(@RequestParam(required = true) String symbol, @RequestParam(required = true) Integer orderDirection) {
        for (EventHandler<OrderEvent> eventHandler : eventHandlers) {
            OrderEventHandler orderEventHandler = (OrderEventHandler) eventHandler;
            if (orderEventHandler.getSymbol().equals(symbol)) {
                OrderBooks orderBooks = orderEventHandler.getOrderBooks();
                return orderBooks.getCurrentLimitPrices(OrderDirection.getOrderDirection(orderDirection));
            }
        }
        return null;
    }

    // 远程调用接口实现
    /**
     * 查询交易对的盘口数据
     * key :sell:asks   value: List<DepthItemVo>
     * key:buy:bids    value:List<DepthItemVo>
     *
     * @param symbol
     * @return
     */
    @Override
    public Map<String, List<DepthItemVo>> querySymbolDepth(String symbol) {
        for (EventHandler<OrderEvent> eventHandler : eventHandlers) {// 循环交易对
            OrderEventHandler orderEventHandler = (OrderEventHandler) eventHandler;
            if (orderEventHandler.getSymbol().equals(symbol)) {// 交易对名称相同时就去查
                HashMap<String, List<DepthItemVo>> deptMap = new HashMap<>();
                deptMap.put("asks", orderEventHandler.getOrderBooks().getSellTradePlate().getItems());
                deptMap.put("bids", orderEventHandler.getOrderBooks().getBuyTradePlate().getItems());
                return deptMap;
            }
        }
        return null;
    }
}

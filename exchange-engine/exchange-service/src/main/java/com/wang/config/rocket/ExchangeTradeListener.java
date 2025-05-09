package com.wang.config.rocket;

import com.wang.domain.ExchangeTrade;
import com.wang.service.EntrustOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author wangzhen
 * @Description 交易数据的监听
 * @Date 2025/5/6 22:14
 * @Version 1.0
 */
@Component
@Slf4j
public class ExchangeTradeListener {
    @Autowired
    private EntrustOrderService entrustOrderService ;

    @Transactional
    @StreamListener("exchange_trade_in")// 表示监听 "exchange_trade_in" 通道的消息。
    public void receiveExchangeTrade(List<ExchangeTrade> exchangeTrades){
        // 接受到消息之后之后的处理逻辑

        if (CollectionUtils.isEmpty(exchangeTrades)){
            return;
        }
        for (ExchangeTrade exchangeTrade : exchangeTrades) {
            if (exchangeTrade != null) {// 交易完成后,去更新我们的数据库
                entrustOrderService.doMatch(exchangeTrade);
            }
        }
    }


//    @Transactional
//    @StreamListener("cancel_order_in")
//    public void receiveCancelOrder(String orderId){
//        entrustOrderService.cancleEntrustOrderToDb(orderId);
//    }
}

package com.wang.rocket;

import com.wang.disruptor.DisruptorTemplate;
import com.wang.domain.EntrustOrder;
import com.wang.model.Order;
import com.wang.util.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * @Author wangzhen
 * @Description 消费者对消费消息的监听器
 * @Date 2025/5/6 20:54
 * @Version 1.0
 */
@Service
@Slf4j
public class MessageConsumerListener {
    @Autowired// 接收到消息后发送到disruptorTemplate中
    private DisruptorTemplate disruptorTemplate;

    @StreamListener("order_in")
    public void handleMessage(EntrustOrder entrustOrder) {// 监听消息
        Order order = null;
        if (entrustOrder.getStatus() == 2) { // 该单需要取消
            order = new Order();
            order.setOrderId(entrustOrder.getId().toString());
            order.setCancelOrder(true);
        } else {
            order = BeanUtils.entrustOrder2Order(entrustOrder);
        }
        log.info("接收到了委托单:{}", order);
        disruptorTemplate.onData(order);
    }
}

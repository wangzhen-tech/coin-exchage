package com.wang.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import com.wang.model.Order;

/**
 * @Author wangzhen
 * @Description 在boot里面使用它发送消息
 * @Date 2025/5/6 11:34
 * @Version 1.0
 */
public class DisruptorTemplate {
    // 往disruptor中发的只能是时间，但是传入的是订单，所以需要将订单转换成订单事件
    private static final EventTranslatorOneArg<OrderEvent, Order> TRANSLATOR = new EventTranslatorOneArg<OrderEvent, Order>() {

        public void translateTo(OrderEvent event, long sequence, Order input) {
            event.setSource(input);
        }
    };
    private final RingBuffer<OrderEvent> ringBuffer;

    public DisruptorTemplate(RingBuffer<OrderEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    /**
     * 我们使用DisruptorTemplate 时,就使用它的onData方法
     * @param input
     */
    public void onData(Order input) {
        ringBuffer.publishEvent(TRANSLATOR, input);
    }
}

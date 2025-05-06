package com.wang.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import net.openhft.affinity.AffinityThreadFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.ThreadFactory;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/5/6 11:36
 * @Version 1.0
 */

@Configuration
@EnableConfigurationProperties(value = DisruptorProperties.class)
public class DisruptorAutoConfiguration {

    public DisruptorProperties disruptorProperties;
    public DisruptorAutoConfiguration(DisruptorProperties disruptorProperties) {
        this.disruptorProperties = disruptorProperties;// 注入disruptor的配置信息
    }


    @Bean// 放入容器中进行管理
    public EventFactory<OrderEvent> eventEventFactory() {
        EventFactory<OrderEvent> orderEventEventFactory = new EventFactory<OrderEvent>() {
            @Override
            public OrderEvent newInstance() {
                return new OrderEvent();// 创建事件工厂
            }
        };
        return orderEventEventFactory;
    }


    @Bean
    public ThreadFactory threadFactory() {
        return new AffinityThreadFactory("Match-Handler:") ;
    }

    @Bean
    public WaitStrategy waitStrategy() {
        return new YieldingWaitStrategy();// 无锁高效的等待策略
    }

    @Bean// 创建DisruptorTemplate
    public DisruptorTemplate disruptorTemplate(RingBuffer<OrderEvent> ringBuffer) {
        return new DisruptorTemplate(ringBuffer);
    }
    /**
     * 核心： 创建一个RingBuffer
     * eventFactory:    事件工厂
     * threadFactory:   线程工厂：确定执行者(消费者)的线程该怎么创建
     * waitStrategy :   等待策略: 当我们ringBuffer 没有数据时,我们怎么等待
     */
    @Bean
    public RingBuffer<OrderEvent> ringBuffer(
            EventFactory<OrderEvent> eventFactory,
            ThreadFactory threadFactory,
            WaitStrategy waitStrategy,
            EventHandler<OrderEvent>[] eventHandlers
    ) {

        /**
         * 构建disruptor
         */
        Disruptor<OrderEvent> disruptor = null;

        ProducerType producerType = ProducerType.SINGLE;

        if (disruptorProperties.isMultiProducer()) {
            producerType = ProducerType.MULTI;
        }

        disruptor = new Disruptor<OrderEvent>(eventFactory, disruptorProperties.getRingBufferSize(), threadFactory, producerType, waitStrategy);
        disruptor.setDefaultExceptionHandler(new DisruptorHandlerException());

        // 设置消费者-存在数组中
        // ---我们的每个消费者代表我们的一个交易对,有多少个交易对,我们就有多少个eventHandlers ,事件来了后,多个eventHandlers 是并发执行的
        disruptor.handleEventsWith(eventHandlers);
        RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();
        disruptor.start();// 开始监听，一但监听到消息，eventHandlers中的各个handler就回去抢占这个消息进行消费

        // 使用优雅的停机
        final Disruptor<OrderEvent> disruptorShutdown = disruptor;
        Runtime.getRuntime().addShutdownHook(new Thread(
                () -> {
                    disruptorShutdown.shutdown();
                }, "DisruptorShutdownThread"
        ));
        return ringBuffer;
    }


}

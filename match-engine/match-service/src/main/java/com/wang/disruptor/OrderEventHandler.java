package com.wang.disruptor;

import com.lmax.disruptor.EventHandler;
//import com.wang.match.MatchServiceFactory;
//import com.wang.match.MatchStrategy;
//import com.wang.model.Order;
//import com.wang.model.OrderBooks;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


/**
 * @Author wangzhen
 * @Description  * 该对象 有多个: 和Symbol的数据对应
 *              * 针对某一个OrderEventHandler ,只会同一时间有一个线程来执行它
 * @Date 2025/5/5 20:33
 * @Version 1.0
 */

@Data
@Slf4j
public class OrderEventHandler implements EventHandler<OrderEvent> {
//    private OrderBooks orderBooks;
//
//    private String symbol ;
//
//    public OrderEventHandler(OrderBooks orderBooks) {
//        this.orderBooks = orderBooks;
//        this.symbol =  this.orderBooks.getSymbol() ;
//    }

    /**
     * 接收到了某个消息
     *
     * @param event
     * @param sequence
     * @param endOfBatch
     * @throws Exception
     */
    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws Exception {
        log.info("开始接收订单事件============>{}", event);
        // 处理逻辑
        log.info("处理完成我们的订单事件===================>{}", event);
//        // 从ringbuffer 里面接收了某个数据
//        Order order = (Order)event.getSource();
//        if(!order.getSymbol().equals(symbol)){ // 我们接收到了一个不属于我们处理的数据,我们不处理
//            return;
//        }
//
////        log.info("开始接收订单事件============>{}", event);
//
//        MatchServiceFactory.getMatchService(MatchStrategy.LIMIT_PRICE).match(orderBooks ,order);
//
//        /// 处理逻辑是啥?
////        log.info("处理完成我们的订单事件===================>{}", event);
    }
}

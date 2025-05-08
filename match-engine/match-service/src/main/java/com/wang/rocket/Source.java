package com.wang.rocket;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
/**
 * @Author wangzhen
 * @Description 撮合引擎单一职责原则：只负责撮合交易。对于在此过程中的其他业务，通过消息队列发送给其他的系统处理
 * @Date 2025/5/6 20:58
 * @Version 1.0
 */
public interface Source {
    /**
     * 盘口数据的输出
     */
    @Output("trade_plate_out")
    MessageChannel plateOut() ;

    /**
     * 完成订单数据的输出
     */
    @Output("completed_orders_out")
    MessageChannel completedOrdersOut() ;

    /**
     * 交易记录的输入
     */
    @Output("exchange_trades_out")
    MessageChannel exchangeTradesOut() ;

    /**
     * 取消单的输出
     */
    @Output("cancel_order_out")
    MessageChannel cancelOrderOut() ;
}

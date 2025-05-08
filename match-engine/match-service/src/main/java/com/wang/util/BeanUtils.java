package com.wang.util;

import com.wang.domain.EntrustOrder;
import com.wang.enums.OrderDirection;
import com.wang.model.Order;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/5/6 16:38
 * @Version 1.0
 */
public class BeanUtils {
    /**
     * 将EntrustOrder 转化为我们的Order
     * 因为订单消息发送过来时将EntrustOrder格式，我们需要的是Order
     * @param entrustOrder
     * @return
     */
    public static  Order entrustOrder2Order(EntrustOrder entrustOrder) {
        Order order = new Order();
        order.setOrderId(entrustOrder.getId().toString());

        order.setPrice(entrustOrder.getPrice());
        order.setAmount(entrustOrder.getVolume().subtract(entrustOrder.getDeal())); // 交易的数量= 总数量- 已经成交的数量

        order.setSymbol(entrustOrder.getSymbol());
        order.setOrderDirection(OrderDirection.getOrderDirection(entrustOrder.getType().intValue()));
        order.setTime(entrustOrder.getCreated().getTime());

        return order ;

    }
}

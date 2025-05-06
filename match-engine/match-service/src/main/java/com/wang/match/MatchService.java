package com.wang.match;

import com.wang.model.Order;
import com.wang.model.OrderBooks;
/**
 * @Author wangzhen
 * @Description 撮合/交易的接口定义
 * @Date 2025/5/6 21:16
 * @Version 1.0
 */
public interface MatchService {

    /**
     * 进行订单的撮合交易
     * @param order
     */
    void match(OrderBooks orderBooks, Order order) ;
}

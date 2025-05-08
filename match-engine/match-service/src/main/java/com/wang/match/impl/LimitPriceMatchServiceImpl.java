package com.wang.match.impl;

import com.wang.enums.OrderDirection;
import com.wang.match.MatchService;
import com.wang.match.MatchServiceFactory;
import com.wang.match.MatchStrategy;
import com.wang.model.*;
import com.wang.rocket.Source;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/5/6 21:18
 * @Version 1.0
 */
@Service
@Slf4j
public class LimitPriceMatchServiceImpl implements MatchService, InitializingBean {
    @Autowired
    private Source source;

    /**
     * 进行订单的撮合交易，核心中的核心
     *
     * @param orderBooks
     * @param order
     */
    @Override
    public void match(OrderBooks orderBooks, Order order) {
        if (order.isCancelOrder()) {
            orderBooks.cancelOrder(order);
            Message<String> message = MessageBuilder.withPayload(order.getOrderId()).build();
            source.cancelOrderOut().send(message);
            return; // 取消单的操作
        }

        // 1 进行数据的校验
        if (order.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }
        // 2 获取对向的挂单队列：如买的时候必须要知道卖单的情况才能买
        Iterator<Map.Entry<BigDecimal, MergeOrder>> markerQueueIterator = null;
        if (order.getOrderDirection() == OrderDirection.BUY) {// 买方向 -> 获取委托单中方向为卖出的订单
            markerQueueIterator = orderBooks.getCurrentLimitPriceIterator(OrderDirection.SELL);
        } else {// 卖方向 -> 获取委托单中方向为买的订单
            markerQueueIterator = orderBooks.getCurrentLimitPriceIterator(OrderDirection.BUY);
        }

        boolean exitLoop = false;// 循环退出标记
        List<Order> completedOrders = new ArrayList<>();// 已经完成的订单
        List<ExchangeTrade> exchangeTrades = new ArrayList<>(); // 产生的交易记录
        // 3 循环对向委托单队列
        while (markerQueueIterator.hasNext() && !exitLoop) {
            // 获取当前迭代到的信息
            Map.Entry<BigDecimal, MergeOrder> markerOrderEntry = markerQueueIterator.next();
            BigDecimal markerPrice = markerOrderEntry.getKey();
            MergeOrder markerMergeOrder = markerOrderEntry.getValue();

            // 判断当前交易能否继续进行
            if (order.getOrderDirection() == OrderDirection.BUY && order.getPrice().compareTo(markerPrice) < 0) {
                break;// 买入时：买入价格 -卖出价格 < 0 ,即买不了，退出
            }
            if (order.getOrderDirection() == OrderDirection.SELL && order.getPrice().compareTo(markerPrice) > 0) {
                break;// 卖出时：卖出价格 - 购买价格 > 0, 即不能卖，退出
            }

            // 循环该价格下按时间排序的所有订单
            Iterator<Order> markerIterator = markerMergeOrder.iterator();
            while (markerIterator.hasNext()) {
                Order marker = markerIterator.next();

                ExchangeTrade exchangeTrade = processMath(order, marker, orderBooks);
                exchangeTrades.add(exchangeTrade);
                if (order.isCompleted()) { // 经过一圈的吃单,我吃饱了
                    completedOrders.add(order);
                    exitLoop = true; // 退出最外层的循环
                    break;  // 退出当前的MergeOrder的循环
                }

                if (marker.isCompleted()) {// MergeOrder 的一个小的订单完成了
                    completedOrders.add(marker);
                    markerIterator.remove();
                }

            }

            if (markerMergeOrder.size() == 0) { // 该价格下的所有MergeOrder已经吃完了
                markerQueueIterator.remove(); // 将该MergeOrder 从树上移除掉
            }

        }

        // 4 若我们的订单没有完成 -> 会进入盘口中向用户展示
        if (order.getAmount().compareTo(order.getTradedAmount()) > 0) {
            orderBooks.addOrder(order);//只能继续加入委托单账本
        }

        if (exchangeTrades.size() > 0) {// 5 发送交易记录（只有不为空才发送消息）
            handlerExchangeTrades(exchangeTrades);

        }
        if (completedOrders.size() > 0) {
            completedOrders(completedOrders);// 6 发送已经成交的交易记录
//            sendTradePlateData(tradePlate); 存疑 盘口数据的发送
        }


    }

    /**
     * 核心：进行委托单的匹配撮合交易
     *
     * @param taker  吃单
     * @param marker 挂单
     * @return ExchangeTrade 交易记录
     */
    private ExchangeTrade processMath(Order taker, Order marker, OrderBooks orderBooks) {
        // 1 定义交易的变量
        BigDecimal dealPrice = marker.getPrice();// 成交的价格
        BigDecimal turnoverAmount = BigDecimal.ZERO;// 成交的数量
        BigDecimal needAmount = calcTradeAmount(taker); // 本次需要的数量 10  20，由calcTradeAmount计算得出
        BigDecimal providerAmount = calcTradeAmount(marker);// 本次提供的数量 20 10

        turnoverAmount = needAmount.compareTo(providerAmount) <= 0 ? needAmount : providerAmount;

        if (turnoverAmount.compareTo(BigDecimal.ZERO) == 0) {
            return null; // 无法成交
        }

        // 设置本次吃单的成交数据
        taker.setTradedAmount(taker.getTradedAmount().add(turnoverAmount));
        BigDecimal turnoverTaker = turnoverAmount.multiply(dealPrice).setScale(orderBooks.getCoinScale(), RoundingMode.HALF_UP);
        taker.setTurnover(turnoverTaker);

        // 设置本次挂单的成交数据
        marker.setTradedAmount(marker.getTradedAmount().add(turnoverAmount));
        BigDecimal markerTurnover = turnoverAmount.multiply(dealPrice).setScale(orderBooks.getBaseCoinScale(), RoundingMode.HALF_UP);
        marker.setTurnover(markerTurnover);

        ExchangeTrade exchangeTrade = new ExchangeTrade();
        exchangeTrade.setAmount(turnoverAmount); // 设置购买的数量
        exchangeTrade.setPrice(dealPrice);  // 设置购买的价格
        exchangeTrade.setTime(System.currentTimeMillis()); // 设置成交的时间
        exchangeTrade.setSymbol(orderBooks.getSymbol());  // 设置成交的交易对
        exchangeTrade.setDirection(taker.getOrderDirection());  // 设置交易的方法
        exchangeTrade.setSellOrderId(marker.getOrderId()); // 设置出售方的id
        exchangeTrade.setBuyOrderId(taker.getOrderId()); // 设置买方的id

        exchangeTrade.setBuyTurnover(taker.getTurnover()); // 设置买方的交易额
        exchangeTrade.setSellTurnover(marker.getTurnover()); // 设置卖方的交易额

        /**
         * 更新盘口数据:
         *  我们的委托单肯定是: 将挂单的数据做了一部分消耗
         */
        if (marker.getOrderDirection() == OrderDirection.BUY) {
            orderBooks.getBuyTradePlate().remove(marker, turnoverAmount);// 减少挂单的数据量
        } else {
            orderBooks.getSellTradePlate().remove(marker, turnoverAmount);
        }

        return exchangeTrade;
    }

    /**
     * 计算本次的交易额
     *
     * @param order
     * @return
     */
    private BigDecimal calcTradeAmount(Order order) {
        return order.getAmount().subtract(order.getTradedAmount());
    }

    /**
     * 发送盘口数据,供以后我们前端的数据更新
     *
     * @param tradePlate
     */
    private void sendTradePlateData(TradePlate tradePlate) {
        // 设置消息载荷
        Message<TradePlate> message = MessageBuilder
                .withPayload(tradePlate)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();
        // 发送消息
        source.plateOut().send(message);
    }

    /***
     * 订单的完成
     * @param completedOrders
     */
    private void completedOrders(List<Order> completedOrders) {

        Message<List<Order>> message = MessageBuilder
                .withPayload(completedOrders)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();
        source.completedOrdersOut().send(message);
    }

    /**
     * 处理订单的记录
     *
     * @param exchangeTrades
     */
    private void handlerExchangeTrades(List<ExchangeTrade> exchangeTrades) {

        Message<List<ExchangeTrade>> message = MessageBuilder
                .withPayload(exchangeTrades)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();
        source.exchangeTradesOut().send(message);
        log.info("本次成交的记录为:" + exchangeTrades);
        log.info("本次成交的记录为:" + exchangeTrades);

    }


    @Override
    public void afterPropertiesSet() throws Exception {

        MatchServiceFactory.addMatchService(MatchStrategy.LIMIT_PRICE, this);
    }
}

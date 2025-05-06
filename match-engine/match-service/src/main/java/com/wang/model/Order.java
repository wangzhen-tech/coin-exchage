package com.wang.model;

import com.wang.enums.OrderDirection;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author wangzhen
 * @Description 委托单，撮合引擎自己实现的委托单 避免和其他的系统之间的耦合
 * @Date 2025/5/6 11:29
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class Order implements Serializable {

    /**
     * 本次订单的Id
     */
    private String orderId;

    /**
     * 用户/会员Id
     */
    private Long userId;

    /**
     * 支持的币币交易对
     */
    private String symbol;

    /**
     * 买入或卖出量
     */
    private BigDecimal amount = BigDecimal.ZERO;

    /**
     * 成交量
     */
    private BigDecimal tradedAmount = BigDecimal.ZERO;

    /**
     * 成交额
     */
    private BigDecimal turnover = BigDecimal.ZERO;

    /**
     * 币单位
     */
    private String coinSymbol;

    /**
     * 结算单位
     */
    private String baseSymbol;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 订单的方向
     */
    private OrderDirection orderDirection;

    /**
     * 挂单的价格
     */
    private BigDecimal price = BigDecimal.ZERO;

    /**
     * 挂单时间
     */
    private Long time;

    /**
     * 交易完成时间
     */
    private Long completedTime;

    /**
     * 交易取消时间
     */
    private Long cancelTime;

    private boolean isCancelOrder ;

//    /**
//     * 已经成功的水平订单 一个订单中不一定所有的交易都能成交成功，所以需要保存成交成功的交易
//     */
//    private List<OrderDetail> details;


    /**
     * 订单是否完成的判断依据
     *
     * @return
     */
    public boolean isCompleted() {
        return amount.compareTo(tradedAmount) <= 0;
    }
}

package com.wang.model;

import lombok.Data;
import java.math.BigDecimal;

/**
 * @Author wangzhen
 * @Description  已经撮合成功的订单
 * @Date 2025/5/6 11:31
 * @Version 1.0
 */

@Data
public class OrderDetail {
    /**
     * 订单Id
     */
    private String orderId;

    /**
     * 成交价格
     */
    private BigDecimal price;

    /**
     * 成交数量
     */
    private BigDecimal amount;

    /**
     * 成交额
     */
    private BigDecimal turnover;

    /**
     * 费率
     */
    private BigDecimal fee;

    /**
     * 成功时间
     */
    private Long dealTime;
}

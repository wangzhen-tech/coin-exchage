package com.wang.dto;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/5/3 16:18
 * @Version 1.0
 */
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode
public class CreateKLineDto {
    /**
     * 交易对名称
     */
    private String symbol;

    /**
     * 交易的价格
     */
    private BigDecimal price;

    /**
     * 交易的数量
     */
    private BigDecimal volume;
}

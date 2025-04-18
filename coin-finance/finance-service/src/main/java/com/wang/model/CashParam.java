package com.wang.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
/**
 * @Author wangzhen
 * @Description
 * @Date 2025/4/17 21:10
 * @Version 1.0
 */
@Data
@ApiModel(value = "Cash购买时的表单参数")
public class CashParam {
    @ApiModelProperty(value = "币种的id")
    @NotNull
    private Long coinId ;

    @ApiModelProperty(value = "币种的数量")
    @NotNull
    private BigDecimal num ;

    @ApiModelProperty(value = "币种的金额")
    @NotNull
    private BigDecimal mum ;
}

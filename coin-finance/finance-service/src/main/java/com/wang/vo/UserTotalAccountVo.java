package com.wang.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
/**
 * @Author wangzhen
 * @Description
 * @Date 2025/5/2 20:30
 * @Version 1.0
 */
@Data
@ApiModel(value = "账户资产")
public class UserTotalAccountVo {
    @ApiModelProperty(value = "用户的总金额CNY(人民币)")
    private BigDecimal amount ;

    @ApiModelProperty(value = "用户的总金额(USDT平台币)")
    private BigDecimal amountUs ;

    @ApiModelProperty(value = "账户的金额单位(USDT平台币)")
    private String amountUsUnit ;

    @ApiModelProperty(value = "资产列表")
    private List<AccountVo> assertList ;
}

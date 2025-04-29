package com.wang.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @Author wangzhen
 * @Description
 * @Date 2025/4/29 15:01
 * @Version 1.0
 */
@Data
@ApiModel(value = "银行卡的参数")
public class AdminBankDto {
    @ApiModelProperty(value = "开户行的人名称")
    private String name ;

    @ApiModelProperty(value = "开户行的银行名称")
    private String bankName ;

    @ApiModelProperty(value = "开户行的银行卡号")
    private String bankCard ;
}

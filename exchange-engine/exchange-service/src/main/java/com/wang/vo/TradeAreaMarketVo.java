package com.wang.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

/**
 * @Author wangzhen
 * @Description 用户系统首页-交易区域->市场 不同的交易区域对应着不同的市场
 * @Date 2025/5/4 22:58
 * @Version 1.0
 */
@Data
@ApiModel(value = "交易区域和交易市场")
public class TradeAreaMarketVo {

    /**
     * 交易区域的名称
     */
    @ApiModelProperty(value = "交易区域的名称")
    private String areaName ;

    /**
     * 交易区域包含的市场
     */
    @ApiModelProperty(value = "交易区域下包含的市场")
    private List<TradeMarketVo> markets ;
}


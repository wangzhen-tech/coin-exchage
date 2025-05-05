package com.wang.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;

/**
 * @Author wangzhen
 * @Description 合并的深度
 * @Date 2025/5/4 23:00
 * @Version 1.0
 */
@Data
@ApiModel(value = "合并的深度")
public class MergeDeptVo {

    /**
     * 合并类型
     */
    @ApiModelProperty(value = "合并类型")
    private String mergeType;

    /**
     * 合并精度
     */
    @ApiModelProperty(value = "合并精度")
    private BigDecimal value;
}

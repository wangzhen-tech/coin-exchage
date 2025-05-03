package com.wang.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.math.BigDecimal;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/5/3 16:18
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class MergeDepthDto {

    /**
     * 合并类型
     */
    private String mergeType;

    /**
     * 合并精度
     */
    private BigDecimal value;
}

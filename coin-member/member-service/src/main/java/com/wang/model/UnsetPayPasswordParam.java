package com.wang.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/4/29 17:09
 * @Version 1.0
 */
@Data
@ApiModel(value = "重置交易密码")
public class UnsetPayPasswordParam {
    @ApiModelProperty(value = "新的交易密码")
    @NotBlank
    private String payPassword ;

    @ApiModelProperty(value = "手机的验证码")
    @NotBlank
    private String validateCode ;
}


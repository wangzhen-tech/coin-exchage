package com.wang.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/4/29 17:11
 * @Version 1.0
 */
@Data
@ApiModel(value = "重置密码的表单")
public class UnSetPasswordParam extends GeetestForm {
    @ApiModelProperty(value = "国家的代号")
    @NotBlank
    private  String countryCode ;

    @ApiModelProperty(value = "手机号")
    @NotBlank
    private String mobile ;

    @ApiModelProperty(value = "新的密码")
    @NotBlank
    private String password ;

    @ApiModelProperty(value = "手机的验证码")
    @NotBlank
    private String validateCode ;
}

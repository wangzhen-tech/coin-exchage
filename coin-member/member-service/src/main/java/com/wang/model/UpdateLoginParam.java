package com.wang.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/4/29 17:07
 * @Version 1.0
 */
@Data
@ApiModel(value = "修改用户登录密码的参数")
public class UpdateLoginParam {
    @ApiModelProperty(value = "原始密码")
    @NotBlank
    private String oldpassword ;

    @ApiModelProperty(value = "新的密码")
    @NotBlank
    private String newpassword ;

    @ApiModelProperty(value = "手机验证码")
    @NotBlank
    private String validateCode ;
}

package com.wang.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;
/**
 * @Author wangzhen
 * @Description 前端普通用户登录表单对象
 * @Date 2025/4/10 21:14
 * @Version 1.0
 */
@Data
@ApiModel(value = "登录的表单参数")
public class LoginForm  extends GeetestForm{
    @ApiModelProperty(value = "国家的电话编号")
    private String countryCode ;

    @ApiModelProperty(value = "用户名称")
    @NotBlank
    private String username ;

    @ApiModelProperty(value = "用户密码")
    @NotBlank
    private String password ;

    @ApiModelProperty(value = "用户的uuid")
    private String uuid ;
}

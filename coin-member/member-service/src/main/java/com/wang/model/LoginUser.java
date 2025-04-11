package com.wang.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @Author wangzhen
 * @Description 登录成功后的返回对象:返回给前端，前端在该对象中取值
 * @Date 2025/4/10 21:18
 * @Version 1.0
 */
@ApiModel(value = "登录成功的用户")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {

    @ApiModelProperty(value = "用户名称")
    private String username ;

    @ApiModelProperty(value = "token的过期时间")
    private Long expire ;

    @ApiModelProperty(value = "access_token")
    private String access_token ;

    @ApiModelProperty(value = "refreshToken")
    private String refresh_token ;
}

package com.wang.controller;

import com.wang.model.LoginResult;
import com.wang.service.SysLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Author wangzhen
 * @Description 系统用户(管理员)登录的controller
 * @Date 2025/4/7 15:56
 * @Version 1.0
 */

@RestController
@Api(tags = "登录的控制器")
public class SysLoginController {

    @Autowired
    private SysLoginService loginService;

    @PostMapping("/login")// 网关中配置了去除路径前缀，所以后端的路径中没有/admin了
    @ApiOperation(value = "后台管理人员登录")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "username", value = "用户名称"),
                    @ApiImplicitParam(name = "password", value = "用户的密码"),
            }
    )
    public LoginResult login(
            @RequestParam(required = true) String username, // 用户名称
            @RequestParam(required = true) String password  // 用户的密码
    ) {
        return loginService.login(username, password);
    }
}

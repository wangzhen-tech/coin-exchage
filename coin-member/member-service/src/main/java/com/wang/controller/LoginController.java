package com.wang.controller;

import com.wang.model.LoginForm;
import com.wang.model.LoginUser;
import com.wang.model.R;
import com.wang.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wangzhen
 * @Description 会员（普通用户）登录控制器
 * @Date 2025/4/10 21:11
 * @Version 1.0
 */

@RestController
@Api(tags = "登录的控制器")
public class LoginController {
    @Autowired
    private LoginService loginService ;

    @PostMapping("/login")
    @ApiOperation(value = "会员的登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginForm",value = "登录的表单参数")
    })
    public R<LoginUser> login(@RequestBody @Validated  LoginForm loginForm){
        LoginUser loginUser =  loginService.login(loginForm) ;
        return R.ok(loginUser) ;
    }
}

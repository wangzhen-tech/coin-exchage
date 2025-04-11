package com.wang.service;

import com.wang.model.LoginForm;
import com.wang.model.LoginUser;
/**
 * @Author wangzhen
 * @Description LoginService
 * @Date 2025/4/10 21:23
 * @Version 1.0
 */
public interface LoginService {
    /**
     * 会员的登录
     * @param loginForm 登录的表单参数
     * @return 登录的结果
     */
    LoginUser login(LoginForm loginForm);
}

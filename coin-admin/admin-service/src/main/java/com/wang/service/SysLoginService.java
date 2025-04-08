package com.wang.service;

import com.wang.model.LoginResult;
/**
 * @Author wangzhen
 * @Description 系统用户登录service
 * @Date 2025/4/7 16:05
 * @Version 1.0
 */
public interface SysLoginService {

    /**
     * 登录的实现
     * @param username 用户名
     * @param password 用户的密码
     * @return
     * 登录的结果
     */
    LoginResult login(String username ,String password) ;
}

package com.wang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/4/2 10:10
 * @Version 1.0
 */
@RestController
public class UserInfoController {
    /**
     * principal是根据token换区的当前的登录用户对象
     * @param principal
     * @return
     */
    @GetMapping("/user/info")
    public Principal userInfo(Principal principal){
        // 该接口原理是使用ThreadLocal来实现的，如下：
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return principal ;
    }
}

package com.wang.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * @Author wangzhen
 * @Description
 * @Date 2025/4/10 21:29
 * @Version 1.0
 */
@FeignClient(value = "authorization-server")// feign客户端访问的远端服务
public interface OAuth2FeignClient {
    @PostMapping("/oauth/token")
    ResponseEntity<JwtToken> getToken(
            @RequestParam("grant_type") String grantType , // 授权类型
            @RequestParam("username") String username , // 用户名
            @RequestParam("password") String  password , // 用户的密码
            @RequestParam("login_type")  String loginType,  // 登录的类型
            @RequestHeader("Authorization") String basicToken // 如：Basic Y29pbi1hcGk6Y29pbi1zZWNyZXQ= 这是由由在授权服务中spirngSecurity第三方客户端根据我们的配置信息加密出来的值
    ) ;
}

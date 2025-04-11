package com.wang.service.impl;

import com.alibaba.fastjson.JSON;
import com.wang.feign.JwtToken;
import com.wang.feign.OAuth2FeignClient;
import com.wang.geetest.GeetestLib;
import com.wang.model.LoginForm;
import com.wang.model.LoginUser;
import com.wang.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/4/10 21:24
 * @Version 1.0
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Autowired// 远程调用
    private OAuth2FeignClient oAuth2FeignClient;

    @Value("${basic.token:Basic Y29pbi1hcGk6Y29pbi1zZWNyZXQ=}")
    private String basicToken;

    @Autowired
    private StringRedisTemplate strRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private GeetestLib geetestLib;

    /**
     * 会员的登录
     *
     * @param loginForm 登录的表单参数
     * @return 登录的结果：即返回给前端的对象
     */
    @Override
    public LoginUser login(LoginForm loginForm) {
        log.info("用户{}开始登录", loginForm.getUsername());

        checkFormData(loginForm);// 校验登录表单数据
        // 登录就是使用用户名和密码换一个token--->远程调用->authorization-server
        LoginUser loginUser = null;
        ResponseEntity<JwtToken> tokenResponseEntity = oAuth2FeignClient.getToken("password", loginForm.getUsername(), loginForm.getPassword(), "member_type", basicToken);
        if (tokenResponseEntity.getStatusCode() == HttpStatus.OK) {// 如果响应状态时OK，说明换区token成功了
            JwtToken jwtToken = tokenResponseEntity.getBody();
            log.info("远程调用成功,结果为", JSON.toJSONString(jwtToken, true));
            // jwtToken必须包含bearer,所以还需要额外拼接一下
            loginUser = new LoginUser(loginForm.getUsername(), jwtToken.getExpiresIn(), jwtToken.getTokenType() + " " + jwtToken.getAccessToken(), jwtToken.getRefreshToken());
            // 使用网关解决登出的问题: 这里的token是直接存储的没有bearer
            strRedisTemplate.opsForValue().set(jwtToken.getAccessToken(), "", jwtToken.getExpiresIn(), TimeUnit.SECONDS);
        }
        return loginUser;
    }

    /**
     * 校验数据
     *   使用极验提供的验证方法进行数据校验
     *
     * @param loginForm
     */
    private void checkFormData(LoginForm loginForm) {
        loginForm.check(geetestLib,redisTemplate);
    }

}

package com.wang.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.enums.ApiErrorCode;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.wang.domain.SysMenu;
import com.wang.feign.JwtToken;
import com.wang.feign.OAuth2FeignClient;
import com.wang.model.LoginResult;
import com.wang.service.SysLoginService;
import com.wang.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
/**
 * @Author wangzhen
 * @Description 系统用户登录service实现类
 * @Date 2025/4/7 16:07
 * @Version 1.0
 */

@Service
@Slf4j
public class SysLoginServiceImpl implements SysLoginService {

    @Autowired // 授权服务
    private OAuth2FeignClient oAuth2FeignClient;

    @Autowired // 菜单服务
    private SysMenuService sysMenuService ;

    @Value("${basic.token:Basic Y29pbi1hcGk6Y29pbi1zZWNyZXQ=}")
    // Basic Y29pbi1hcGk6Y29pbi1zZWNyZXQ= 这是授权服务中，SpringSecurity第三方客户端根据我们自定义的配置加密出来的值
    private String basicToken ;

    @Autowired
    private StringRedisTemplate redisTemplate ;
    /**
     * 登录的实现
     *
     * @param username 用户名
     * @param password 用户的密码
     * @return 登录的结果
     */
    @Override
    public LoginResult login(String username, String password) {
        log.info("用户{}开始登录", username);
        // 1 获取token 需要远程调用authorization-server服务，进行登录校验与授权等
        // 对系统管理员来讲，授权类型和登录类型是定死的
        ResponseEntity<JwtToken> tokenResponseEntity = oAuth2FeignClient.getToken("password", username, password, "admin_type", basicToken);
        if(tokenResponseEntity.getStatusCode()!= HttpStatus.OK){
            throw new ApiException(ApiErrorCode.FAILED) ;
        }
        JwtToken jwtToken = tokenResponseEntity.getBody();
        log.info("远程调用授权服务器成功,获取的token为{}", JSON.toJSONString(jwtToken,true));
        String token = jwtToken.getAccessToken() ;

        // 2 查询菜单数据:主要就是usernmae
        Jwt jwt = JwtHelper.decode(token);
        String jwtJsonStr = jwt.getClaims();
        JSONObject jwtJson = JSON.parseObject(jwtJsonStr);
        Long userId = Long.valueOf(jwtJson.getString("user_name")) ;
        List<SysMenu> menus = sysMenuService.getMenusByUserId(userId);

        // 3 权限数据怎么查询 -- 不需要查询的，因为我们的jwt 里面已经包含了
        JSONArray authoritiesJsonArray = jwtJson.getJSONArray("authorities");
        List<SimpleGrantedAuthority> authorities = authoritiesJsonArray.stream() // 组装我们的权限数据
                .map(authorityJson -> new SimpleGrantedAuthority(authorityJson.toString()))
                .collect(Collectors.toList());
        // 1 将该token存储在redis里面，配置我们的网关做jwt验证的操作
        redisTemplate.opsForValue().set(token,"", jwtToken.getExpiresIn() ,TimeUnit.SECONDS);
        //2 我们返回给前端的Token数据，少一个bearer：
        return new LoginResult(jwtToken.getTokenType()+" "+token, menus, authorities);
    }
}

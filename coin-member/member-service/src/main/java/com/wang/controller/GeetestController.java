package com.wang.controller;

import com.wang.geetest.GeetestLib;
import com.wang.geetest.GeetestLibResult;
import com.wang.model.R;
//import com.wang.util.IpUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/**
 * @Author wangzhen
 * @Description Geetest极验的控制器
 * @Date 2025/4/10 20:37
 * @Version 1.0
 */
@RestController
@RequestMapping("/gt")
public class GeetestController {

    @Autowired
    private GeetestLib geetestLib;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @GetMapping("/register")
    @ApiOperation(value = "获取极验的第一次数据包---")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid" ,value = "用户验证的一个凭证")
    })
    public R<String> register(String uuid) {
//        GeetestLib gtLib = new GeetestLib(GeetestConfig.GEETEST_ID, GeetestConfig.GEETEST_KEY);
        String digestmod = "md5";
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("digestmod", digestmod);
        paramMap.put("user_id", uuid);
        paramMap.put("client_type", "web");
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        paramMap.put("ip_address", "127.0.0.1");
//        paramMap.put("ip_address", IpUtil.getIpAddr(servletRequestAttributes.getRequest()));

        // 核心：和极验的服务器进行交互
        GeetestLibResult result = geetestLib.register(digestmod, paramMap);

        // 将结果状态写到session中，此处register接口存入session，后续validate接口会取出使用
        // 注意，此demo应用的session是单机模式，格外注意分布式环境下session的应用


        // 我们将极验的验证信息存放到redis中取，在验证时到redis中去取
        redisTemplate.opsForValue().set(GeetestLib.GEETEST_SERVER_STATUS_SESSION_KEY, result.getStatus(), 180, TimeUnit.SECONDS);
//        request.getSession().setAttribute( result.getStatus());
        redisTemplate.opsForValue().set(GeetestLib.GEETEST_SERVER_USER_KEY + ":" + uuid, uuid, 180, TimeUnit.SECONDS);
//        request.getSession().setAttribute("userId", userId);
        // 注意，不要更改返回的结构和值类型
        return R.ok(result.getData());
    }
}

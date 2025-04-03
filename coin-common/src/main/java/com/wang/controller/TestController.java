package com.wang.controller;

import com.wang.model.R;
import com.wang.model.WebLog;
import com.wang.service.TestService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author wangzhen
 * @Description 测试
 * @Date 2025/4/3 10:49
 * @Version 1.0
 */
@RestController
@Api("coin-common中测试的controller")
public class TestController {

    @Autowired// redis测试需要注入
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired//测试jetCache缓存
    private TestService testService;

    @GetMapping("/common/test")
    @ApiOperation(value="测试方法",authorizations = {@Authorization("Authorization")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "s", value = "形参1", dataType = "String", paramType = "query", example = "hello"),
            @ApiImplicitParam(name = "s", value = "形参2", dataType = "String", paramType = "query", example = "hello")
    })
    public R<String> testMethod(String s, String s2){
        return R.ok("ok啦");
    }



    @GetMapping("/data/test")
    @ApiOperation(value="日期格式化测试",authorizations = {@Authorization("Authorization")})
    public R<Date> testDate(){
        return R.ok(new Date());//测试时间是否为jackson配置的yyyy-MM-dd HH:mm:ss的格式
    }

    @GetMapping("/redis/test")
    @ApiOperation(value="redis测试",authorizations = {@Authorization("Authorization")})
    public R<String> testRedis(){
        WebLog webLog = new WebLog();
        webLog.setResult("ok");
        webLog.setMethod("xxx方法");
        webLog.setUsername("11110000");
        redisTemplate.opsForValue().set("xxx方法", webLog);
        return R.ok();
    }

    @GetMapping("/jetcache/test")
    @ApiOperation(value="jetcache测试",authorizations = {@Authorization("Authorization")})
    public R<String> testJetCache(String username){
        WebLog webLog = testService.get(username);
        System.out.println(webLog);
        return R.ok("okk");
    }

}

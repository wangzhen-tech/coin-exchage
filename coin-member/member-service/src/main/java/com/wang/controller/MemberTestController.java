package com.wang.controller;

import com.wang.model.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wangzhen
 * @Description 会员系统测试
 * @Date 2025/4/10 15:12
 * @Version 1.0
 */
@RestController
@Api(tags = "会员系统启动测试接口")
public class MemberTestController {
    @GetMapping("/test")
    @ApiOperation(value="会员系统的测试", authorizations = {@Authorization("Authorization")})
    public R<String> test(){
        return R.ok("会员系统搭建成功");
    }
}

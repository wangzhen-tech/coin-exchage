package com.wang.controller;

import com.wang.domain.Sms;
import com.wang.model.R;
import com.wang.service.SmsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
 * @Author wangzhen
 * @Description
 * @Date 2025/5/3 17:03
 * @Version 1.0
 */
@RestController
@RequestMapping("/sms")
public class SmsController {
    @Autowired
    private SmsService smsService ;

    @PostMapping("/sendTo")
    @ApiOperation(value = "发送短信")
    @ApiImplicitParams({
            @ApiImplicitParam(name="sms",value = "smsjson数据")
    })
    public R sendSms(@RequestBody @Validated Sms sms){
        boolean isOk = smsService.sendSms(sms) ;
        if(isOk){
            return R.ok() ;
        }
        return R.fail("发送失败") ;
    }
}

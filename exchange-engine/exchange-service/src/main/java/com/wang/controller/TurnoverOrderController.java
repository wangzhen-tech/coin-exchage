package com.wang.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.domain.TurnoverOrder;
import com.wang.model.R;
import com.wang.service.TurnoverOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
/**
 * @Author wangzhen
 * @Description 用户前台-资产管理-成交记录
 * @Date 2025/5/5 19:48
 * @Version 1.0
 */
@RestController
@RequestMapping("/turnoverOrders")
@Api(tags = "成交记录")
public class TurnoverOrderController {

    @Autowired
    private TurnoverOrderService turnoverOrderService ;

    @GetMapping
    @ApiOperation(value = "成交记录的查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current",value = "当前页") ,
            @ApiImplicitParam(name = "size",value = "显示的条数") ,
    })
    public R<Page<TurnoverOrder>> findByPage(@ApiIgnore Page<TurnoverOrder> page ,String symbol ,Integer type){
        Long userId = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())  ;
        Page<TurnoverOrder> pageData = turnoverOrderService.findByPage(page ,userId ,symbol ,type) ;
        return R.ok(pageData) ;
    }
}

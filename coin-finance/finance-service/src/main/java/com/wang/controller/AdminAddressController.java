package com.wang.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.domain.AdminAddress;
import com.wang.model.R;
import com.wang.service.AdminAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
/**
 * @Author wangzhen
 * @Description 钱包归集Controller 后台-币币交易参数-币种配置详情-钱包归集
 * @Date 2025/4/17 17:27
 * @Version 1.0
 */
@RestController
@RequestMapping("/adminAddress")
@Api(tags = "归集地址的控制器")
public class AdminAddressController {
    @Autowired
    private AdminAddressService adminAddressService;

    @GetMapping
    @ApiOperation(value = "查询归集地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "coinId", value = "币种的id"),
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页显示的条数"),
    })
    public R<Page<AdminAddress>> findByPage(@ApiIgnore Page<AdminAddress> page, Long coinId) {
        Page<AdminAddress> adminAddressPage = adminAddressService.findByPage(page, coinId);
        return R.ok(adminAddressPage);
    }


    @PostMapping
    @ApiOperation(value = "归集地址的新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminAddress" ,value = "adminAddress json")
    })
    public R save(@RequestBody @Validated AdminAddress adminAddress) {
        boolean save = adminAddressService.save(adminAddress);
        if (save) {
            return R.ok();
        }
        return R.fail("新增失败");
    }


    @PatchMapping
    @ApiOperation(value = "归集地址的修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminAddress" ,value = "adminAddress json")
    })
    public R update(@RequestBody @Validated AdminAddress adminAddress) {
        boolean update = adminAddressService.updateById(adminAddress);
        if (update) {
            return R.ok();
        }
        return R.fail("修改失败");
    }
}

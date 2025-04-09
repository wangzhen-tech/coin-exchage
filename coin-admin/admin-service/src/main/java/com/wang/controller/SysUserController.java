package com.wang.controller;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.domain.SysUser;
import com.wang.domain.SysUserRole;
import com.wang.model.R;
import com.wang.service.SysUserRoleService;
import com.wang.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author wangzhen
 * @Description 系统 -> 权限-> 员工管理功能实现
 * @Date 2025/4/8 20:55
 * @Version 1.0
 */

@Api(tags = "员工管理")
@RequestMapping("/users")
@RestController
public class SysUserController {
    @Autowired
    private SysUserService sysUserService ;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current" ,value = "当前页") ,
            @ApiImplicitParam(name = "size" ,value = "每页显示的条数") ,
            @ApiImplicitParam(name = "mobile" ,value = "员工的手机号码") ,
            @ApiImplicitParam(name = "fullname" ,value = "员工的全名称") ,
    })
    @PreAuthorize("hasAuthority('sys_user_query')")// 权限校验：只有拥有 sys_user_query 权限的用户，才能访问该接口
    // 分页查询 current和Size被MP框架自动整合到Page对象中， mobile和fullname为手机号和员工姓名（非必须参数）
    public R<Page<SysUser>> findByPage(@ApiIgnore  Page<SysUser> page ,String mobile ,String fullname){
        page.addOrder(OrderItem.desc("last_update_time")) ;
        Page<SysUser> pageData =  sysUserService.findByPage(page,mobile ,fullname) ;
        return R.ok(pageData) ;
    }


    @PostMapping
    @ApiOperation(value = "新增员工")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysUser"  ,value = "sysUser 的json数据")
    })
    @PreAuthorize("hasAuthority('sys_user_create')")
    public R addUser(@RequestBody  SysUser sysUser){
        Long userId = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        sysUser.setCreateBy(userId);
        boolean isOk = sysUserService.addUser(sysUser) ;
        if(isOk){
            return R.ok() ;
        }
        return R.fail("新增失败") ;
    }

    @PatchMapping// 编辑操作
    @ApiOperation(value = "编辑用户")
    public R updateUser(@RequestBody  SysUser sysUser){
        boolean success1 = sysUserService.updateById(sysUser);
        sysUserService.updateUserRole(sysUser);
        if (success1){
            return R.ok("编辑成功");
        }
        return R.fail("编辑失败");
    }



    @PostMapping("/delete")
    @ApiOperation(value = "删除用户")
    @PreAuthorize("hasAuthority('sys_user_delete')")
    public R deleteUser( @RequestBody  Long ids[] ){
        boolean b = sysUserService.removeByIds(Arrays.asList(ids));
        if(b){
            return R.ok() ;
        }
        return R.fail() ;
    }


}

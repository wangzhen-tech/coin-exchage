package com.wang.controller;

import com.wang.domain.SysMenu;
import com.wang.model.R;
import com.wang.model.RolePrivilegesParam;
import com.wang.service.SysRolePrivilegeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @Author wangzhen
 * @Description
 * @Date 2025/4/8 17:17
 * @Version 1.0
 */
@Api(tags = "角色权限的配置")
@RestController
public class SysRolePrivilegeController {
    @Autowired
    private SysRolePrivilegeService sysRolePrivilegeService ;

    @GetMapping("/roles_privileges")
    @ApiOperation(value = "查询角色的权限列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId" ,value = "角色的ID")
    })
    // 也就是查询角色的配置列表：二级菜单：权限1 权限2 ...，并标记该完整的权限列表中该角色拥有哪些
    public R<List<SysMenu>> findSysMenuAndPrivileges(Long roleId){
        List<SysMenu> sysMenus  = sysRolePrivilegeService.findSysMenuAndPrivileges(roleId) ;
        return R.ok(sysMenus) ;
    }


    @PostMapping("/grant_privileges")
    @ApiOperation(value = "授予角色某种权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rolePrivilegesParam" ,value = "rolePrivilegesParam json数据")
    })
    public R grantPrivileges(@RequestBody  RolePrivilegesParam rolePrivilegesParam){
        boolean isOk = sysRolePrivilegeService.grantPrivileges(rolePrivilegesParam)  ;
        if(isOk){
            return R.ok() ;
        }
        return R.fail("授权失败") ;
    }
}

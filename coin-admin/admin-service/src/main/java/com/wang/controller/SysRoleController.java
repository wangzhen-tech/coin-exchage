package com.wang.controller;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.domain.SysRole;
import com.wang.model.R;
import com.wang.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
/**
 * @Author wangzhen
 * @Description 角色管理
 * @Date 2025/4/8 15:36
 * @Version 1.0
 */

@RestController
@RequestMapping("/roles")
@Api(tags = "角色管理")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping
    @ApiOperation(value = "条件分页查询")
    @PreAuthorize("hasAuthority('sys_role_query')") // 权限判断
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页显示的大小"),
            @ApiImplicitParam(name = "name", value = "角色名称"),
    })                                         // mybatisPlis的Page对象，SpringMVC自动注入current和Size参数（前提是前段的查询参数名和mp的page对象的属性名要对的上）
    public R<Page<SysRole>> findByPage(@ApiIgnore Page<SysRole> page, String name) {
        page.addOrder(OrderItem.desc("last_update_time"));// 分页数据按照lastxx做降序排序
        return R.ok(sysRoleService.findByPage(page, name));
    }


    @PostMapping
    @ApiOperation(value = "新增一个角色")
    @PreAuthorize("hasAuthority('sys_role_create')")
    public R add(@RequestBody @Validated SysRole sysRole) { // RequestBody 将请求体中的json字符串反序列化为java对象并绑定到参数上
        boolean save = sysRoleService.save(sysRole);
        if (save) {
            return R.ok();
        }
        return R.fail("新增失败");

    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除一个角色数据")
    @PreAuthorize("hasAuthority('sys_role_delete')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "要删除角色的id的集合")
    })
    public R delete(@RequestBody String[] ids) {
        if (ids == null || ids.length == 0) {
            return R.fail("要删除的数据不能为null");
        }
        boolean b = sysRoleService.removeByIds(Arrays.asList(ids));
        if (b) {
            return R.ok();
        }
        return R.fail("删除失败");
    }

}

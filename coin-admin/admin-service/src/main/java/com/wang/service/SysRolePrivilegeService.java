package com.wang.service;

import com.wang.domain.SysRolePrivilege;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.domain.SysMenu;
import com.wang.domain.SysRolePrivilege;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.model.RolePrivilegesParam;

import java.util.List;
    /**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/3 16:11
 * @Version 1.0
 */
public interface SysRolePrivilegeService extends IService<SysRolePrivilege>{

    /**
     * 查询角色的权限
     * @param roleId
     * @return
     */
    List<SysMenu> findSysMenuAndPrivileges(Long roleId);


    /**
     * 给角色授权权限
     * @param rolePrivilegesParam
     * @return
     */
    boolean grantPrivileges(RolePrivilegesParam rolePrivilegesParam);
}

package com.wang.service;

import com.wang.domain.SysPrivilege;
import com.baomidou.mybatisplus.extension.service.IService;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.domain.SysPrivilege;
import java.util.List;

/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/3 16:11
 * @Version 1.0
 */
public interface SysPrivilegeService extends IService<SysPrivilege>{
    /**
     *  获取该角色的该菜单下面所有的权限
     * @param roleId   roleId 代表当前的查询的角色的ID
     * @param menuId  菜单的ID
     * @return
     */
    List<SysPrivilege> getAllSysPrivilege(Long menuId ,Long roleId);

}

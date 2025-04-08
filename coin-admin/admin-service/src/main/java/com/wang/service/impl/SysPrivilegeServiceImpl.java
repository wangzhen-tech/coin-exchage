package com.wang.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.mapper.SysPrivilegeMapper;
import com.wang.domain.SysPrivilege;
import com.wang.service.SysPrivilegeService;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;
/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/3 16:11
 * @Version 1.0
 */

@Service
public class SysPrivilegeServiceImpl extends ServiceImpl<SysPrivilegeMapper, SysPrivilege> implements SysPrivilegeService {


    @Autowired
    private SysPrivilegeMapper sysPrivilegeMapper ;
    /**
     * 获取该角色该菜单下面所有的权限
     *
     * @param menuId 菜单的ID
     * @param roleId roleId 代表当前的查询的角色的ID
     * @return
     */
    @Override
    public List<SysPrivilege> getAllSysPrivilege(Long menuId, Long roleId) {
        // 1 查询所有的该菜单下的权限
        List<SysPrivilege> sysPrivileges = list(new LambdaQueryWrapper<SysPrivilege>().eq(SysPrivilege::getMenuId, menuId));
        if(CollectionUtils.isEmpty(sysPrivileges)){
            return Collections.emptyList() ;
        }
        // 2 判断当前角色是否包含该权限信息
        for (SysPrivilege sysPrivilege : sysPrivileges) {
            Set<Long>  currentRoleSysPrivilegeIds = sysPrivilegeMapper.getPrivilegesByRoleId(roleId)  ;
            if (currentRoleSysPrivilegeIds.contains(sysPrivilege.getId())){
                sysPrivilege.setOwn(1); // 当前的角色有该权限
            }

        }
        return sysPrivileges;
    }
}

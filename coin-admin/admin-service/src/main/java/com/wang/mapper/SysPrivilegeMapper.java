package com.wang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.domain.SysPrivilege;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.domain.SysPrivilege;

import java.util.List;
import java.util.Set;
/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/3 16:15
 * @Version 1.0
 */

public interface SysPrivilegeMapper extends BaseMapper<SysPrivilege> {
    /**
     * 使用角色Id查询权限
     * @param roleId
     * @return
     */
    List<SysPrivilege> selectByRoleId(Long roleId);

    /**
     * 使用角色的ID查询权限的id
     * @param roleId
     * @return
     */
    Set<Long> getPrivilegesByRoleId(Long roleId);
}
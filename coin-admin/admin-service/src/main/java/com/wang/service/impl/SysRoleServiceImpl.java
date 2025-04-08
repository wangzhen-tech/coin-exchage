package com.wang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.domain.SysRole;
import com.wang.mapper.SysRoleMapper;
import com.wang.service.SysRoleService;
/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/3 16:11
 * @Version 1.0
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService{
    @Autowired
    private  SysRoleMapper sysRoleMapper;
    @Override// 判断用户是否为超级管理员
    public boolean isSuperAdmin(Long userId) {
        // 当用户的角色码(ROLE_CODE)为role_admin时 为管理员
        // 通过用户id查询用户角色
        String roleCode = sysRoleMapper.getUserRoleCode(userId);
        if(!StringUtils.isEmpty(roleCode) && roleCode.equals("ROLE_ADMIN")){
            return true;
        }
        return false;
    }

    @Override
    public Page<SysRole> findByPage(Page<SysRole> page, String name) {

        return page(page, new LambdaQueryWrapper<SysRole>().like(
                !StringUtils.isEmpty(name), // 判断是否使用条件构造器，即名称不为空才启用
                SysRole::getName, // 哪些列
                name
        ));
    }
}

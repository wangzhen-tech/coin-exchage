package com.wang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wang.domain.SysRolePrivilege;
import com.wang.mapper.SysUserRoleMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.domain.SysUser;
import com.wang.service.SysUserService;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.domain.SysUser;
import com.wang.domain.SysUserRole;
import com.wang.mapper.SysUserMapper;
import com.wang.service.SysUserRoleService;
import com.wang.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/3 16:11
 * @Version 1.0
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService{
    @Autowired
    private SysUserRoleService sysUserRoleService ;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 分页查询员工
     *
     * @param page     分页参数
     * @param mobile   员工的手机号
     * @param fullname 员工的全名称
     * @return
     */
    @Override
    public Page<SysUser> findByPage(Page<SysUser> page, String mobile, String fullname) {
        Page<SysUser> pageData = page(page,
                new LambdaQueryWrapper<SysUser>()
                        // 使用like实现模糊查询 like(是否查询，查询哪个字段，查询字段的值)
                        .like(!StringUtils.isEmpty(mobile), SysUser::getMobile, mobile)
                        .like(!StringUtils.isEmpty(fullname), SysUser::getFullname, fullname)
        );
        // 查询出角色内容
        List<SysUser> records = pageData.getRecords();
        if(!CollectionUtils.isEmpty(records)){
            for (SysUser record : records) {
                List<SysUserRole> userRoles = sysUserRoleService.list(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, record.getId()));
                if(!CollectionUtils.isEmpty(userRoles)){
                    record.setRole_strings(
                            userRoles.stream().
                                    map(sysUserRole -> sysUserRole.getRoleId().toString())
                                    .collect(Collectors.joining(",")));
                }
            }
        }
        return pageData;
    }


    /**
     * 新增员工
     *
     * @param sysUser
     * @return
     */
    @Override
    @Transactional
    public boolean addUser(SysUser sysUser) {
        // 1 用户的密码
        String password = sysUser.getPassword();
        // 用户的角色Ids
        String role_strings = sysUser.getRole_strings();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(password); // 加密密码
        sysUser.setPassword(encode); // 设置密码
        boolean save = super.save(sysUser);
        if(save){
            // 给用户新增角色数据
            if(!StringUtils.isEmpty(role_strings)){
                String[] roleIds = role_strings.split(",");
                List<SysUserRole> sysUserRoleList = new ArrayList<>(roleIds.length) ;
                for (String roleId : roleIds) {
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setRoleId(Long.valueOf(roleId));
                    sysUserRole.setUserId(sysUser.getId());
                    sysUserRoleList.add(sysUserRole) ;
                }
                sysUserRoleService.saveBatch(sysUserRoleList) ;
            }
        }
        return save;
    }

    @Transactional
    @Override// 更新员工角色
    public void updateUserRole(SysUser sysUser) {
        // 1.判断是否有角色 有的话先删除该员工的角色
        Long userId = sysUser.getId();
        LambdaQueryWrapper<SysUserRole>  wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId, userId);
        List<SysUserRole> userRolesList = sysUserRoleMapper.selectList(wrapper);
        List<Long> ids = new ArrayList<>();
        for (SysUserRole sysUserRole : userRolesList) {
            ids.add(sysUserRole.getId());//得到角色所对应的主键id
        }
        sysUserRoleMapper.deleteBatchIds(ids);
        // 2.保存该员工当前的角色
        String roleStrings = sysUser.getRole_strings();
        String[] roleIds = roleStrings.split(",");
        List<Long> idList = Arrays.stream(roleIds)
                                .distinct()
                                .map(Long::valueOf)
                                .collect(Collectors.toList());
        List<SysUserRole> sysUserRoleList = new ArrayList<>(roleIds.length) ;
        for (Long roleId : idList) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(sysUser.getId());
            sysUserRole.setRoleId(roleId);
            sysUserRoleList.add(sysUserRole) ;
        }
        sysUserRoleService.saveBatch(sysUserRoleList) ;
    }


    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        boolean b = super.removeByIds(idList);// 调用元方法删除员工信息
        // 还需要删除员工的角色
        sysUserRoleService.remove(new LambdaQueryWrapper<SysUserRole>().in(SysUserRole::getUserId,idList)) ;
        return b;
    }

}

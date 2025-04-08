package com.wang.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.domain.SysRolePrivilege;
import com.wang.mapper.SysRolePrivilegeMapper;
import com.wang.service.SysRolePrivilegeService;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wang.domain.SysMenu;
import com.wang.domain.SysPrivilege;
import com.wang.domain.SysRolePrivilege;
import com.wang.mapper.SysRolePrivilegeMapper;
import com.wang.model.RolePrivilegesParam;
import com.wang.service.SysMenuService;
import com.wang.service.SysPrivilegeService;
import com.wang.service.SysRolePrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/3 16:11
 * @Version 1.0
 */

@Service
public class SysRolePrivilegeServiceImpl extends ServiceImpl<SysRolePrivilegeMapper, SysRolePrivilege> implements SysRolePrivilegeService {

    @Autowired// 菜单相关
    private SysMenuService sysMenuService;

    @Autowired
    private SysPrivilegeService sysPrivilegeService;

    @Autowired
    private SysRolePrivilegeService sysRolePrivilegeService;

    /**
     * 查询角色的权限：即能使用的二级菜单
     *
     * @param roleId
     * @return
     */
    @Override
    public List<SysMenu> findSysMenuAndPrivileges(Long roleId) {
        List<SysMenu> list = sysMenuService.list(); // 查询出的所有的菜单
        // 我们在页面显示的是二级菜单,以及二级菜单包含的权限
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        // 查询一级菜单
        List<SysMenu> rootMenus = list.stream()
                .filter(sysMenu -> sysMenu.getParentId() == null)// parentID==null表示这是一级菜单，留在流中收集，其余的过滤掉
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(rootMenus)) {
            return Collections.emptyList();
        }
        // 查询所有的二级菜单
        List<SysMenu> subMenus = new ArrayList<>();
        for (SysMenu rootMenu : rootMenus) {// 遍历一级菜单，并查询对应的二级菜单，将其add到list
            subMenus.addAll(getChildMenus(rootMenu.getId(), roleId, list));
        }
        return subMenus;
    }

    /**
     * 给定parentID，查询该父ID对应的子菜单 (递归)
     *
     * @param parentId 父菜单的ID
     * @param roleId   当前查询的角色的ID
     * @param sources  源数据，包括所有的菜单数据
     * @return
     */
    private List<SysMenu> getChildMenus(Long parentId, Long roleId, List<SysMenu> sources) {
        // 将parentId对应的子菜单都存放到childs中
        List<SysMenu> childs = new ArrayList<>();
        for (SysMenu source : sources) {
            if (source.getParentId() == parentId) { // 找儿子
                childs.add(source);
                source.setChilds(getChildMenus(source.getId(), roleId, sources)); // 给该儿子设置儿子
                List<SysPrivilege> sysPrivileges = sysPrivilegeService.getAllSysPrivilege(source.getId(), roleId);
                source.setPrivileges(sysPrivileges); // 该儿子可能包含权限
            }
        }
        return childs;
    }


    /**
     * 给角色授权
     *
     * @param rolePrivilegesParam 参数
     * @return
     */
    @Transactional// 注意：新增和删除在同一个事务中
    @Override
    public boolean grantPrivileges(RolePrivilegesParam rolePrivilegesParam) {
        Long roleId = rolePrivilegesParam.getRoleId(); // 角色Id
        //1 先删除之前该角色的权限 TODO 删除权限，新增权限。这里需要继续品味，之前的想法不对
        sysRolePrivilegeService.remove(new LambdaQueryWrapper<SysRolePrivilege>().eq(SysRolePrivilege::getRoleId, roleId));
        // 移除之前的值成功
        List<Long> privilegeIds = rolePrivilegesParam.getPrivilegeIds();
        if (!CollectionUtils.isEmpty(privilegeIds)) {
            List<SysRolePrivilege> sysRolePrivileges = new ArrayList<>();
            for (Long privilegeId : privilegeIds) {
                SysRolePrivilege sysRolePrivilege = new SysRolePrivilege();
                sysRolePrivilege.setRoleId(rolePrivilegesParam.getRoleId());
                sysRolePrivilege.setPrivilegeId(privilegeId);
                sysRolePrivileges.add(sysRolePrivilege);
            }
            // 2 新增新的值
            boolean b = sysRolePrivilegeService.saveBatch(sysRolePrivileges);
            return b;
        }
        // 2 新增该角色的权限
        return true ;
    }
}

package com.wang.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.domain.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/3 16:11
 * @Version 1.0
 */
public interface SysRoleService extends IService<SysRole>{
    /**
     * 判断用户是否为超级用户
     * @param userId 用户id
     * @return
     */
    boolean isSuperAdmin(Long userId);

        /**
         * 根据角色名模糊分页查询
         * @param page 分页数据
         * @param name 角色名称
         * @return
         */
    Page<SysRole> findByPage(Page<SysRole> page, String name);
}

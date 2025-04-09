package com.wang.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;


    /**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/3 16:11
 * @Version 1.0
 */
public interface SysUserService extends IService<SysUser>{
    /**
     * 分页查询员工
     * @param page      分页参数
     * @param mobile    员工手机
     * @param fullname  员工姓名
     * @return
     */
    Page<SysUser> findByPage(Page<SysUser> page, String mobile, String fullname);

    boolean addUser(SysUser sysUser);


    void updateUserRole(SysUser sysUser);
}

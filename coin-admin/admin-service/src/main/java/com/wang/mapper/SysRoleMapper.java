package com.wang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.domain.SysRole;

/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/3 16:15
 * @Version 1.0
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 获取用户角色的code
     * @param userId
     * @return
     */
    String getUserRoleCode(Long userId);
}
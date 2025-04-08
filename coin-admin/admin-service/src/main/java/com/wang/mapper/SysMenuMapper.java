package com.wang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.domain.SysMenu;

import java.util.List;

/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/3 16:15
 * @Version 1.0
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 通过用户id查询用户菜单
     * @param userId
     * @return
     */
    List<SysMenu> selectMenusByUserId(Long userId);
}
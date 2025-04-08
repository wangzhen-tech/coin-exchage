package com.wang.service;

import com.wang.domain.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/3 16:11
 * @Version 1.0
 */
public interface SysMenuService extends IService<SysMenu>{


        List<SysMenu> getMenusByUserId(Long userId);
    }

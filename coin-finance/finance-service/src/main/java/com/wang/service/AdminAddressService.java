package com.wang.service;

import com.wang.domain.AdminAddress;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/17 17:25
 * @Version 1.0
 */
public interface AdminAddressService extends IService<AdminAddress>{
    /**
     * 条件分页查询归集地址
     * @param page 分页参数
     * @param coinId 币种的Id
     * @return
     */
    Page<AdminAddress> findByPage(Page<AdminAddress> page, Long coinId);
}

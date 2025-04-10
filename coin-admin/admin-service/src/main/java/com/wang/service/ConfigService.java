package com.wang.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.domain.Config;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/3 16:11
 * @Version 1.0
 */
public interface ConfigService extends IService<Config>{
    /**
     * 分页查询
     * @param page 分页对象
     * @param type 规则类型 如：SYSTEM、CNY等
     * @param name 规则名称 如：最小取现额（GCN）、最小取现手续费等
     * @param code 对则代码 如：USDT2CNY、SWITCH等
     * @return
     */
    Page<Config> findByPage(Page<Config> page, String type, String name, String code);
}

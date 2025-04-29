package com.wang.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.domain.Config;
/**
 * @Author wangzhen
 * @Description
 * @Date 2025/4/29 11:08
 * @Version 1.0
 */
public interface ConfigService  extends IService<Config> {

    /**
     * 通过的规则的Code 查询签名
     * @param code   签名的code
     * @return  config value
     */
    Config getConfigByCode(String code);
}

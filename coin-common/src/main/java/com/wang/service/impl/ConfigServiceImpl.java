package com.wang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wang.service.ConfigService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.domain.Config;
import com.wang.mapper.ConfigMapper;
import com.wang.service.ConfigService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/4/29 11:09
 * @Version 1.0
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService{
   /**
     * 通过的规则的Code 查询签名
     *
     * @param code 签名的code
     * @return config value
     */
    @Override
    public Config getConfigByCode(String code) {
        return getOne(new LambdaQueryWrapper<Config>().eq(Config::getCode,code));
    }
}

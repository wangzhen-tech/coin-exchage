package com.wang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.domain.Config;
import com.wang.mapper.ConfigMapper;
import com.wang.service.ConfigService;
import org.springframework.util.StringUtils;

/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/3 16:11
 * @Version 1.0
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService{

    @Autowired
    private ConfigMapper configMapper;

    @Override
    public Page<Config> findByPage(Page<Config> page, String type, String name, String code) {
        Page<Config> configPage = configMapper.selectPage(page, new LambdaQueryWrapper<Config>()
                .like(!StringUtils.isEmpty(type), Config::getType, type)
                .like(!StringUtils.isEmpty(name), Config::getName, name)
                .like(!StringUtils.isEmpty(code), Config::getCode, code)
        );
        return configPage;
    }
}

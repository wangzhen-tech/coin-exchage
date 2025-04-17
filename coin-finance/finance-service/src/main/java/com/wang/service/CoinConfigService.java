package com.wang.service;

import com.wang.domain.CoinConfig;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/17 15:52
 * @Version 1.0
 */
public interface CoinConfigService extends IService<CoinConfig> {
    /**
     * 通过币种的id 查询币种的配置信息
     * @param coinId 币种的id
     * @return 币种的配置信息
     */
    CoinConfig findByCoinId(Long coinId);

    /**
     * 新增或修改币种配置
     * @param coinConfig
     * @return
     */
    boolean updateOrSave(CoinConfig coinConfig);
}

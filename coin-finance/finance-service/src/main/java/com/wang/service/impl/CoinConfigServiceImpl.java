package com.wang.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.mapper.CoinConfigMapper;
import com.wang.domain.CoinConfig;
import com.wang.service.CoinConfigService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wang.domain.Coin;
import com.wang.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/17 15:52
 * @Version 1.0
 */
@Service
public class CoinConfigServiceImpl extends ServiceImpl<CoinConfigMapper, CoinConfig> implements CoinConfigService {
    @Autowired
    private CoinService coinService;

    /**
     * 通过币种的id 查询币种的配置信息
     *
     * @param coinId 币种的id
     * @return 币种的配置信息
     */
    @Override
    public CoinConfig findByCoinId(Long coinId) {
        // coinConfig的id 和Coin的id 值是相同的
        return getOne(new LambdaQueryWrapper<CoinConfig>().eq(CoinConfig::getId, coinId));
    }

    /**
     * 新增或修改币种配置
     *
     * @param coinConfig
     * @return
     */
    @Override
    public boolean updateOrSave(CoinConfig coinConfig) {
        //
        Coin coin = coinService.getById(coinConfig.getId());
        if(coin==null){
            throw new IllegalArgumentException("coin-Id不存在") ;
        }
        coinConfig.setCoinType(coin.getType());
        coinConfig.setName(coin.getName());
        // 如何新增/修改？
        CoinConfig config = getById(coinConfig.getId());
        if (config == null) { // 新增操作
            return save(coinConfig);
        } else { // 修改操作
            return updateById(coinConfig);
        }
    }
}

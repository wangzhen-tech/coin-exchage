package com.wang.service;

import com.wang.domain.Market;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.dto.MarketDto;
import java.util.List;

/**
 * @Author wangzhen
 * @Description 市场
 * @Date 2025/5/4 16:53
 * @Version 1.0
 */
public interface MarketService extends IService<Market>{


    /**
     * 分页查询市场的配置
     * @param page
     * 分页参数
     * @param tradeAreaId
     * 交易区域的ID
     * @param status
     * 状态
     * @return
     */
    Page<Market> findByPage(Page<Market> page, Long tradeAreaId, Byte status);


    /**
     * 使用交易区域的id 查询该区域下的市场
     * @param id
     *      区域的Id
     * @return
     */
//    List<Market> getMarkersByTradeAreaId(Long id);


    /**
     * 使用交易对查询市场
     * @param symbol
     * @return
     */
//    Market getMarkerBySymbol(String symbol);

    /**
     * 使用报价货币和基础货币查询市场
     * @param buyCoinId
     * @param sellCoinId
     * @return
     */
//    MarketDto findByCoinId(Long buyCoinId, Long sellCoinId);

    /**
     * 查询所有的市场数据
     * @return
     */
//    List<MarketDto> queryAllMarkets();


    /**
     * 使用交易区域查询市场
     * @param id
     * @return
     */
//    List<Market> queryByAreaId(Long id);
}

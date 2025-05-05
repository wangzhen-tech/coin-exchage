package com.wang.service;

import com.wang.domain.TradeArea;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.dto.TradeAreaDto;
import com.wang.vo.TradeAreaMarketVo;
import java.util.List;

/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/5/4 16:53
 * @Version 1.0
 */
public interface TradeAreaService extends IService<TradeArea>{


    /**
     * 分页查询交易区域
     * @param page
     * 分页参数
     * @param name
     * 交易区域的名称
     * @param status
     * 交易区域的状态
     * @return
     */
    Page<TradeArea> findByPage(Page<TradeArea> page, String name, Byte status);

    /**
     * 使用交易区域的状态查询列表
     * @param status
     * @return
     */
    List<TradeArea> findAll(Byte status);

    /**
     * 查询所有的交易区域以及市场
     * @return
     */
    List<TradeAreaMarketVo> findTradeAreaMarket();


    /**
     * 查询用户收藏的交易市场
     * @param userId
     * @return
     */
    List<TradeAreaMarketVo> getUserFavoriteMarkets(Long userId);

    /**
     * 查询所有的交易区域和交易区域下的市场
     * @return
     */
    List<TradeAreaDto> findAllTradeAreaAndMarket();


}

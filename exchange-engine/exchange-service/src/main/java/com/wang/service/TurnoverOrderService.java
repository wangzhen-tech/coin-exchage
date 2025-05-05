package com.wang.service;

import com.wang.domain.TurnoverOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.domain.TurnoverOrder;
//import com.wang.dto.TurnoverData24HDTO;
import java.util.List;
/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/5/4 16:53
 * @Version 1.0
 */
public interface TurnoverOrderService extends IService<TurnoverOrder>{


    /**
     * 查询分页对象
     * @param page      分页数据
     * @param userId          用户的ID
     * @param symbol       交易对
     * @param type         交易类型
     * @return
     */
    Page<TurnoverOrder> findByPage(Page<TurnoverOrder> page, Long userId, String symbol, Integer type);

    /**
     * 获取买入的订单的成功的记录
     * @param orderId
     * @return
     */
    List<TurnoverOrder> getBuyTurnoverOrder(Long orderId,Long userId);

    /**
     * 获取卖出订单的成交记录
     * @param orderId
     * @return
     */
    List<TurnoverOrder> getSellTurnoverOrder(Long orderId,Long userId);

    /**
     * 根据交易市场查询我们的成交记录
     * @param symbol
     * @return
     */
    List<TurnoverOrder> findBySymbol(String symbol);

    /**
     * 查询该交易对的24 小时成交记录
     * @param symbol
     * @return
     */
//    TurnoverData24HDTO query24HDealData(String symbol);
}

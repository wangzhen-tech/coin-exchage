package com.wang.service.impl;

import com.wang.domain.UserFavoriteMarket;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/5/4 23:20
 * @Version 1.0
 */
public interface UserFavoriteMarketService extends IService<UserFavoriteMarket>{

    /**
     * 用户取消收藏
     * @param marketId
     * @param userId
     * @return
     */
    boolean deleteUserFavoriteMarket(Long marketId, Long userId);
}

package com.wang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.domain.UserFavoriteMarket;
import com.wang.mapper.UserFavoriteMarketMapper;
import org.springframework.stereotype.Service;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/5/4 23:29
 * @Version 1.0
 */
@Service
public class UserFavoriteMarketServiceImpl extends ServiceImpl<UserFavoriteMarketMapper, UserFavoriteMarket> implements UserFavoriteMarketService{

    /**
     * 用户取消收藏
     *
     * @param marketId // 交易市场的id
     * @param userId // 用户的Id
     * @return
     */
    @Override
    public boolean deleteUserFavoriteMarket(Long marketId, Long userId) {
        return remove(new LambdaQueryWrapper<UserFavoriteMarket>()
                .eq(UserFavoriteMarket::getMarketId, marketId)
                .eq(UserFavoriteMarket::getUserId, userId)
        );
    }
}

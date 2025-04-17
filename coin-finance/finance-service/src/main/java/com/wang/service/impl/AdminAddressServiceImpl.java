package com.wang.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.domain.AdminAddress;
import com.wang.mapper.AdminAddressMapper;
import com.wang.service.AdminAddressService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.domain.Coin;
import com.wang.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/17 17:25
 * @Version 1.0
 */
@Service
public class AdminAddressServiceImpl extends ServiceImpl<AdminAddressMapper, AdminAddress> implements AdminAddressService{
    @Autowired
    private CoinService coinService ;
    /**
     * 条件分页查询归集地址
     *
     * @param page   分页参数
     * @param coinId 币种的Id
     * @return
     */
    @Override
    public Page<AdminAddress> findByPage(Page<AdminAddress> page, Long coinId) {
        return page(page,new LambdaQueryWrapper<AdminAddress>().eq(coinId!=null ,AdminAddress::getCoinId,coinId));
    }

    /**
     * 重新save ,为了让我们的归集地址里面包含coinType
     * @param entity
     * @return
     */
    @Override
    public boolean save(AdminAddress entity) {
        Long coinId = entity.getCoinId();
        Coin coin = coinService.getById(coinId);
        if(coin==null){
            throw new IllegalArgumentException("输入的币种id错误") ;
        }
        String type = coin.getType();
        entity.setCoinType(type);
        return super.save(entity) ;
    }
}

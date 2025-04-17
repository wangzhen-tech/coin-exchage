package com.wang.service;

import com.wang.domain.CoinType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.domain.CoinType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/17 15:52
 * @Version 1.0
 */
public interface CoinTypeService extends IService<CoinType> {
    /**
     * 条件分页查询货币类型
     * @param page  分页参数
     * @param code 币种类型的Code
     * @return 分页货币类型
     */
    Page<CoinType> findByPage(Page<CoinType> page, String code);

    /**
     * 使用币种类型的状态查询所有的币种类型值
     * @param status
     * @return
     */
    List<CoinType> listByStatus(Byte status);
}

package com.wang.service.impl;

import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.domain.CoinType;
import com.wang.mapper.CoinTypeMapper;
import com.wang.service.CoinTypeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;
/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/17 15:52
 * @Version 1.0
 */
@Service
public class CoinTypeServiceImpl extends ServiceImpl<CoinTypeMapper, CoinType> implements CoinTypeService {

    /**
     * 条件分页查询货币类型
     *
     * @param page 分页参数
     * @param code 类型的Code
     * @return 分页货币类型
     */
    @Override
    public Page<CoinType> findByPage(Page<CoinType> page, String code) {
        Page<CoinType> pageData = page(page, new LambdaQueryWrapper<CoinType>()
                .like(!StringUtils.isEmpty(code), CoinType::getCode, code));
        return pageData;
    }

    /**
     * 使用币种类型的状态查询所有的币种类型值
     *
     * @param status
     * @return
     */
    @Override
    public List<CoinType> listByStatus(Byte status) {
        return list(new LambdaQueryWrapper<CoinType>().eq(status!=null ,CoinType::getStatus,status));
    }
}

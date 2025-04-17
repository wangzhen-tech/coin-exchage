package com.wang.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.domain.CoinBalance;
import com.wang.mapper.CoinBalanceMapper;
import com.wang.service.CoinBalanceService;
/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/17 15:52
 * @Version 1.0
 */
@Service
public class CoinBalanceServiceImpl extends ServiceImpl<CoinBalanceMapper, CoinBalance> implements CoinBalanceService{

}

package com.wang.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.domain.Market;
import com.wang.mapper.MarketMapper;
import com.wang.service.MarketService;
/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/5/4 16:53
 * @Version 1.0
 */
@Service
public class MarketServiceImpl extends ServiceImpl<MarketMapper, Market> implements MarketService{

}

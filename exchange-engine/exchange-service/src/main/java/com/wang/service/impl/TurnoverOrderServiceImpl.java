package com.wang.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.mapper.TurnoverOrderMapper;
import com.wang.domain.TurnoverOrder;
import com.wang.service.TurnoverOrderService;
/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/5/4 16:53
 * @Version 1.0
 */
@Service
public class TurnoverOrderServiceImpl extends ServiceImpl<TurnoverOrderMapper, TurnoverOrder> implements TurnoverOrderService{

}

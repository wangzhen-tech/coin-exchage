package com.wang.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.domain.Sms;
import com.wang.mapper.SmsMapper;
import com.wang.service.SmsService;
/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/10 14:48
 * @Version 1.0
 */
@Service
public class SmsServiceImpl extends ServiceImpl<SmsMapper, Sms> implements SmsService{

}

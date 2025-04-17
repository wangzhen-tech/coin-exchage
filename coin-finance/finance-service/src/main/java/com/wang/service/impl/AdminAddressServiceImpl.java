package com.wang.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.domain.AdminAddress;
import com.wang.mapper.AdminAddressMapper;
import com.wang.service.AdminAddressService;
/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/17 17:25
 * @Version 1.0
 */
@Service
public class AdminAddressServiceImpl extends ServiceImpl<AdminAddressMapper, AdminAddress> implements AdminAddressService{

}

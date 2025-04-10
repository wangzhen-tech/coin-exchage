package com.wang.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.domain.UserAuthInfo;
import com.wang.mapper.UserAuthInfoMapper;
import com.wang.service.UserAuthInfoService;
/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/10 14:48
 * @Version 1.0
 */
@Service
public class UserAuthInfoServiceImpl extends ServiceImpl<UserAuthInfoMapper, UserAuthInfo> implements UserAuthInfoService{

}

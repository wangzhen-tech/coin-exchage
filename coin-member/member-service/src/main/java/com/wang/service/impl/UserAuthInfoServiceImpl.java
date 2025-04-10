package com.wang.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.domain.UserAuthInfo;
import com.wang.mapper.UserAuthInfoMapper;
import com.wang.service.UserAuthInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.util.Collections;
/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/10 14:48
 * @Version 1.0
 */
@Service
public class UserAuthInfoServiceImpl extends ServiceImpl<UserAuthInfoMapper, UserAuthInfo> implements UserAuthInfoService{
    /**
     * 用户未被认证,我们需要通过用户的ID 查询用户的额认证列表
     *
     * @param id
     * @return
     */
    @Override
    public List<UserAuthInfo> getUserAuthInfoByUserId(Long id) {
        List<UserAuthInfo> list = list(new LambdaQueryWrapper<UserAuthInfo>()
                .eq(UserAuthInfo::getUserId, id)
                .orderByDesc(UserAuthInfo::getCreated)
                .last("limit 3")// 只查询最近的3条记录
        );
        // 用户未被认证过，因此该值可能存在空的情况
        return list == null ? Collections.emptyList() : list ; // 处理null
    }

    /**
     * 用户通过认证，使用唯一的认证码来查询用户的认证详情
     *
     * @param authCode 认证的唯一Code
     * @return
     */
    @Override
    public List<UserAuthInfo> getUserAuthInfoByCode(Long authCode) {
        return list(new LambdaQueryWrapper<UserAuthInfo>()
                .eq(UserAuthInfo::getAuthCode,authCode) // 通过认证的唯一Code 来查询用户的认证信息了
        )   ;
    }
}

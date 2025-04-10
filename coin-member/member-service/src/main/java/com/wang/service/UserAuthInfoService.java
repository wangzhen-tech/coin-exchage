package com.wang.service;

import com.wang.domain.UserAuthInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/10 14:48
 * @Version 1.0
 */
public interface UserAuthInfoService extends IService<UserAuthInfo>{
    /**
     * 用户通过认证，使用唯一的认证码code来查询用户的认证详情
     * @param authCode
     * 认证的唯一Code
     * @return
     */
    List<UserAuthInfo> getUserAuthInfoByCode(Long authCode);

    /**
     * 用户未被认证,过用户的id来查询用户的认证列表
     * @param id
     * @return
     */
    List<UserAuthInfo> getUserAuthInfoByUserId(Long id);
}

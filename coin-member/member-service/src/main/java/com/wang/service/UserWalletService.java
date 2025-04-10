package com.wang.service;

import com.wang.domain.UserWallet;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.domain.UserWallet;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/10 14:48
 * @Version 1.0
 */
public interface UserWalletService extends IService<UserWallet>{
    /**
     * 分页查询用户的提币地址
     * @param page 分页参数
     * @param userId  用户的ID
     * @return
     */
    Page<UserWallet> findByPage(Page<UserWallet> page, Long userId);

    /**
     * 查询用户的提币的地址
     * @param userId 用户的Id
     * @param coinId 币种的Id
     * @return
     */
    List<UserWallet> findUserWallets(Long userId, Long coinId);

    /**
     * 删除用户的提现地址
     * @param addressId 提现地址的Id
     * @param payPassword 交易密码
     * @return
     */
    boolean deleteUserWallet(Long addressId, String payPassword);
}

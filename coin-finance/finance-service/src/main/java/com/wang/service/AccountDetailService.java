package com.wang.service;

import com.wang.domain.AccountDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/17 15:52
 * @Version 1.0
 */
public interface AccountDetailService extends IService<AccountDetail> {

    /**
     * 分页查询我们的资金流水
     * @param page  分页数据
     * @param coinId  币种的iD
     * @param accountId  账号的Id
     * @param userId  用户的id
     * @param userName  用户的名称
     * @param mobile  用户的手机
     * @param amountStart  金额最小值
     * @param amountEnd  金额的最大值
     * @param startTime  起始时间
     * @param endTime  截至时间
     * @return
     */
    Page<AccountDetail> findByPage(Page<AccountDetail> page, Long coinId, Long accountId, Long userId, String userName, String mobile, String amountStart, String amountEnd, String startTime, String endTime);

}

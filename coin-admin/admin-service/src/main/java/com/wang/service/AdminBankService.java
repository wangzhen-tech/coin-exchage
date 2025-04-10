package com.wang.service;

import com.wang.domain.AdminBank;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.domain.AdminBank;
//import com.wang.dto.AdminBankDto;
import java.util.List;

/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/9 21:26
 * @Version 1.0
 */
public interface AdminBankService extends IService<AdminBank>{
    /**
     * 条件分页查询公司银行卡
     * @param page  分页参数
     * @param bankCard 银行卡卡号
     * @return
     */
    Page<AdminBank> findByPage(Page<AdminBank> page, String bankCard);


    /**
     * 查询所有的银行开启信息
     * @return
     */
//    List<AdminBankDto> getAllAdminBanks();

}

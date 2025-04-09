package com.wang.service;

import com.wang.domain.WorkIssue;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.domain.WorkIssue;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/3 16:11
 * @Version 1.0
 */
public interface WorkIssueService extends IService<WorkIssue>{


    /**
     * 条件分页查询工单列表
     * @param page
     *  分页参数
     * @param status
     * 工单的状态
     * @param startTime
     * 查询的工单创建起始时间
     * @param endTime
     * 查询的工单创建截至时间
     * @return
     */
    Page<WorkIssue> findByPage(Page<WorkIssue> page, Integer status, String startTime, String endTime);


    /**
     * 前台系统查询客户工单
     * @param page
     * @param  userId 会员的Id
     *
     * @return
     */
    Page<WorkIssue> getIssueList(Page<WorkIssue> page,Long userId);


}

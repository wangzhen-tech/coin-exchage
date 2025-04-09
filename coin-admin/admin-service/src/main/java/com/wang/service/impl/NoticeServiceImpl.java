package com.wang.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.mapper.NoticeMapper;
import com.wang.domain.Notice;
import com.wang.service.NoticeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;

/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/3 16:11
 * @Version 1.0
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService{

    /**
     * 条件查询公告
     *
     * @param page      分页参数
     * @param title     公告的标签
     * @param startTime 公告创建的开始时间
     * @param endTime   公告创建的结束时间
     * @param status    公告当前的状态
     * @return
     */
    @Override
    public Page<Notice> findByPage(Page<Notice> page, String title, String startTime, String endTime, Integer status) {
        return page(page,new LambdaQueryWrapper<Notice>()
                // 模糊查询： like(什么条件执行模糊查询， 那个列， 按什么值查)
                .like(!StringUtils.isEmpty(title),Notice::getTitle,title)
                // 范围查询   between(什么条件下执行范围查询， 哪个列， 开始范围， 结束范围)
                .between(!StringUtils.isEmpty(startTime)&&!StringUtils.isEmpty(endTime) ,Notice::getCreated,startTime,endTime+" 23:59:59")
                // 精确查
                .eq(status!=null,Notice::getStatus,status)
        );
    }

    /**
     * 查询公告
     *
     * @param page
     * @return
     */
    @Override
    public Page<Notice> findNoticeForSimple(Page<Notice> page) {
        return page(page,new LambdaQueryWrapper<Notice>()
                .eq(Notice::getStatus,1)
                .orderByAsc(Notice::getSort)
        );
    }

}

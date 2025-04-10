package com.wang.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.domain.UserAuthAuditRecord;
import com.wang.mapper.UserAuthAuditRecordMapper;
import com.wang.service.UserAuthAuditRecordService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/10 14:48
 * @Version 1.0
 */
@Service
public class UserAuthAuditRecordServiceImpl extends ServiceImpl<UserAuthAuditRecordMapper, UserAuthAuditRecord> implements UserAuthAuditRecordService{
    /**
     * 获取一个用户的审核记录
     *
     * @param id
     * @return
     */
    @Override
    public List<UserAuthAuditRecord> getUserAuthAuditRecordList(Long id) {
        return list(new LambdaQueryWrapper<UserAuthAuditRecord>()
                .eq(UserAuthAuditRecord::getUserId ,id)
                .orderByDesc(UserAuthAuditRecord::getCreated)
                .last("limit 3")// 查询最近的3条记录
        );
    }

}

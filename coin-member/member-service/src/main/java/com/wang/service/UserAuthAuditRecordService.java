package com.wang.service;

import com.wang.domain.UserAuthAuditRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/10 14:48
 * @Version 1.0
 */
public interface UserAuthAuditRecordService extends IService<UserAuthAuditRecord>{
    /**
     * 获取一个用户的审核记录
     * @param id
     * @return
     */
    List<UserAuthAuditRecord> getUserAuthAuditRecordList(Long id);

}

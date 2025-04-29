package com.wang.service;

import com.wang.domain.Sms;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/10 14:48
 * @Version 1.0
 */
public interface SmsService extends IService<Sms>{

    /**
     * 发送一个短信
     * @param sms 短信内容
     * @return 是否发送成功
     */
    boolean sendSms(Sms sms);
}

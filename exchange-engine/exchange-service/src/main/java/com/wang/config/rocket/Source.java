package com.wang.config.rocket;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/5/5 19:38
 * @Version 1.0
 */
public interface Source {
    @Output("order_out")
    MessageChannel outputMessage() ;
}

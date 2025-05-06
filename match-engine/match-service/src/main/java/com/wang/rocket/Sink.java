package com.wang.rocket;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/5/6 20:53
 * @Version 1.0
 */
public interface Sink {
    @Input("order_in")
    public MessageChannel messageChannel() ;
}

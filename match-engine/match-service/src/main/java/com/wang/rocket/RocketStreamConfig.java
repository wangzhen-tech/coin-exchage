package com.wang.rocket;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

/**
 * @Author wangzhen
 * @Description 开启我们的Stream的开发
 * @Date 2025/5/6 20:56
 * @Version 1.0
 */
@Configuration
@EnableBinding(value = {Sink.class,Source.class}) //
public class RocketStreamConfig {
}

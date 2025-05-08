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
@EnableBinding(value = {Sink.class,Source.class})
//启用SpringCloud Stream的消息通道绑定功能。指定要绑定的消息通道接口有两个：Sink.class 和 Source.class。即消息输入和输出通道
// 会将sink和source中的方法自动注册为bean
public class RocketStreamConfig {
}

package com.wang.config.rocket;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;
/**
 * @Author wangzhen
 * @Description
 * @Date 2025/5/6 22:13
 * @Version 1.0
 */
@Configuration// 放入容器中管理
//@EnableBinding(value = Source.class) // ???
@EnableBinding(value = {Sink.class,Source.class})  //???
public class RocketMQConfig {
}

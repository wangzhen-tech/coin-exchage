package com.wang.disruptor;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/5/6 11:25
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "spring.disruptor")
public class DisruptorProperties {


    /**
     * 缓冲区的大小
     */
    private Integer ringBufferSize = 1024 * 1024 ;

    /**
     * 是否支持多生产者
     */
    private boolean isMultiProducer = false ;
}

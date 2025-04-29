package com.wang.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/4/29 16:53
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "identify")
@Data
public class IdProperties {

    /**
     * 身份认证的URL地址  // https://idcert.market.alicloudapi.com/idcard?idCard=%s&name=%s
     */
    private String url ;


    /***
     * 你购买的appKey
     */
    private String appKey ;

    /***
     * 你购买的appSecret
     */
    private String appSecret ;

    /***
     * 你购买的appCode
     */
    private String appCode ;
}

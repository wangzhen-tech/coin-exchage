package com.wang.feign;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
/**
 * @Author wangzhen
 * @Description 将feign访问的授权服务响应的信息封装为一个JwtToken
 * @Date 2025/4/7 16:17
 * @Version 1.0
 */
@Data
public class JwtToken {
    // 授权服务响应的信息就是如下6个字段：
    /**
     * access_token
     */
    @JsonProperty("access_token")
    private String accessToken;

    /**
     * token类型
     */
    @JsonProperty("token_type")
    private String tokenType;

    /**
     * refresh_token
     */
    @JsonProperty("refresh_token")
    private String refreshToken;

    /**
     * 过期时间
     */
    @JsonProperty("expires_in")
    private Long expiresIn;


    /**
     * token的范围
     */
    private String scope;

    /**
     * 颁发的凭证
     */
    private String jti;
}

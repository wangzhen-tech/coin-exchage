package com.wang.match;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.Map;
/**
 * @Author wangzhen
 * @Description
 * @Date 2025/5/6 21:09
 * @Version 1.0
 */

@Data
@ConfigurationProperties(prefix = "spring.match")
public class MatchEngineProperties {

    /**
     * 交易对的信息
     */
    private Map<String,CoinScale> symbols ;

    @Data
    public static  class  CoinScale {
        private int coinScale; // 交易币种的精度
        private int baseCoinScale;// 基币的精度
    }
}

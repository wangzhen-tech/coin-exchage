package com.wang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @Author wangzhen
 * @Description 授权服务配置，通过OAuth实现
 * @Date 2025/4/1 18:56
 * @Version 1.0
 */
@EnableAuthorizationServer // 开启授权服务器的功能
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired// 通过第三方的客户端对.secret()中的秘钥进行加密
    private PasswordEncoder passwordEncoder;

    @Autowired // 注入一个验证管理器
    private AuthenticationManager authenticationManager;

//    @Qualifier("userServiceDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

//    @Autowired // redis工厂，用于连接redis数据源
//    private RedisConnectionFactory redisConnectionFactory ;

    /**
     * 添加第三方的客户端
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory() // 放在内存中
                .withClient("coin-api") // 第三方客户端的名称
                .secret(passwordEncoder.encode("coin-secret")) //  第三方客户端的密钥
                .scopes("all") //第三方客户端的授权范围
                .authorizedGrantTypes("password","refresh_token") // 允许两种授权机制
                .accessTokenValiditySeconds(7 * 24 *3600) // 获取到的token的有效期 1周
                .refreshTokenValiditySeconds(30 * 24 * 3600)// refresh_token的有效期 1个月
                // 应用之间内部token的传递和获取  (因为不同的服务之间通信可能需要校验token，而token只在登录时会被获取，其他间接类型的请求就需要自己去请求的上下文提取token)
                .and()
                .withClient("inside-app")
                .secret(passwordEncoder.encode("inside-secret"))
                .authorizedGrantTypes("client_credentials")
                .scopes("all")
                .accessTokenValiditySeconds(7 * 24 *3600)
        ;
        super.configure(clients);
    }

    /**
     * 配置验证管理器，UserdetailService
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager) // 该验证管理器需要注入
                .userDetailsService(userDetailsService)
//                .tokenStore(redisTokenStore());// 使用redis存储token
                .tokenStore(jwtTokenStore())//  jwt 存储token
                .tokenEnhancer(jwtAccessTokenConverter());// twt需要转换器转换成json存储

        super.configure(endpoints);
    }
//    private TokenStore redisTokenStore() {
//        return new RedisTokenStore(redisConnectionFactory);
//    }

    private TokenStore jwtTokenStore() {
        JwtTokenStore jwtTokenStore = new JwtTokenStore(jwtAccessTokenConverter());
        return jwtTokenStore;
    }

    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        // 加载我们的私钥
        ClassPathResource classPathResource = new ClassPathResource("coinexchange.jks");
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(classPathResource, "coinexchange".toCharArray());
        tokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("coinexchange", "coinexchange".toCharArray()));
        return tokenConverter;
    }
}

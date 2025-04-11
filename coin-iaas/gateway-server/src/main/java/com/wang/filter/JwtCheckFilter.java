package com.wang.filter;

import com.alibaba.fastjson.JSONObject;
import com.google.common.net.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

/**
 * @Author wangzhen
 * @Description 网关检测jwt
 * @Date 2025/4/2 11:06
 * @Version 1.0
 */

@Component
public class JwtCheckFilter implements GlobalFilter, Ordered {

    @Autowired
    private StringRedisTemplate redisTemplate ;

    // 通过一个set封装不需要token就可以访问的路径
    // (/admin/login:管理员登录)   (/user/gt/register:极验) (/user/login:普通用户登录)
    @Value("${no.require.urls:/admin/login,/user/gt/register,/user/login,/user/users/register,/user/sms/sendTo,/user/users/setPassword}")
    private Set<String> noRequireTokenUris ;

    /**
     * 过滤器拦截到用户的请求后要执行的事
     * @param exchange 这个参数封装了当前 HTTP 请求和响应的信息。 通过 exchange，你可以获取请求头、查询参数、路径信息、请求体等，也可以修改响应信息。
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1 : 该接口是否需要token 才能访问
        if(!isRequireToken(exchange)){
            return chain.filter(exchange) ;// 不需要token ,直接放行
        }
        // 2: 取出用户的token
        String token = getUserToken(exchange) ;
        // 3 判断用户的token 是否有效
        if(StringUtils.isEmpty(token)){
            return buildeNoAuthorizationResult(exchange) ;
        }
        Boolean hasKey = redisTemplate.hasKey(token);
        if(hasKey!=null && hasKey){// redis中这个值存在并且不为空
            return chain.filter(exchange) ;// token有效 ,直接放行
        }
        return buildeNoAuthorizationResult(exchange) ;
    }
    /**
     * 判断该接口是否需要token
     * @param exchange
     * @return
     */
    private boolean isRequireToken(ServerWebExchange exchange) {
        String path = exchange.getRequest().getURI().getPath();
        if(noRequireTokenUris.contains(path)){
            return false ; // 该路径不需要token，如login等
        }
        return Boolean.TRUE ;
    }
    /**
     * 从 请求头里面获取用户的token
     * @param exchange
     * @return
     */
    private String getUserToken(ServerWebExchange exchange) {
        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        return token ==null ? null : token.replace("bearer ","") ;
    }


    /**
     * 给用户响应一个没有token的错误
     * @param exchange
     * @return
     */
    private Mono<Void> buildeNoAuthorizationResult(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().set("Content-Type","application/json");
        response.setStatusCode(HttpStatus.UNAUTHORIZED) ;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error","NoAuthorization") ;
        jsonObject.put("errorMsg","Token is Null or Error") ;
        DataBuffer wrap = response.bufferFactory().wrap(jsonObject.toJSONString().getBytes());
        return response.writeWith(Flux.just(wrap)) ;
    }





    /**
     * 拦截器的顺序
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}

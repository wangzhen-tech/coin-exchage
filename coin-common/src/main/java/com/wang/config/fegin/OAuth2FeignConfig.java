package com.wang.config.fegin;

import com.wang.constant.Constants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
/**
 * @Author wangzhen
 * @Description Fegin远程调用
 * @Date 2025/4/16 16:19
 * @Version 1.0
 */
@Slf4j
public class OAuth2FeignConfig implements RequestInterceptor {// 通过实现reqInter来将本类实现一个全局的效果

    /**
     * Called for every request. Add data using methods on the supplied {@link RequestTemplate}.
     *
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        // 1 我们可以从request的上下文环境里面获取token
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        String header = null;
        if (requestAttributes == null) {
            log.info("没有请求的上下文,故无法进行token的传递");
//            header = "bearer "+ Constants.INSIDE_TOKEN ;
        } else {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            // 获取请求上下文的头里面的AUTHORIZATION
            header = request.getHeader(HttpHeaders.AUTHORIZATION);
        }

        if (!StringUtils.isEmpty(header)) {
            template.header(HttpHeaders.AUTHORIZATION, header);
            log.info("本次token传递成功,token的值为:{}", header);
        }
    }
}
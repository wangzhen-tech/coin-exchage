package com.wang.feign;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/4/16 19:48
 * @Version 1.0
 */
import com.wang.config.fegin.OAuth2FeignConfig;
import com.wang.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "member-service",configuration = OAuth2FeignConfig.class ,path = "/users")
// 调用member-Service的服务；使用xxx.class的配置(完成token的传递),这个配置在common中定义；路径前缀是/users
public interface UserServiceFeign {

    /**
     * 通过id的List进行批量查询
     * 用于admin-service里面远程调用member-service
     * @param ids
     * @return
     */
//    List<UserDto>  getBasicUsers(@RequestParam("ids") List<Long> ids) ;
    //  Map<Long,UserDto> Long:userId,UserDto 用户的基础信息
    @GetMapping("/basic/users")
    Map<Long,UserDto> getBasicUsers(
            @RequestParam(value = "ids",required = false) List<Long> ids,
            @RequestParam(value = "userName",required = false) String userName ,
            @RequestParam(value = "mobile",required = false) String mobile
    ) ;
}

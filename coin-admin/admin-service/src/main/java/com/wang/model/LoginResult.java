package com.wang.model;

import com.wang.domain.SysMenu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;

/**
 * @Author wangzhen
 * @Description 登录结果
 * @Date 2025/4/7 15:53
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "登录的结果")
public class LoginResult {
    /**
     * 登录成功的token，来自authorization-server里面
     */
    @ApiModelProperty(value = "登录成功的token，来自我们的authorization-server 里面的")
    private String token ;

    /**
     * 该用户的菜单数据
     */
    @ApiModelProperty(value = "该用户的菜单数据")
    private List<SysMenu> menus ;

    /**
     * 该用户的权限数据
     */
    @ApiModelProperty(value = "该用户的权限数据")
    private List<SimpleGrantedAuthority> authorities ;
}

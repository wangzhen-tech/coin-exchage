package com.wang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

/**
 * @Author wangzhen
 * @Description web安全相关的配置
 * @Date 2025/4/1 19:07
 * @Version 1.0
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean// 验证管理器
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean // 密码加密器
    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();// 测试期间为了简单，返回空的密码加密器
        return new BCryptPasswordEncoder();
        // BCryptPassxxx SpringSecurity实现的非常强的加密器，密码不能被逆向，同一个密码多次加密的结果不同。
    }

//    @Bean // 在内存中伪造一个用户样例：用户名admin，密码123456，角色为admin
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
//        User user = new User("admin", "123456", Arrays.asList(new SimpleGrantedAuthority("Role_Admin")));
//        inMemoryUserDetailsManager.createUser(user);
//        return inMemoryUserDetailsManager;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // 禁用crsf攻击
        http.authorizeRequests().anyRequest().authenticated(); // 访问须授权
    }
}

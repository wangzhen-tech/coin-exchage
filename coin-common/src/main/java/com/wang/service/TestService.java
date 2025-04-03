package com.wang.service;

import com.wang.model.WebLog;
import org.springframework.stereotype.Service;

/**
 * @Author wangzhen
 * @Description 测试接口
 * @Date 2025/4/3 15:20
 * @Version 1.0
 */
public interface TestService {

    WebLog get(String username);
}

package com.wang.service.impl;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.wang.model.WebLog;
import com.wang.service.TestService;
import jdk.nashorn.internal.ir.CallNode;
import org.springframework.stereotype.Service;

/**
 * @Author wangzhen
 * @Description
 * @Date 2025/4/3 15:21
 * @Version 1.0
 */

@Service
public class TestServiceImpl implements TestService {
    @Override // 通过用户name查询weblog
    @Cached(name="com.wang.service.impl:", key = "#username", cacheType = CacheType.BOTH)
    // name是key的前缀，缓存类型：既在本地做，也在redis做
    public WebLog get(String username) {
        WebLog webLog = new WebLog();
        webLog.setUsername(username);
        webLog.setResult("ok");
        return webLog;
    }
}

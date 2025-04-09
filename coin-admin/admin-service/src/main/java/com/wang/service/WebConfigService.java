package com.wang.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.domain.WebConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
    /**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/3 16:11
 * @Version 1.0
 */
public interface WebConfigService extends IService<WebConfig>{


     /**
     * 条件分页查询
     * @param page 分页参数
     * @param name webConfig的名称
     * @param type webConfig的类型
     * @return
     */
    Page<WebConfig> findByPage(Page<WebConfig> page, String name, String type);

    /**
     * 查询所有的web_banner
     * @return
     */
    List<WebConfig> findWebBanners();

    /**
     * 查询PC端的banner
     * @return
     */
    List<WebConfig> getPcBanners();
}

package com.huangpuguang.common.core.web.service;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>功能描述 </p>
 *
 * @author Procon
 * @since 2021/7/21
 */
public interface BaseService<T> extends IService<T> {
    /**
     * 设置分页请求(在mapper前使用)
     **/
    void startPage();
}

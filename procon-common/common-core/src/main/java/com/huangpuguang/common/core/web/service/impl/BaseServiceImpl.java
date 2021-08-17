package com.huangpuguang.common.core.web.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.page.PageMethod;
import com.huangpuguang.common.core.utils.StringUtils;
import com.huangpuguang.common.core.utils.sql.SqlUtil;
import com.huangpuguang.common.core.web.service.BaseService;
import com.huangpuguang.common.core.web.page.PageDomain;
import com.huangpuguang.common.core.web.page.TableSupport;

/**
 * <p>基础service </p>
 *
 * @author Procon
 * @since 2021/7/21
 */

public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M,T>  implements BaseService<T> {

    @Override
    public void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageMethod.startPage(pageNum, pageSize, orderBy);
        }
    }
}

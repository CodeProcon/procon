package com.huangpuguang.common.core.web.page;

import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.huangpuguang.common.core.utils.ProconStringUtils;
import com.huangpuguang.common.core.utils.sql.SqlUtil;

/**
 * 分页操作
 *
 * @author procon
 * @version 1.0
 * @since 2020/12/8
 */
public class PageOper<T> {
    /**
     * 设置请求分页数据
     */
    protected Page<T> startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        Page<T> page = null;
        if (ProconStringUtils.isNotNull(pageNum) && ProconStringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            page = PageMethod.startPage(pageNum, pageSize, orderBy);
        }
        return page;
    }
}

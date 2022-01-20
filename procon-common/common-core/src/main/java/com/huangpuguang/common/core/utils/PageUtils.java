package com.huangpuguang.common.core.utils;

import com.github.pagehelper.PageHelper;
import com.huangpuguang.common.core.utils.sql.SqlUtil;
import com.huangpuguang.common.core.web.page.PageDomain;
import com.huangpuguang.common.core.web.page.TableSupport;

/**
 * <p>分页工具类</p>
 *
 * @author Procon
 * @since 2022/1/20
 */
public class PageUtils extends PageHelper {
    /**
     * 设置请求分页数据
     */
    public static void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (ProconStringUtils.isNotNull(pageNum) && ProconStringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            Boolean reasonable = pageDomain.getReasonable();
            PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
        }
    }
}

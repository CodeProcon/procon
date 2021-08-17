package com.huangpuguang.blog.service.impl;

import com.huangpuguang.blog.domain.BlogCommentReport;
import com.huangpuguang.blog.mapper.BlogCommentReportMapper;
import com.huangpuguang.blog.service.BlogCommentReportService;
import com.huangpuguang.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论举报Service业务层处理
 *
 * @author procon
 * @date 2020-10-27
 */
@Service
public class BlogCommentReportServiceImpl implements BlogCommentReportService
{
    @Autowired
    private BlogCommentReportMapper blogCommentReportMapper;

    /**
     * 查询评论举报
     *
     * @param id 评论举报ID
     * @return 评论举报
     */
    @Override
    public BlogCommentReport selectBlogCommentReportById(Long id)
    {
        return blogCommentReportMapper.selectBlogCommentReportById(id);
    }

    /**
     * 查询评论举报列表
     *
     * @param blogCommentReport 评论举报
     * @return 评论举报
     */
    @Override
    public List<BlogCommentReport> selectBlogCommentReportList(BlogCommentReport blogCommentReport)
    {
        return blogCommentReportMapper.selectBlogCommentReportList(blogCommentReport);
    }

    /**
     * 新增评论举报
     *
     * @param blogCommentReport 评论举报
     * @return 结果
     */
    @Override
    public int insertBlogCommentReport(BlogCommentReport blogCommentReport)
    {
        blogCommentReport.setCreateTime(DateUtils.getNowDate());
        return blogCommentReportMapper.insertBlogCommentReport(blogCommentReport);
    }

    /**
     * 修改评论举报
     *
     * @param blogCommentReport 评论举报
     * @return 结果
     */
    @Override
    public int updateBlogCommentReport(BlogCommentReport blogCommentReport)
    {
        blogCommentReport.setUpdateTime(DateUtils.getNowDate());
        return blogCommentReportMapper.updateBlogCommentReport(blogCommentReport);
    }

    /**
     * 批量删除评论举报
     *
     * @param ids 需要删除的评论举报ID
     * @return 结果
     */
    @Override
    public int deleteBlogCommentReportByIds(Long[] ids)
    {
        return blogCommentReportMapper.deleteBlogCommentReportByIds(ids);
    }

    /**
     * 删除评论举报信息
     *
     * @param id 评论举报ID
     * @return 结果
     */
    @Override
    public int deleteBlogCommentReportById(Long id)
    {
        return blogCommentReportMapper.deleteBlogCommentReportById(id);
    }
}

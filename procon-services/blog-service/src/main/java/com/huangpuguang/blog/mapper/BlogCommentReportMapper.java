package com.huangpuguang.blog.mapper;

import com.huangpuguang.blog.domain.BlogCommentReport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论举报Mapper接口
 *
 * @author procon
 * @date 2020-10-27
 */
@Repository
public interface BlogCommentReportMapper
{
    /**
     * 查询评论举报
     *
     * @param id 评论举报ID
     * @return 评论举报
     */

    public BlogCommentReport selectBlogCommentReportById(Long id);

    /**
     * 查询评论举报列表
     *
     * @param blogCommentReport 评论举报
     * @return 评论举报集合
     */
    public List<BlogCommentReport> selectBlogCommentReportList(BlogCommentReport blogCommentReport);

    /**
     * 新增评论举报
     *
     * @param blogCommentReport 评论举报
     * @return 结果
     */
    public int insertBlogCommentReport(BlogCommentReport blogCommentReport);

    /**
     * 修改评论举报
     *
     * @param blogCommentReport 评论举报
     * @return 结果
     */
    public int updateBlogCommentReport(BlogCommentReport blogCommentReport);

    /**
     * 删除评论举报
     *
     * @param id 评论举报ID
     * @return 结果
     */
    public int deleteBlogCommentReportById(Long id);

    /**
     * 批量删除评论举报
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBlogCommentReportByIds(Long[] ids);
}

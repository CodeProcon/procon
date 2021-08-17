package com.huangpuguang.blog.mapper;

import com.huangpuguang.blog.domain.BlogComment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论Mapper接口
 *
 * @author procon
 * @date 2020-10-27
 */
@Repository
public interface BlogCommentMapper
{
    /**
     * 查询评论
     *
     * @param id 评论ID
     * @return 评论
     */

    public BlogComment selectBlogCommentById(Long id);

    /**
     * 查询评论列表
     *
     * @param blogComment 评论
     * @return 评论集合
     */
    public List<BlogComment> selectBlogCommentList(BlogComment blogComment);

    /**
     * 新增评论
     *
     * @param blogComment 评论
     * @return 结果
     */
    public int insertBlogComment(BlogComment blogComment);

    /**
     * 修改评论
     *
     * @param blogComment 评论
     * @return 结果
     */
    public int updateBlogComment(BlogComment blogComment);

    /**
     * 删除评论
     *
     * @param id 评论ID
     * @return 结果
     */
    public int deleteBlogCommentById(Long id);

    /**
     * 批量删除评论
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBlogCommentByIds(Long[] ids);

    /**
     * 功能描述 查询一级评论
     * @param blogComment 博客评论
     * @return java.util.List<com.huangpuguang.blog.domain.BlogComment>
     */
    public List<BlogComment> selectFirstBlogCommentList(BlogComment blogComment);

    /**
     * 查询用户评论或回复
     * @param blogComment 博客评论
     * @return java.util.List<com.huangpuguang.blog.domain.BlogComment>
     */
    public List<BlogComment> selectUserCommentList(BlogComment blogComment);
}

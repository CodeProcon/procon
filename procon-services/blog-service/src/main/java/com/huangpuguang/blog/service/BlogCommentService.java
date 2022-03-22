package com.huangpuguang.blog.service;

import com.huangpuguang.blog.domain.BlogComment;
import com.huangpuguang.common.core.web.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 评论Service接口
 *
 * @author procon
 * @date 2020-10-27
 */
public interface BlogCommentService
{
    /**
     * 查询评论
     *
     * @param id 评论ID
     * @return 评论
     */
    BlogComment selectBlogCommentById(Long id);

    /**
     * 查询评论列表
     *
     * @param blogComment 评论
     * @return 评论集合
     */
    List<BlogComment> selectBlogCommentList(BlogComment blogComment);

    /**
     * 新增评论
     *
     * @param blogComment 评论
     * @return 结果
     */
    int insertBlogComment(BlogComment blogComment);

    /**
     * 修改评论
     *
     * @param blogComment 评论
     * @return 结果
     */
    int updateBlogComment(BlogComment blogComment);

    /**
     * 批量删除评论
     *
     * @param ids 需要删除的评论ID
     * @return 结果
     */
    int deleteBlogCommentByIds(Long[] ids);

    /**
     * 删除评论信息
     *
     * @param id 评论ID
     * @return 结果
     */
    int deleteBlogCommentById(Long id);

    /**
     * 博客列表
     * @param blogComment 参数
     * @return com.huangpuguang.common.core.web.page.TableDataInfo
     */
    TableDataInfo getBlogCommentResp(BlogComment blogComment);

    /**
     * 获取用户的评论列表
     * @param request 请求request
     * @param blogComment 用户
     * @return java.lang.String
     */
    Map<String, Object> getListByUser(HttpServletRequest request, BlogComment blogComment);

    /**
     * 获取用户点赞列表
     * @param blogComment 参数
     * @return 点赞列表
     */
    List<BlogComment> getPraiseListByUser(BlogComment blogComment);

    /**
     * 添加博客
     * @param comment 评论
     * @param userId 用户id
     * @return com.huangpuguang.blog.domain.BlogComment
     */
    BlogComment add(BlogComment comment, Long userId);

    /**
     * 举报评论
     * @param comment 评论
     * @return void
     */
    void reportComment(BlogComment comment);

    /**
     * 删除评论
     * @param comment
     */
    void deleteComment(BlogComment comment);
}

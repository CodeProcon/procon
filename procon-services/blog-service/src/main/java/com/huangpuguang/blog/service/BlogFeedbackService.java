package com.huangpuguang.blog.service;

import com.huangpuguang.blog.domain.BlogFeedback;

import java.util.List;

/**
 * 反馈Service接口
 *
 * @author procon
 * @date 2020-10-27
 */
public interface BlogFeedbackService
{
    /**
     * 查询反馈
     *
     * @param id 反馈ID
     * @return 反馈
     */
    public BlogFeedback selectBlogFeedbackById(Long id);

    /**
     * 查询反馈列表
     *
     * @param blogFeedback 反馈
     * @return 反馈集合
     */
    public List<BlogFeedback> selectBlogFeedbackList(BlogFeedback blogFeedback);

    /**
     * 新增反馈
     *
     * @param blogFeedback 反馈
     * @return 结果
     */
    public int insertBlogFeedback(BlogFeedback blogFeedback);

    /**
     * 修改反馈
     *
     * @param blogFeedback 反馈
     * @return 结果
     */
    public int updateBlogFeedback(BlogFeedback blogFeedback);

    /**
     * 批量删除反馈
     *
     * @param ids 需要删除的反馈ID
     * @return 结果
     */
    public int deleteBlogFeedbackByIds(Long[] ids);

    /**
     * 删除反馈信息
     *
     * @param id 反馈ID
     * @return 结果
     */
    public int deleteBlogFeedbackById(Long id);
}

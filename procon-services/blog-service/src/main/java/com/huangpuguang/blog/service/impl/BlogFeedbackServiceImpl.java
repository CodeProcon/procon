package com.huangpuguang.blog.service.impl;

import com.huangpuguang.blog.domain.BlogFeedback;
import com.huangpuguang.blog.mapper.BlogFeedbackMapper;
import com.huangpuguang.blog.service.BlogFeedbackService;
import com.huangpuguang.common.core.utils.ProconDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 反馈Service业务层处理
 *
 * @author procon
 * @date 2020-10-27
 */
@Service
public class BlogFeedbackServiceImpl implements BlogFeedbackService
{
    @Autowired
    private BlogFeedbackMapper blogFeedbackMapper;

    /**
     * 查询反馈
     *
     * @param id 反馈ID
     * @return 反馈
     */
    @Override
    public BlogFeedback selectBlogFeedbackById(Long id)
    {
        return blogFeedbackMapper.selectBlogFeedbackById(id);
    }

    /**
     * 查询反馈列表
     *
     * @param blogFeedback 反馈
     * @return 反馈
     */
    @Override
    public List<BlogFeedback> selectBlogFeedbackList(BlogFeedback blogFeedback)
    {
        return blogFeedbackMapper.selectBlogFeedbackList(blogFeedback);
    }

    /**
     * 新增反馈
     *
     * @param blogFeedback 反馈
     * @return 结果
     */
    @Override
    public int insertBlogFeedback(BlogFeedback blogFeedback)
    {
        blogFeedback.setCreateTime(ProconDateUtils.getNowDate());
        return blogFeedbackMapper.insertBlogFeedback(blogFeedback);
    }

    /**
     * 修改反馈
     *
     * @param blogFeedback 反馈
     * @return 结果
     */
    @Override
    public int updateBlogFeedback(BlogFeedback blogFeedback)
    {
        blogFeedback.setUpdateTime(ProconDateUtils.getNowDate());
        return blogFeedbackMapper.updateBlogFeedback(blogFeedback);
    }

    /**
     * 批量删除反馈
     *
     * @param ids 需要删除的反馈ID
     * @return 结果
     */
    @Override
    public int deleteBlogFeedbackByIds(Long[] ids)
    {
        return blogFeedbackMapper.deleteBlogFeedbackByIds(ids);
    }

    /**
     * 删除反馈信息
     *
     * @param id 反馈ID
     * @return 结果
     */
    @Override
    public int deleteBlogFeedbackById(Long id)
    {
        return blogFeedbackMapper.deleteBlogFeedbackById(id);
    }
}

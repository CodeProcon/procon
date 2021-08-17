package com.huangpuguang.blog.service.impl;

import com.huangpuguang.blog.domain.BlogTag;
import com.huangpuguang.blog.mapper.BlogTagMapper;
import com.huangpuguang.blog.service.BlogTagService;
import com.huangpuguang.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标签Service业务层处理
 *
 * @author procon
 * @date 2020-10-27
 */
@Service
public class BlogTagServiceImpl implements BlogTagService
{
    @Autowired
    private BlogTagMapper blogTagMapper;

    /**
     * 查询标签
     *
     * @param id 标签ID
     * @return 标签
     */
    @Override
    public BlogTag selectBlogTagById(Long id)
    {
        return blogTagMapper.selectBlogTagById(id);
    }

    /**
     * 查询标签列表
     *
     * @param blogTag 标签
     * @return 标签
     */
    @Override
    public List<BlogTag> selectBlogTagList(BlogTag blogTag)
    {
        return blogTagMapper.selectBlogTagList(blogTag);
    }

    /**
     * 新增标签
     *
     * @param blogTag 标签
     * @return 结果
     */
    @Override
    public int insertBlogTag(BlogTag blogTag)
    {
        blogTag.setCreateTime(DateUtils.getNowDate());
        return blogTagMapper.insertBlogTag(blogTag);
    }

    /**
     * 修改标签
     *
     * @param blogTag 标签
     * @return 结果
     */
    @Override
    public int updateBlogTag(BlogTag blogTag)
    {
        blogTag.setUpdateTime(DateUtils.getNowDate());
        return blogTagMapper.updateBlogTag(blogTag);
    }

    /**
     * 批量删除标签
     *
     * @param ids 需要删除的标签ID
     * @return 结果
     */
    @Override
    public int deleteBlogTagByIds(Long[] ids)
    {
        return blogTagMapper.deleteBlogTagByIds(ids);
    }

    /**
     * 删除标签信息
     *
     * @param id 标签ID
     * @return 结果
     */
    @Override
    public int deleteBlogTagById(Long id)
    {
        return blogTagMapper.deleteBlogTagById(id);
    }
}

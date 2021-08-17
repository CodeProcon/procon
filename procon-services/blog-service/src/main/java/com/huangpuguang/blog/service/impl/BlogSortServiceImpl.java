package com.huangpuguang.blog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.huangpuguang.blog.domain.BlogSort;
import com.huangpuguang.blog.mapper.BlogSortMapper;
import com.huangpuguang.blog.service.BlogSortService;
import com.huangpuguang.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 博客分类Service业务层处理
 *
 * @author procon
 * @date 2020-10-27
 */
@Service
public class BlogSortServiceImpl implements BlogSortService
{
    @Autowired
    private BlogSortMapper blogSortMapper;

    /**
     * 查询博客分类
     *
     * @param id 博客分类ID
     * @return 博客分类
     */
    @Override
    public BlogSort selectBlogSortById(Long id)
    {
        return blogSortMapper.selectBlogSortById(id);
    }

    /**
     * 查询博客分类列表
     *
     * @param blogSort 博客分类
     * @return 博客分类
     */
    @Override
    public List<BlogSort> selectBlogSortList(BlogSort blogSort)
    {
        return blogSortMapper.selectBlogSortList(blogSort);
    }

    /**
     * 新增博客分类
     *
     * @param blogSort 博客分类
     * @return 结果
     */
    @Override
    public int insertBlogSort(BlogSort blogSort)
    {
        blogSort.setCreateTime(DateUtils.getNowDate());
        blogSort.setUpdateTime(DateUtil.date());
        return blogSortMapper.insertBlogSort(blogSort);
    }

    /**
     * 修改博客分类
     *
     * @param blogSort 博客分类
     * @return 结果
     */
    @Override
    public int updateBlogSort(BlogSort blogSort)
    {
        blogSort.setUpdateTime(DateUtils.getNowDate());
        return blogSortMapper.updateBlogSort(blogSort);
    }

    /**
     * 批量删除博客分类
     *
     * @param ids 需要删除的博客分类ID
     * @return 结果
     */
    @Override
    public int deleteBlogSortByIds(Long[] ids)
    {
        return blogSortMapper.deleteBlogSortByIds(ids);
    }

    /**
     * 删除博客分类信息
     *
     * @param id 博客分类ID
     * @return 结果
     */
    @Override
    public int deleteBlogSortById(Long id)
    {
        return blogSortMapper.deleteBlogSortById(id);
    }
}

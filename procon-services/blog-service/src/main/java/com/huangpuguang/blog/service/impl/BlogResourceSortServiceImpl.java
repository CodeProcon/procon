package com.huangpuguang.blog.service.impl;

import com.huangpuguang.blog.domain.BlogResourceSort;
import com.huangpuguang.blog.mapper.BlogResourceSortMapper;
import com.huangpuguang.blog.service.BlogResourceSortService;
import com.huangpuguang.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资源分类Service业务层处理
 *
 * @author procon
 * @date 2020-10-27
 */
@Service
public class BlogResourceSortServiceImpl implements BlogResourceSortService
{
    @Autowired
    private BlogResourceSortMapper blogResourceSortMapper;

    /**
     * 查询资源分类
     *
     * @param id 资源分类ID
     * @return 资源分类
     */
    @Override
    public BlogResourceSort selectBlogResourceSortById(Long id)
    {
        return blogResourceSortMapper.selectBlogResourceSortById(id);
    }

    /**
     * 查询资源分类列表
     *
     * @param blogResourceSort 资源分类
     * @return 资源分类
     */
    @Override
    public List<BlogResourceSort> selectBlogResourceSortList(BlogResourceSort blogResourceSort)
    {
        return blogResourceSortMapper.selectBlogResourceSortList(blogResourceSort);
    }

    /**
     * 新增资源分类
     *
     * @param blogResourceSort 资源分类
     * @return 结果
     */
    @Override
    public int insertBlogResourceSort(BlogResourceSort blogResourceSort)
    {
        blogResourceSort.setCreateTime(DateUtils.getNowDate());
        return blogResourceSortMapper.insertBlogResourceSort(blogResourceSort);
    }

    /**
     * 修改资源分类
     *
     * @param blogResourceSort 资源分类
     * @return 结果
     */
    @Override
    public int updateBlogResourceSort(BlogResourceSort blogResourceSort)
    {
        blogResourceSort.setUpdateTime(DateUtils.getNowDate());
        return blogResourceSortMapper.updateBlogResourceSort(blogResourceSort);
    }

    /**
     * 批量删除资源分类
     *
     * @param ids 需要删除的资源分类ID
     * @return 结果
     */
    @Override
    public int deleteBlogResourceSortByIds(Long[] ids)
    {
        return blogResourceSortMapper.deleteBlogResourceSortByIds(ids);
    }

    /**
     * 删除资源分类信息
     *
     * @param id 资源分类ID
     * @return 结果
     */
    @Override
    public int deleteBlogResourceSortById(Long id)
    {
        return blogResourceSortMapper.deleteBlogResourceSortById(id);
    }
}

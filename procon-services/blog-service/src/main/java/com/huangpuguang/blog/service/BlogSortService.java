package com.huangpuguang.blog.service;

import com.huangpuguang.blog.domain.BlogSort;

import java.util.List;

/**
 * 博客分类Service接口
 *
 * @author procon
 * @date 2020-10-27
 */
public interface BlogSortService
{
    /**
     * 查询博客分类
     *
     * @param id 博客分类ID
     * @return 博客分类
     */
    public BlogSort selectBlogSortById(Long id);

    /**
     * 查询博客分类列表
     *
     * @param blogSort 博客分类
     * @return 博客分类集合
     */
    public List<BlogSort> selectBlogSortList(BlogSort blogSort);

    /**
     * 新增博客分类
     *
     * @param blogSort 博客分类
     * @return 结果
     */
    public int insertBlogSort(BlogSort blogSort);

    /**
     * 修改博客分类
     *
     * @param blogSort 博客分类
     * @return 结果
     */
    public int updateBlogSort(BlogSort blogSort);

    /**
     * 批量删除博客分类
     *
     * @param ids 需要删除的博客分类ID
     * @return 结果
     */
    public int deleteBlogSortByIds(Long[] ids);

    /**
     * 删除博客分类信息
     *
     * @param id 博客分类ID
     * @return 结果
     */
    public int deleteBlogSortById(Long id);
}

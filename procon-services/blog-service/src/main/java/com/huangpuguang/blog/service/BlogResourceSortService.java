package com.huangpuguang.blog.service;

import com.huangpuguang.blog.domain.BlogResourceSort;

import java.util.List;

/**
 * 资源分类Service接口
 *
 * @author procon
 * @date 2020-10-27
 */
public interface BlogResourceSortService
{
    /**
     * 查询资源分类
     *
     * @param id 资源分类ID
     * @return 资源分类
     */
    public BlogResourceSort selectBlogResourceSortById(Long id);

    /**
     * 查询资源分类列表
     *
     * @param blogResourceSort 资源分类
     * @return 资源分类集合
     */
    public List<BlogResourceSort> selectBlogResourceSortList(BlogResourceSort blogResourceSort);

    /**
     * 新增资源分类
     *
     * @param blogResourceSort 资源分类
     * @return 结果
     */
    public int insertBlogResourceSort(BlogResourceSort blogResourceSort);

    /**
     * 修改资源分类
     *
     * @param blogResourceSort 资源分类
     * @return 结果
     */
    public int updateBlogResourceSort(BlogResourceSort blogResourceSort);

    /**
     * 批量删除资源分类
     *
     * @param ids 需要删除的资源分类ID
     * @return 结果
     */
    public int deleteBlogResourceSortByIds(Long[] ids);

    /**
     * 删除资源分类信息
     *
     * @param id 资源分类ID
     * @return 结果
     */
    public int deleteBlogResourceSortById(Long id);
}

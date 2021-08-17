package com.huangpuguang.blog.service;

import com.huangpuguang.blog.domain.BlogPictureSort;

import java.util.List;

/**
 * 图片分类Service接口
 *
 * @author procon
 * @date 2020-10-27
 */
public interface BlogPictureSortService
{
    /**
     * 查询图片分类
     *
     * @param id 图片分类ID
     * @return 图片分类
     */
    public BlogPictureSort selectBlogPictureSortById(Long id);

    /**
     * 查询图片分类列表
     *
     * @param blogPictureSort 图片分类
     * @return 图片分类集合
     */
    public List<BlogPictureSort> selectBlogPictureSortList(BlogPictureSort blogPictureSort);

    /**
     * 新增图片分类
     *
     * @param blogPictureSort 图片分类
     * @return 结果
     */
    public int insertBlogPictureSort(BlogPictureSort blogPictureSort);

    /**
     * 修改图片分类
     *
     * @param blogPictureSort 图片分类
     * @return 结果
     */
    public int updateBlogPictureSort(BlogPictureSort blogPictureSort);

    /**
     * 批量删除图片分类
     *
     * @param ids 需要删除的图片分类ID
     * @return 结果
     */
    public int deleteBlogPictureSortByIds(Long[] ids);

    /**
     * 删除图片分类信息
     *
     * @param id 图片分类ID
     * @return 结果
     */
    public int deleteBlogPictureSortById(Long id);
}

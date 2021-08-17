package com.huangpuguang.blog.service;

import com.huangpuguang.blog.domain.BlogPicture;

import java.util.List;

/**
 * 图片Service接口
 *
 * @author procon
 * @date 2020-10-27
 */
public interface BlogPictureService
{
    /**
     * 查询图片
     *
     * @param id 图片ID
     * @return 图片
     */
    public BlogPicture selectBlogPictureById(Long id);

    /**
     * 查询图片列表
     *
     * @param blogPicture 图片
     * @return 图片集合
     */
    public List<BlogPicture> selectBlogPictureList(BlogPicture blogPicture);

    /**
     * 新增图片
     *
     * @param blogPicture 图片
     * @return 结果
     */
    public int insertBlogPicture(BlogPicture blogPicture);

    /**
     * 修改图片
     *
     * @param blogPicture 图片
     * @return 结果
     */
    public int updateBlogPicture(BlogPicture blogPicture);

    /**
     * 批量删除图片
     *
     * @param ids 需要删除的图片ID
     * @return 结果
     */
    public int deleteBlogPictureByIds(Long[] ids);

    /**
     * 删除图片信息
     *
     * @param id 图片ID
     * @return 结果
     */
    public int deleteBlogPictureById(Long id);
}

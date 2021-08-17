package com.huangpuguang.blog.mapper;

import com.huangpuguang.blog.domain.BlogPicture;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 图片Mapper接口
 *
 * @author procon
 * @date 2020-10-27
 */
@Repository
public interface BlogPictureMapper
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
     * 删除图片
     *
     * @param id 图片ID
     * @return 结果
     */
    public int deleteBlogPictureById(Long id);

    /**
     * 批量删除图片
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBlogPictureByIds(Long[] ids);
}

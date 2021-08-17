package com.huangpuguang.blog.mapper;

import com.huangpuguang.blog.domain.BlogContent;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 博客Mapper接口
 *
 * @author procon
 * @date 2020-10-27
 */
@Repository
public interface BlogContentMapper
{
    /**
     * 查询博客
     *
     * @param id 博客ID
     * @return 博客
     */

    public BlogContent selectBlogContentById(Long id);

    /**
     * 查询博客列表
     *
     * @param blogContent 博客
     * @return 博客集合
     */
    public List<BlogContent> selectBlogContentList(BlogContent blogContent);

    /**
     * 新增博客
     *
     * @param blogContent 博客
     * @return 结果
     */
    public int insertBlogContent(BlogContent blogContent);

    /**
     * 修改博客
     *
     * @param blogContent 博客
     * @return 结果
     */
    public int updateBlogContent(BlogContent blogContent);

    /**
     * 删除博客
     *
     * @param id 博客ID
     * @return 结果
     */
    public int deleteBlogContentById(Long id);

    /**
     * 批量删除博客
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBlogContentByIds(Long[] ids);

    /**
     * <p>根据ids查询</p>
     * @param ids  主键ids
     * @return java.util.List<com.huangpuguang.blog.domain.BlogContent>
     * @author procon
     * @time 2020/11/25 17:50
     */
    public List<BlogContent> selectBlogContentByIds(List<Long> ids);

    /**
     * 根据标签id查询
     * @param tagId
     * @return java.util.List<com.huangpuguang.blog.domain.BlogContent>
     */
    public List<BlogContent> selectBlogContentByTag(String tagId);
}

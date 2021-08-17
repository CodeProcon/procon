package com.huangpuguang.blog.mapper;

import com.huangpuguang.blog.domain.BlogTag;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 标签Mapper接口
 *
 * @author procon
 * @date 2020-10-27
 */
@Repository
public interface BlogTagMapper
{
    /**
     * 查询标签
     *
     * @param id 标签ID
     * @return 标签
     */

    public BlogTag selectBlogTagById(Long id);

    /**
     * 查询标签列表
     *
     * @param blogTag 标签
     * @return 标签集合
     */
    public List<BlogTag> selectBlogTagList(BlogTag blogTag);

    /**
     * 新增标签
     *
     * @param blogTag 标签
     * @return 结果
     */
    public int insertBlogTag(BlogTag blogTag);

    /**
     * 修改标签
     *
     * @param blogTag 标签
     * @return 结果
     */
    public int updateBlogTag(BlogTag blogTag);

    /**
     * 删除标签
     *
     * @param id 标签ID
     * @return 结果
     */
    public int deleteBlogTagById(Long id);

    /**
     * 批量删除标签
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBlogTagByIds(Long[] ids);

    /**
     * 根据id集合查询标签
     * @param ids
     * @return
     */
    public List<BlogTag> selectBlogTagByIds(List<Long> ids);
}

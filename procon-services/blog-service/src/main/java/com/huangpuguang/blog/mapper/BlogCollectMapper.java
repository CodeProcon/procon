package com.huangpuguang.blog.mapper;

import com.huangpuguang.blog.domain.BlogCollect;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 收藏Mapper接口
 *
 * @author procon
 * @date 2020-10-27
 */
@Repository
public interface BlogCollectMapper
{
    /**
     * 查询收藏
     *
     * @param id 收藏ID
     * @return 收藏
     */

    public BlogCollect selectBlogCollectById(Long id);

    /**
     * 查询收藏列表
     *
     * @param blogCollect 收藏
     * @return 收藏集合
     */
    public List<BlogCollect> selectBlogCollectList(BlogCollect blogCollect);

    /**
     * 新增收藏
     *
     * @param blogCollect 收藏
     * @return 结果
     */
    public int insertBlogCollect(BlogCollect blogCollect);

    /**
     * 修改收藏
     *
     * @param blogCollect 收藏
     * @return 结果
     */
    public int updateBlogCollect(BlogCollect blogCollect);

    /**
     * 删除收藏
     *
     * @param id 收藏ID
     * @return 结果
     */
    public int deleteBlogCollectById(Long id);

    /**
     * 批量删除收藏
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBlogCollectByIds(Long[] ids);
}

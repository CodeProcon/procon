package com.huangpuguang.blog.mapper;

import com.huangpuguang.blog.domain.BlogWebConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author procon
 * @date 2020-10-27
 */
@Repository
public interface BlogWebConfigMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */

    public BlogWebConfig selectBlogWebConfigById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param blogWebConfig 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<BlogWebConfig> selectBlogWebConfigList(BlogWebConfig blogWebConfig);

    /**
     * 新增【请填写功能名称】
     *
     * @param blogWebConfig 【请填写功能名称】
     * @return 结果
     */
    public int insertBlogWebConfig(BlogWebConfig blogWebConfig);

    /**
     * 修改【请填写功能名称】
     *
     * @param blogWebConfig 【请填写功能名称】
     * @return 结果
     */
    public int updateBlogWebConfig(BlogWebConfig blogWebConfig);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteBlogWebConfigById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBlogWebConfigByIds(Long[] ids);
}

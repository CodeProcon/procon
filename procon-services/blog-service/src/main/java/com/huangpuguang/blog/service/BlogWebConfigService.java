package com.huangpuguang.blog.service;

import com.huangpuguang.blog.domain.BlogWebConfig;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author procon
 * @date 2020-10-27
 */
public interface BlogWebConfigService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    BlogWebConfig selectBlogWebConfigById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param blogWebConfig 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    List<BlogWebConfig> selectBlogWebConfigList(BlogWebConfig blogWebConfig);

    /**
     * 新增【请填写功能名称】
     *
     * @param blogWebConfig 【请填写功能名称】
     * @return 结果
     */
    int insertBlogWebConfig(BlogWebConfig blogWebConfig);

    /**
     * 修改【请填写功能名称】
     *
     * @param blogWebConfig 【请填写功能名称】
     * @return 结果
     */
    int updateBlogWebConfig(BlogWebConfig blogWebConfig);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    int deleteBlogWebConfigByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    int deleteBlogWebConfigById(Long id);

    /**
     * 查询网站配置
     * @return 【博客列表】
     */
    BlogWebConfig selectBlogWebConfig();
}

package com.huangpuguang.blog.service;

import com.huangpuguang.blog.domain.BlogVisitor;

import java.util.List;

/**
 * 游客Service接口
 *
 * @author procon
 * @date 2020-10-27
 */
public interface BlogVisitorService
{
    /**
     * 查询游客
     *
     * @param id 游客ID
     * @return 游客
     */
    public BlogVisitor selectBlogVisitorById(Long id);

    /**
     * 查询游客列表
     *
     * @param blogVisitor 游客
     * @return 游客集合
     */
    public List<BlogVisitor> selectBlogVisitorList(BlogVisitor blogVisitor);

    /**
     * 新增游客
     *
     * @param blogVisitor 游客
     * @return 结果
     */
    public int insertBlogVisitor(BlogVisitor blogVisitor);

    /**
     * 修改游客
     *
     * @param blogVisitor 游客
     * @return 结果
     */
    public int updateBlogVisitor(BlogVisitor blogVisitor);

    /**
     * 批量删除游客
     *
     * @param ids 需要删除的游客ID
     * @return 结果
     */
    public int deleteBlogVisitorByIds(Long[] ids);

    /**
     * 删除游客信息
     *
     * @param id 游客ID
     * @return 结果
     */
    public int deleteBlogVisitorById(Long id);
}

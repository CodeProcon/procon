package com.huangpuguang.blog.service;

import com.huangpuguang.blog.domain.BlogLink;

import java.util.List;

/**
 * 友情链接Service接口
 *
 * @author procon
 * @date 2020-10-27
 */
public interface BlogLinkService
{
    /**
     * 查询友情链接
     *
     * @param id 友情链接ID
     * @return 友情链接
     */
    public BlogLink selectBlogLinkById(Long id);

    /**
     * 查询友情链接列表
     *
     * @param blogLink 友情链接
     * @return 友情链接集合
     */
    public List<BlogLink> selectBlogLinkList(BlogLink blogLink);

    /**
     * 新增友情链接
     *
     * @param blogLink 友情链接
     * @return 结果
     */
    public int insertBlogLink(BlogLink blogLink);

    /**
     * 修改友情链接
     *
     * @param blogLink 友情链接
     * @return 结果
     */
    public int updateBlogLink(BlogLink blogLink);

    /**
     * 批量删除友情链接
     *
     * @param ids 需要删除的友情链接ID
     * @return 结果
     */
    public int deleteBlogLinkByIds(Long[] ids);

    /**
     * 删除友情链接信息
     *
     * @param id 友情链接ID
     * @return 结果
     */
    public int deleteBlogLinkById(Long id);
}

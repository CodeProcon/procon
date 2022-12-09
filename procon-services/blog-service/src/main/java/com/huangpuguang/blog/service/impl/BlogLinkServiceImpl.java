package com.huangpuguang.blog.service.impl;

import com.huangpuguang.blog.domain.BlogLink;
import com.huangpuguang.blog.mapper.BlogLinkMapper;
import com.huangpuguang.blog.service.BlogLinkService;
import com.huangpuguang.common.core.utils.ProconDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友情链接Service业务层处理
 *
 * @author procon
 * @date 2020-10-27
 */
@Service
public class BlogLinkServiceImpl implements BlogLinkService
{
    @Autowired
    private BlogLinkMapper blogLinkMapper;

    /**
     * 查询友情链接
     *
     * @param id 友情链接ID
     * @return 友情链接
     */
    @Override
    public BlogLink selectBlogLinkById(Long id)
    {
        return blogLinkMapper.selectBlogLinkById(id);
    }

    /**
     * 查询友情链接列表
     *
     * @param blogLink 友情链接
     * @return 友情链接
     */
    @Override
    public List<BlogLink> selectBlogLinkList(BlogLink blogLink)
    {
        return blogLinkMapper.selectBlogLinkList(blogLink);
    }

    /**
     * 新增友情链接
     *
     * @param blogLink 友情链接
     * @return 结果
     */
    @Override
    public int insertBlogLink(BlogLink blogLink)
    {
        blogLink.setCreateTime(ProconDateUtils.getNowDate());
        return blogLinkMapper.insertBlogLink(blogLink);
    }

    /**
     * 修改友情链接
     *
     * @param blogLink 友情链接
     * @return 结果
     */
    @Override
    public int updateBlogLink(BlogLink blogLink)
    {
        blogLink.setUpdateTime(ProconDateUtils.getNowDate());
        return blogLinkMapper.updateBlogLink(blogLink);
    }

    /**
     * 批量删除友情链接
     *
     * @param ids 需要删除的友情链接ID
     * @return 结果
     */
    @Override
    public int deleteBlogLinkByIds(Long[] ids)
    {
        return blogLinkMapper.deleteBlogLinkByIds(ids);
    }

    /**
     * 删除友情链接信息
     *
     * @param id 友情链接ID
     * @return 结果
     */
    @Override
    public int deleteBlogLinkById(Long id)
    {
        return blogLinkMapper.deleteBlogLinkById(id);
    }
}

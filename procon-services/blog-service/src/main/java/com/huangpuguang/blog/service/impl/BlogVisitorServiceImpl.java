package com.huangpuguang.blog.service.impl;

import com.huangpuguang.blog.domain.BlogVisitor;
import com.huangpuguang.blog.mapper.BlogVisitorMapper;
import com.huangpuguang.blog.service.BlogVisitorService;
import com.huangpuguang.common.core.utils.ProconDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 游客Service业务层处理
 *
 * @author procon
 * @date 2020-10-27
 */
@Service
public class BlogVisitorServiceImpl implements BlogVisitorService
{
    @Autowired
    private BlogVisitorMapper blogVisitorMapper;

    /**
     * 查询游客
     *
     * @param id 游客ID
     * @return 游客
     */
    @Override
    public BlogVisitor selectBlogVisitorById(Long id)
    {
        return blogVisitorMapper.selectBlogVisitorById(id);
    }

    /**
     * 查询游客列表
     *
     * @param blogVisitor 游客
     * @return 游客
     */
    @Override
    public List<BlogVisitor> selectBlogVisitorList(BlogVisitor blogVisitor)
    {
        return blogVisitorMapper.selectBlogVisitorList(blogVisitor);
    }

    /**
     * 新增游客
     *
     * @param blogVisitor 游客
     * @return 结果
     */
    @Override
    public int insertBlogVisitor(BlogVisitor blogVisitor)
    {
        blogVisitor.setCreateTime(ProconDateUtils.getNowDate());
        return blogVisitorMapper.insertBlogVisitor(blogVisitor);
    }

    /**
     * 修改游客
     *
     * @param blogVisitor 游客
     * @return 结果
     */
    @Override
    public int updateBlogVisitor(BlogVisitor blogVisitor)
    {
        blogVisitor.setUpdateTime(ProconDateUtils.getNowDate());
        return blogVisitorMapper.updateBlogVisitor(blogVisitor);
    }

    /**
     * 批量删除游客
     *
     * @param ids 需要删除的游客ID
     * @return 结果
     */
    @Override
    public int deleteBlogVisitorByIds(Long[] ids)
    {
        return blogVisitorMapper.deleteBlogVisitorByIds(ids);
    }

    /**
     * 删除游客信息
     *
     * @param id 游客ID
     * @return 结果
     */
    @Override
    public int deleteBlogVisitorById(Long id)
    {
        return blogVisitorMapper.deleteBlogVisitorById(id);
    }
}

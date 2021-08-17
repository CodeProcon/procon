package com.huangpuguang.blog.service.impl;

import com.huangpuguang.blog.domain.BlogWebVisit;
import com.huangpuguang.blog.mapper.BlogWebVisitMapper;
import com.huangpuguang.blog.service.BlogWebVisitService;
import com.huangpuguang.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Web访问记录Service业务层处理
 *
 * @author procon
 * @date 2020-10-27
 */
@Service
public class BlogWebVisitServiceImpl implements BlogWebVisitService
{
    @Autowired
    private BlogWebVisitMapper blogWebVisitMapper;

    /**
     * 查询Web访问记录
     *
     * @param id Web访问记录ID
     * @return Web访问记录
     */
    @Override
    public BlogWebVisit selectBlogWebVisitById(Long id)
    {
        return blogWebVisitMapper.selectBlogWebVisitById(id);
    }

    /**
     * 查询Web访问记录列表
     *
     * @param blogWebVisit Web访问记录
     * @return Web访问记录
     */
    @Override
    public List<BlogWebVisit> selectBlogWebVisitList(BlogWebVisit blogWebVisit)
    {
        return blogWebVisitMapper.selectBlogWebVisitList(blogWebVisit);
    }

    /**
     * 新增Web访问记录
     *
     * @param blogWebVisit Web访问记录
     * @return 结果
     */
    @Override
    public int insertBlogWebVisit(BlogWebVisit blogWebVisit)
    {
        blogWebVisit.setCreateTime(DateUtils.getNowDate());
        return blogWebVisitMapper.insertBlogWebVisit(blogWebVisit);
    }

    /**
     * 修改Web访问记录
     *
     * @param blogWebVisit Web访问记录
     * @return 结果
     */
    @Override
    public int updateBlogWebVisit(BlogWebVisit blogWebVisit)
    {
        blogWebVisit.setUpdateTime(DateUtils.getNowDate());
        return blogWebVisitMapper.updateBlogWebVisit(blogWebVisit);
    }

    /**
     * 批量删除Web访问记录
     *
     * @param ids 需要删除的Web访问记录ID
     * @return 结果
     */
    @Override
    public int deleteBlogWebVisitByIds(Long[] ids)
    {
        return blogWebVisitMapper.deleteBlogWebVisitByIds(ids);
    }

    /**
     * 删除Web访问记录信息
     *
     * @param id Web访问记录ID
     * @return 结果
     */
    @Override
    public int deleteBlogWebVisitById(Long id)
    {
        return blogWebVisitMapper.deleteBlogWebVisitById(id);
    }
}

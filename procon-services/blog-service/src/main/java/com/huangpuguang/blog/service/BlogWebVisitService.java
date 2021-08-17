package com.huangpuguang.blog.service;

import com.huangpuguang.blog.domain.BlogWebVisit;

import java.util.List;

/**
 * Web访问记录Service接口
 *
 * @author procon
 * @date 2020-10-27
 */
public interface BlogWebVisitService
{
    /**
     * 查询Web访问记录
     *
     * @param id Web访问记录ID
     * @return Web访问记录
     */
    public BlogWebVisit selectBlogWebVisitById(Long id);

    /**
     * 查询Web访问记录列表
     *
     * @param blogWebVisit Web访问记录
     * @return Web访问记录集合
     */
    public List<BlogWebVisit> selectBlogWebVisitList(BlogWebVisit blogWebVisit);

    /**
     * 新增Web访问记录
     *
     * @param blogWebVisit Web访问记录
     * @return 结果
     */
    public int insertBlogWebVisit(BlogWebVisit blogWebVisit);

    /**
     * 修改Web访问记录
     *
     * @param blogWebVisit Web访问记录
     * @return 结果
     */
    public int updateBlogWebVisit(BlogWebVisit blogWebVisit);

    /**
     * 批量删除Web访问记录
     *
     * @param ids 需要删除的Web访问记录ID
     * @return 结果
     */
    public int deleteBlogWebVisitByIds(Long[] ids);

    /**
     * 删除Web访问记录信息
     *
     * @param id Web访问记录ID
     * @return 结果
     */
    public int deleteBlogWebVisitById(Long id);
}

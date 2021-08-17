package com.huangpuguang.blog.mapper;

import com.huangpuguang.blog.domain.BlogWebVisit;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Web访问记录Mapper接口
 *
 * @author procon
 * @date 2020-10-27
 */
@Repository
public interface BlogWebVisitMapper
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
     * 删除Web访问记录
     *
     * @param id Web访问记录ID
     * @return 结果
     */
    public int deleteBlogWebVisitById(Long id);

    /**
     * 批量删除Web访问记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBlogWebVisitByIds(Long[] ids);
}

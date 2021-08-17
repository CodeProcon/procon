package com.huangpuguang.blog.service;

import com.huangpuguang.blog.domain.BlogContent;
import com.huangpuguang.blog.vo.BlogContentVo;
import com.huangpuguang.common.core.web.page.TableDataInfo;

import java.util.List;
import java.util.Set;

/**
 * 博客Service接口
 *
 * @author procon
 * @date 2020-10-27
 */
public interface BlogContentService
{
    /**
     * 查询博客
     *
     * @param id 博客ID
     * @return 博客
     */
    public BlogContentVo selectBlogContentById(Long id);

    /**
     * 查询博客列表
     *
     * @param blogContent 博客
     * @param flag 标签查询时候使用
     * @return 博客集合
     */
    public List<BlogContentVo> selectBlogContentList(BlogContent blogContent,Integer flag);

    /**
     * 新增博客
     *
     * @param blogContent 博客
     * @return 结果
     */
    public int insertBlogContent(BlogContent blogContent);

    /**
     * 修改博客
     *
     * @param blogContent 博客
     * @return 结果
     */
    public int updateBlogContent(BlogContent blogContent);

    /**
     * 批量删除博客
     *
     * @param ids 需要删除的博客ID
     * @return 结果
     */
    public int deleteBlogContentByIds(Long[] ids);

    /**
     * 删除博客信息
     *
     * @param id 博客ID
     * @return 结果
     */
    public int deleteBlogContentById(Long id);

    /**
     * <p>批量更新博客</p>
     * @param blogVOList
     * @return java.lang.String
     * @author procon
     * @time 2020/11/25 17:36
     */
    public String editBatch(List<BlogContentVo> blogVOList);

    /**
     * 获取博客点赞数
     * @param id 博客id
     * @return java.lang.Integer
     */
    public Integer getBlogPraiseCountById(Long id);

    /**
     * 通过博客id给博客点赞
     * @param id 博客id
     * @return java.lang.Integer
     */
    public Long praiseBlogById(Long id);


    /**
     * 获取相似博客
     * @param id 博客id
     * @return java.util.List<com.huangpuguang.blog.vo.BlogContentVo>
     */
    public TableDataInfo getSameBlogByBlogId(Long id);


    /**
     * 获取归档时间
     * @return java.util.Set<java.lang.String>
     */
    public Set<String> getBlogTimeSortList();

    /**
     * 通过月份获取日期
     *
     * @param monthDate 月份
     * @return 博客列表
     */
    public List<BlogContentVo> getArticleByMonth(String monthDate);


}

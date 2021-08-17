package com.huangpuguang.blog.service.impl;

import com.huangpuguang.blog.domain.BlogCollect;
import com.huangpuguang.blog.mapper.BlogCollectMapper;
import com.huangpuguang.blog.service.BlogCollectService;
import com.huangpuguang.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收藏Service业务层处理
 *
 * @author procon
 * @date 2020-10-27
 */
@Service
public class BlogCollectServiceImpl implements BlogCollectService
{
    @Autowired
    private BlogCollectMapper blogCollectMapper;

    /**
     * 查询收藏
     *
     * @param id 收藏ID
     * @return 收藏
     */
    @Override
    public BlogCollect selectBlogCollectById(Long id)
    {
        return blogCollectMapper.selectBlogCollectById(id);
    }

    /**
     * 查询收藏列表
     *
     * @param blogCollect 收藏
     * @return 收藏
     */
    @Override
    public List<BlogCollect> selectBlogCollectList(BlogCollect blogCollect)
    {
        return blogCollectMapper.selectBlogCollectList(blogCollect);
    }

    /**
     * 新增收藏
     *
     * @param blogCollect 收藏
     * @return 结果
     */
    @Override
    public int insertBlogCollect(BlogCollect blogCollect)
    {
        blogCollect.setCreateTime(DateUtils.getNowDate());
        return blogCollectMapper.insertBlogCollect(blogCollect);
    }

    /**
     * 修改收藏
     *
     * @param blogCollect 收藏
     * @return 结果
     */
    @Override
    public int updateBlogCollect(BlogCollect blogCollect)
    {
        blogCollect.setUpdateTime(DateUtils.getNowDate());
        return blogCollectMapper.updateBlogCollect(blogCollect);
    }

    /**
     * 批量删除收藏
     *
     * @param ids 需要删除的收藏ID
     * @return 结果
     */
    @Override
    public int deleteBlogCollectByIds(Long[] ids)
    {
        return blogCollectMapper.deleteBlogCollectByIds(ids);
    }

    /**
     * 删除收藏信息
     *
     * @param id 收藏ID
     * @return 结果
     */
    @Override
    public int deleteBlogCollectById(Long id)
    {
        return blogCollectMapper.deleteBlogCollectById(id);
    }
}

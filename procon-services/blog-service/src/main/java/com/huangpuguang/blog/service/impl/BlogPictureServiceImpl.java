package com.huangpuguang.blog.service.impl;

import com.huangpuguang.blog.domain.BlogPicture;
import com.huangpuguang.blog.mapper.BlogPictureMapper;
import com.huangpuguang.blog.service.BlogPictureService;
import com.huangpuguang.common.core.constant.Constants;
import com.huangpuguang.common.core.utils.ProconDateUtils;
import com.huangpuguang.system.api.RemoteFileService;
import com.huangpuguang.system.api.domain.ProconFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 图片Service业务层处理
 *
 * @author procon
 * @date 2020-10-27
 */
@Service
public class BlogPictureServiceImpl implements BlogPictureService
{
    @Autowired
    private BlogPictureMapper blogPictureMapper;

    @Autowired
    private RemoteFileService remoteFileService;

    /**
     * 查询图片
     *
     * @param id 图片ID
     * @return 图片
     */
    @Override
    public BlogPicture selectBlogPictureById(Long id)
    {
        return blogPictureMapper.selectBlogPictureById(id);
    }

    /**
     * 查询图片列表
     *
     * @param blogPicture 图片
     * @return 图片
     */
    @Override
    public List<BlogPicture> selectBlogPictureList(BlogPicture blogPicture)
    {
        List<BlogPicture> blogPictureList = blogPictureMapper.selectBlogPictureList(blogPicture);
        StringBuilder sb = new StringBuilder();
        blogPictureList.forEach(item -> sb.append(item.getFileId()).append(Constants.FILE_SEGMENTATION));
        List<ProconFile> fileList = remoteFileService.getInfos(sb.toString(), Constants.FILE_SEGMENTATION);

        blogPictureList.forEach(item -> fileList.forEach(item2 ->{
            if(item.getFileId().equals(item2.getId())){
                item.setFileUrl(item2.getFileUrl());
            }
        }));

        return blogPictureList;
    }

    /**
     * 新增图片
     *
     * @param blogPicture 图片
     * @return 结果
     */
    @Override
    public int insertBlogPicture(BlogPicture blogPicture)
    {
        blogPicture.setCreateTime(ProconDateUtils.getNowDate());
        return blogPictureMapper.insertBlogPicture(blogPicture);
    }

    /**
     * 修改图片
     *
     * @param blogPicture 图片
     * @return 结果
     */
    @Override
    public int updateBlogPicture(BlogPicture blogPicture)
    {
        blogPicture.setUpdateTime(ProconDateUtils.getNowDate());
        return blogPictureMapper.updateBlogPicture(blogPicture);
    }

    /**
     * 批量删除图片
     *
     * @param ids 需要删除的图片ID
     * @return 结果
     */
    @Override
    public int deleteBlogPictureByIds(Long[] ids)
    {
        return blogPictureMapper.deleteBlogPictureByIds(ids);
    }

    /**
     * 删除图片信息
     *
     * @param id 图片ID
     * @return 结果
     */
    @Override
    public int deleteBlogPictureById(Long id)
    {
        return blogPictureMapper.deleteBlogPictureById(id);
    }
}

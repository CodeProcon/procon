package com.huangpuguang.blog.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.huangpuguang.blog.domain.BlogPicture;
import com.huangpuguang.blog.domain.BlogPictureSort;
import com.huangpuguang.blog.mapper.BlogPictureMapper;
import com.huangpuguang.blog.mapper.BlogPictureSortMapper;
import com.huangpuguang.blog.service.BlogPictureSortService;
import com.huangpuguang.common.core.constant.Constants;
import com.huangpuguang.common.core.utils.ProconDateUtils;
import com.huangpuguang.system.api.RemoteFileService;
import com.huangpuguang.system.api.domain.ProconFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 图片分类Service业务层处理
 *
 * @author procon
 * @date 2020-10-27
 */
@Service
public class BlogPictureSortServiceImpl implements BlogPictureSortService
{
    @Autowired
    private BlogPictureSortMapper blogPictureSortMapper;

    @Autowired
    private BlogPictureMapper blogPictureMapper;

    @Autowired
    private RemoteFileService remoteFileService;

    /**
     * 查询图片分类
     *
     * @param id 图片分类ID
     * @return 图片分类
     */
    @Override
    public BlogPictureSort selectBlogPictureSortById(Long id)
    {
        return blogPictureSortMapper.selectBlogPictureSortById(id);
    }

    /**
     * 查询图片分类列表
     *
     * @param blogPictureSort 图片分类
     * @return 图片分类
     */
    @Override
    public List<BlogPictureSort> selectBlogPictureSortList(BlogPictureSort blogPictureSort)
    {
        List<BlogPictureSort> blogPictureSortList = blogPictureSortMapper.selectBlogPictureSortList(blogPictureSort);

        if(CollUtil.isNotEmpty(blogPictureSortList)){
            blogPictureSortList.forEach(item->{
                BlogPicture blogPicture = new BlogPicture();
                blogPicture.setPictureSortId(item.getId());
                List<BlogPicture> blogPictures= blogPictureMapper.selectBlogPictureList(blogPicture);
                StringBuilder fileIds = new StringBuilder();
                blogPictures.forEach(item2->{
                    fileIds.append(item2.getFileId()).append(Constants.FILE_SEGMENTATION);
                    List<ProconFile> fileList = remoteFileService.getInfos(fileIds.toString(), Constants.FILE_SEGMENTATION);
                    item.setPhotoList(fileList);
                });
            });
        }
        return blogPictureSortList;
    }

    /**
     * 新增图片分类
     *
     * @param blogPictureSort 图片分类
     * @return 结果
     */
    @Override
    public int insertBlogPictureSort(BlogPictureSort blogPictureSort)
    {
        blogPictureSort.setCreateTime(ProconDateUtils.getNowDate());
        return blogPictureSortMapper.insertBlogPictureSort(blogPictureSort);
    }

    /**
     * 修改图片分类
     *
     * @param blogPictureSort 图片分类
     * @return 结果
     */
    @Override
    public int updateBlogPictureSort(BlogPictureSort blogPictureSort)
    {
        blogPictureSort.setUpdateTime(ProconDateUtils.getNowDate());
        return blogPictureSortMapper.updateBlogPictureSort(blogPictureSort);
    }

    /**
     * 批量删除图片分类
     *
     * @param ids 需要删除的图片分类ID
     * @return 结果
     */
    @Override
    public int deleteBlogPictureSortByIds(Long[] ids)
    {
        return blogPictureSortMapper.deleteBlogPictureSortByIds(ids);
    }

    /**
     * 删除图片分类信息
     *
     * @param id 图片分类ID
     * @return 结果
     */
    @Override
    public int deleteBlogPictureSortById(Long id)
    {
        return blogPictureSortMapper.deleteBlogPictureSortById(id);
    }
}

package com.huangpuguang.blog.service.impl;

import com.huangpuguang.blog.domain.BlogWebConfig;
import com.huangpuguang.blog.mapper.BlogWebConfigMapper;
import com.huangpuguang.blog.service.BlogWebConfigService;
import com.huangpuguang.common.core.constant.Constants;
import com.huangpuguang.common.core.utils.ProconDateUtils;
import com.huangpuguang.system.api.RemoteFileService;
import com.huangpuguang.system.api.domain.ProconFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author procon
 * @date 2020-10-27
 */
@Service
public class BlogWebConfigServiceImpl implements BlogWebConfigService
{
    @Autowired
    private BlogWebConfigMapper blogWebConfigMapper;
    @Autowired
    private RemoteFileService remoteFileService;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public BlogWebConfig selectBlogWebConfigById(Long id)
    {
        return blogWebConfigMapper.selectBlogWebConfigById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param blogWebConfig 【查询配置列表】
     * @return 【请填写功能名称】
     */
    @Override
    public List<BlogWebConfig> selectBlogWebConfigList(BlogWebConfig blogWebConfig)
    {
        List<BlogWebConfig> webConfigs = blogWebConfigMapper.selectBlogWebConfigList(blogWebConfig);
        StringBuilder fileIds = new StringBuilder();
        webConfigs.forEach(item -> {
            fileIds.append(item.getLogo()).append(Constants.FILE_SEGMENTATION);
            fileIds.append(item.getWeixinPay()).append(Constants.FILE_SEGMENTATION);
            fileIds.append(item.getAliPay()).append(Constants.FILE_SEGMENTATION);
        });
        List<ProconFile> fileList  = remoteFileService.getInfos(fileIds.toString(), Constants.FILE_SEGMENTATION);
        List<String> photoUrl = new ArrayList<>();
        webConfigs.forEach(item ->{
            List<ProconFile> fileUrl = fileList.stream().filter(item2 -> item2.getId().toString().equals(item.getLogo())).collect(Collectors.toList());
            List<ProconFile> aliPayPhotoList = fileList.stream().filter(item2 -> item2.getId().toString().equals(item.getAliPay())).collect(Collectors.toList());
            List<ProconFile> weChatPhotoList = fileList.stream().filter(item2 -> item2.getId().toString().equals(item.getWeixinPay())).collect(Collectors.toList());
            fileUrl.forEach(file ->photoUrl.add(file.getFileUrl()));
            aliPayPhotoList.forEach(ali ->item.setAliPayPhoto(ali.getFileUrl()));
            weChatPhotoList.forEach(wechat ->item.setWeChatPayPhoto(wechat.getFileUrl()));
            item.setPhotoList(photoUrl);
        });

        return webConfigs;
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param blogWebConfig 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertBlogWebConfig(BlogWebConfig blogWebConfig)
    {
        blogWebConfig.setCreateTime(ProconDateUtils.getNowDate());
        return blogWebConfigMapper.insertBlogWebConfig(blogWebConfig);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param blogWebConfig 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateBlogWebConfig(BlogWebConfig blogWebConfig)
    {
        blogWebConfig.setUpdateTime(ProconDateUtils.getNowDate());
        return blogWebConfigMapper.updateBlogWebConfig(blogWebConfig);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteBlogWebConfigByIds(Long[] ids)
    {
        return blogWebConfigMapper.deleteBlogWebConfigByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteBlogWebConfigById(Long id)
    {
        return blogWebConfigMapper.deleteBlogWebConfigById(id);
    }


    /**
     * 查询配置
     *
     * @return webConfig
     */
    @Override
    public BlogWebConfig selectBlogWebConfig()
    {
        BlogWebConfig webConfig = new BlogWebConfig();
        List<BlogWebConfig> webConfigs = selectBlogWebConfigList(webConfig);
        if(!CollectionUtils.isEmpty(webConfigs)){
            webConfig = webConfigs.get(0);
        }
        return webConfig;
    }
}

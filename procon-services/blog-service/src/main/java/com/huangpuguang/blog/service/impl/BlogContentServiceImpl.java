package com.huangpuguang.blog.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.huangpuguang.blog.domain.BlogComment;
import com.huangpuguang.blog.domain.BlogContent;
import com.huangpuguang.blog.domain.BlogSort;
import com.huangpuguang.blog.domain.BlogTag;
import com.huangpuguang.blog.emun.ECommentSource;
import com.huangpuguang.blog.emun.EStatus;
import com.huangpuguang.blog.mapper.BlogCommentMapper;
import com.huangpuguang.blog.mapper.BlogContentMapper;
import com.huangpuguang.blog.mapper.BlogSortMapper;
import com.huangpuguang.blog.mapper.BlogTagMapper;
import com.huangpuguang.blog.service.BlogContentService;
import com.huangpuguang.blog.vo.BlogContentVo;
import com.huangpuguang.common.core.constant.BlogConstants;
import com.huangpuguang.common.core.constant.Constants;
import com.huangpuguang.common.core.constant.HttpStatus;
import com.huangpuguang.common.core.utils.ProconDateUtils;
import com.huangpuguang.common.core.utils.JsonUtils;
import com.huangpuguang.common.core.utils.ProconStrUtils;
import com.huangpuguang.common.core.web.page.PageOper;
import com.huangpuguang.common.core.web.page.TableDataInfo;
import com.huangpuguang.common.redis.service.RedisService;
import com.huangpuguang.common.security.utils.SecurityUtils;
import com.huangpuguang.system.api.RemoteFileService;
import com.huangpuguang.system.api.domain.ProconFile;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 博客Service业务层处理
 *
 * @author procon
 * @date 2020-10-27
 */
@Service
public class BlogContentServiceImpl extends PageOper<BlogContent> implements BlogContentService
{

    private static final Logger log = LoggerFactory.getLogger(BlogContentServiceImpl.class);
    private final BlogContentMapper blogContentMapper;
    private final BlogSortMapper blogSortMapper;
    private final BlogTagMapper blogTagMapper;
    private final RemoteFileService remoteFileService;
    private final RedisService redisService;
    private final BlogCommentMapper blogCommentMapper;
    @Autowired
    public BlogContentServiceImpl(BlogContentMapper blogContentMapper, BlogSortMapper blogSortMapper, BlogTagMapper blogTagMapper, RemoteFileService remoteFileService, RedisService redisService, BlogCommentMapper blogCommentMapper) {
        this.blogContentMapper = blogContentMapper;
        this.blogSortMapper = blogSortMapper;
        this.blogTagMapper = blogTagMapper;
        this.remoteFileService = remoteFileService;
        this.redisService = redisService;
        this.blogCommentMapper = blogCommentMapper;
    }




    /**
     * 查询博客
     *
     * @param id 博客ID
     * @return 博客
     */
    @Override
    public BlogContentVo selectBlogContentById(Long id)

    {
        BlogContent content = blogContentMapper.selectBlogContentById(id);
        BlogContentVo blogContentVo = new BlogContentVo();

        BeanUtils.copyProperties(content, blogContentVo);
        //查询博客标签
        List<Long> tagIds = new ArrayList<>();
        String tagUid = content.getTagId();
        String[] splitUid = tagUid.split(Constants.FILE_SEGMENTATION);
        for (String s : splitUid) {
            tagIds.add(Long.valueOf(s));
        }
        List<BlogTag> blogTags = blogTagMapper.selectBlogTagByIds(tagIds);
        blogContentVo.setTagList(blogTags);
        //查询博客分类
        List<BlogSort> blogSorts = new ArrayList<>();
        BlogSort blogSort = blogSortMapper.selectBlogSortById(content.getBlogSortId());
        blogSorts.add(blogSort);
        blogContentVo.setBlogSortList(blogSorts);
        blogContentVo.setBlogSort(blogSort);
        //查询博客标题图
        ProconFile file = remoteFileService.getInfo(content.getFileId());
        if(ObjectUtil.isNotNull(file)){
            List<String> fileUrls = new ArrayList<>();
            String fileUrl = file.getFileUrl();
            fileUrls.add(fileUrl);
            blogContentVo.setPhotoList(fileUrls);
        }
        return blogContentVo;
    }

    /**
     * 查询博客列表
     *
     * @param blogContent 博客
     * @return 博客
     */
    @Override
    public List<BlogContentVo> selectBlogContentList(BlogContent blogContent,Integer flag)
    {
        List<BlogContent> blogContentList;
        if(flag != null){
            blogContentList = blogContentMapper.selectBlogContentByTag(blogContent.getTagId());
        }else{
            blogContentList = blogContentMapper.selectBlogContentList(blogContent);
        }


        List<BlogContentVo> blogContentVoList = new ArrayList<>();
        StringBuffer fileIds = new StringBuffer();
        List<Long> sortIds = new ArrayList<>();
        List<Long> tagIds = new ArrayList<>();
        if(CollUtil.isEmpty(blogContentList)){
            return blogContentVoList;
        }
        //List copy
        blogContentVoList = blogContentList.stream().map(
                e->{
                    BlogContentVo blogContentVo = new BlogContentVo();
                    BeanUtils.copyProperties(e, blogContentVo);
                    return blogContentVo;
                }).collect(Collectors.toList());

        //列表遍历
        blogContentVoList.forEach(item ->{
            if(ObjectUtil.isNotNull(item.getFileId())){
                fileIds.append(item.getFileId() + Constants.FILE_SEGMENTATION);
            }
            if(ObjectUtil.isNotNull(item.getBlogSortId())){
                sortIds.add(item.getBlogSortId());
            }
            if(ObjectUtil.isNotNull(item.getTagId())){
                String tagUid = item.getTagId();
                String[] splitUid = tagUid.split(Constants.FILE_SEGMENTATION);
                for(int i = 0;i<splitUid.length;i++){
                    tagIds.add(Long.valueOf(splitUid[i]));
                }

            }
        });
        //查询博客分类列表
        List<BlogSort> sortList = blogSortMapper.selectBlogSortByIds(sortIds);
        //查询博客标签列表
        List<BlogTag> blogTagList = blogTagMapper.selectBlogTagByIds(tagIds);
        // 查询图片列表
        List<ProconFile> fileList = remoteFileService.getInfos(fileIds.toString(), Constants.FILE_SEGMENTATION);


        for(BlogContentVo blogContentVo:blogContentVoList){
            if(ObjectUtil.isNotNull(blogContentVo.getTagId())) {
                //设置分类列表
                List<BlogSort> blogSortList = new ArrayList<>();
                sortList.forEach(item -> {
                    if(item.getId().equals(blogContentVo.getBlogSortId())){
                        blogSortList.add(item);
                    }
                });
                blogContentVo.setBlogSortList(blogSortList);
            }
            //设置标签列表
            if(ObjectUtil.isNotNull(blogContentVo.getTagId())) {
                List<BlogTag> blogTags = new ArrayList<>();
                blogTagList.forEach(item -> {
                    String tagUid = blogContentVo.getTagId();
                    String[] splitUid = tagUid.split(Constants.FILE_SEGMENTATION);
                    for (int i = 0;  i<splitUid.length; i++) {
                        if(item.getId().equals(Long.valueOf(splitUid[i]))){
                            blogTags.add(item);
                        }
                    }
                });
                blogContentVo.setTagList(blogTags);
            }

            //设置图片列表
            if(ObjectUtil.isNotNull(blogContentVo.getFileId())){
                if(CollUtil.isNotEmpty(fileList)){

                    List<String> photoUrl = new ArrayList<>();
                    fileList.forEach(item ->{
                        long id = item.getId();
                        Long fileId =id;
                        if(fileId.equals(blogContentVo.getFileId())){
                            photoUrl.add(item.getFileUrl());
                        }
                    });

                    blogContentVo.setPhotoList(photoUrl);
                }

            }
        }

        return blogContentVoList;
    }

    /**
     * 新增博客
     *
     * @param blogContent 博客
     * @return 结果
     */
    @Override
    public int insertBlogContent(BlogContent blogContent)
    {
        blogContent.setCreateTime(ProconDateUtils.getNowDate());
        return blogContentMapper.insertBlogContent(blogContent);
    }

    /**
     * 修改博客
     *
     * @param blogContent 博客
     * @return 结果
     */
    @Override
    public int updateBlogContent(BlogContent blogContent)
    {
        blogContent.setUpdateTime(ProconDateUtils.getNowDate());
        return blogContentMapper.updateBlogContent(blogContent);
    }

    /**
     * 批量删除博客
     *
     * @param ids 需要删除的博客ID
     * @return 结果
     */
    @Override
    public int deleteBlogContentByIds(Long[] ids)
    {
        return blogContentMapper.deleteBlogContentByIds(ids);
    }

    /**
     * 删除博客信息
     *
     * @param id 博客ID
     * @return 结果
     */
    @Override
    public int deleteBlogContentById(Long id)
    {
        return blogContentMapper.deleteBlogContentById(id);
    }

    @Override
    public String editBatch(List<BlogContentVo> blogVOList) {
        if (blogVOList.size() <= 0) {
            return "参数错误";
        }
        List<Long> blogIdList = new ArrayList<>();
        Map<Long, BlogContentVo> blogVOMap = new HashMap<>(16);
        blogVOList.forEach(item -> {
            blogIdList.add(item.getId());
            blogVOMap.put(item.getId(), item);
        });
        Collection<BlogContent> blogList = blogContentMapper.selectBlogContentByIds(blogIdList);
        blogList.forEach(blog -> {
            BlogContentVo blogVO = blogVOMap.get(blog.getId());
            if (blogVO != null) {
                blog.setAuthor(blogVO.getAuthor());
                blog.setArticlesPart(blogVO.getArticlesPart());
                blog.setTitle(blogVO.getTitle());
                blog.setSummary(blogVO.getSummary());
                blog.setContent(blogVO.getContent());
                blog.setTagId(blogVO.getTagId());
                blog.setBlogSortId(blogVO.getBlogSortId());
                blog.setFileId(blogVO.getFileId());
                blog.setLevel(blogVO.getLevel());
                blog.setIsOriginal(blogVO.getIsOriginal());
                blog.setIsPublish(blogVO.getIsPublish());
                blog.setSort(blogVO.getSort());
                blog.setStatus(BlogConstants.ENABLE);
                blogContentMapper.updateBlogContent(blog);
            }
        });
        // TODO: 2020/11/25 保存成功后，需要发送消息到es 和 redis
        return "更新成功！";
    }

    /**
     * 获取博客点赞数
     * @param id 博客id
     * @return java.lang.Integer
     */
    @Override
    public Integer getBlogPraiseCountById(Long id) {
        int praiseCount = 0;
        if (id == null) {
            log.error("传入的ID为空");
            return praiseCount;
        }
        //从Redis取出用户点赞数据
        String praiseJsonResult = redisService.getCacheObject(BlogConstants.BLOG_PRAISE + BlogConstants.SEGMENTATION + id);
        if (!StringUtils.isEmpty(praiseJsonResult)) {
            praiseCount = Integer.parseInt(praiseJsonResult);
        }
        return praiseCount;

    }

    @Override
    public Long praiseBlogById(Long id) {
        // 如果用户登录了
        if (SecurityUtils.getUserId() != null) {
            Long userId = SecurityUtils.getUserId();
            BlogComment comment = new BlogComment();
            comment.setCommentType(BlogConstants.PRAISE);
            comment.setUserId(userId);
            comment.setBlogId(id);
            List<BlogComment> commentList = blogCommentMapper.selectBlogCommentList(comment);
            if (CollectionUtils.isNotEmpty(commentList)) {
                return -10L;
            }
        } else {
            return -20L;
        }
        BlogContent blog = blogContentMapper.selectBlogContentById(id);
        String praiseJsonResult = redisService.getCacheObject(BlogConstants.BLOG_PRAISE + BlogConstants.SEGMENTATION + id);
        if (StringUtils.isEmpty(praiseJsonResult)) {
            //给该博客点赞数
            redisService.setCacheObject(BlogConstants.BLOG_PRAISE + BlogConstants.SEGMENTATION + id, "1");
            blog.setCollectCount(1L);

        } else {
            Long count = blog.getCollectCount() + 1;
            //给该博客点赞 +1
            redisService.setCacheObject(BlogConstants.BLOG_PRAISE + BlogConstants.SEGMENTATION + id, count.toString());
            blog.setCollectCount(count);
        }
        blogContentMapper.updateBlogContent(blog);
        // 已登录用户，向评论表添加点赞数据
        if (SecurityUtils.getUserId() != null) {
            Long userId = SecurityUtils.getUserId();
            BlogComment comment = new BlogComment();
            comment.setUserId(userId);
            comment.setBlogId(id);
            comment.setSource(ECommentSource.BLOG_INFO.getCode());
            comment.setCommentType(BlogConstants.PRAISE);
            blogCommentMapper.insertBlogComment(comment);
        }
        return blog.getCollectCount();
    }

    /**
     * 获取相似博客
     * @param id 博客id
     * @return 博客列表
     */
    @Override
    public TableDataInfo getSameBlogByBlogId(Long id) {
        TableDataInfo dataInfo = new TableDataInfo();
        BlogContent blogContent = blogContentMapper.selectBlogContentById(id);
        Long sort = blogContent.getSort();
        BlogContent content = new BlogContent();
        content.setSort(sort);
        List<BlogContentVo> blogContentList = this.selectBlogContentList(content,null);
        dataInfo.setTotal(blogContentList.size()-1);
        blogContentList.removeIf(blogContent1 -> blogContent1.getId().equals(blogContent.getId()));
        dataInfo.setRows(blogContentList);
        dataInfo.setCode(HttpStatus.SUCCESS);
        dataInfo.setMsg("获取成功");
        return dataInfo;
    }


    /**
     * 获取归档时间
     * @return java.util.Set<java.lang.String>
     */
    @Override
    public Set<String> getBlogTimeSortList() {
        //从Redis中获取内容
        String monthResult = redisService.getCacheObject(BlogConstants.MONTH_SET);
        //判断redis中时候包含归档的内容
        if (ProconStrUtils.isNotEmpty(monthResult)) {
            List list = JsonUtils.jsonArrayToArrayList(monthResult);
            return new HashSet<String>(list);
        }
        // 第一次启动的时候归档
        Collection collection = monthSet(0, "");
        return (Set<String>) collection;
    }

    /**
     * 通过月份获取日期
     *
     * @param monthDate 月份
     * @return 博客列表
     */
    @Override
    public List<BlogContentVo> getArticleByMonth(String monthDate) {
        //从Redis中获取内容
        String contentResult = redisService.getCacheObject(BlogConstants.BLOG_SORT_BY_MONTH + BlogConstants.REDIS_SEGMENTATION + monthDate);

        //判断redis中时候包含该日期下的文章
        if (ProconStrUtils.isNotEmpty(contentResult)) {
            ArrayList list = JsonUtils.jsonArrayToArrayList(contentResult);
            return list;
        }
        // 第一次启动的时候归档
        Collection collection = monthSet(1, monthDate);
        return (List<BlogContentVo>) collection;
    }


    private Collection monthSet(Integer flag,String monthDate){
        BlogContent blogContent = new BlogContent();
        blogContent.setStatus(EStatus.ENABLE.getState());
        List<BlogContentVo> blogContentList = selectBlogContentList(blogContent,null);
        //因为首页并不需要显示内容，所以需要排除掉内容字段
        blogContentList.forEach(item -> item.setContent(""));

        Map<String, List<BlogContentVo>> map = new HashMap<>();
        Iterator<BlogContentVo> iterable = blogContentList.iterator();
        Set<String> monthSet = new TreeSet<>();
        while (iterable.hasNext()) {
            BlogContentVo blog =  iterable.next();
            Date createTime = blog.getCreateTime();
            String month = new SimpleDateFormat("yyyy年MM月").format(createTime);
            monthSet.add(month);
            if (map.get(month) == null) {
                List<BlogContentVo> blogList = new ArrayList<>();
                blogList.add(blog);
                map.put(month, blogList);
            } else {
                List<BlogContentVo> blogList = map.get(month);
                blogList.add(blog);
                map.put(month, blogList);
            }
        }

        // 缓存该月份下的所有文章  key: 月份   value：月份下的所有文章
        map.forEach((key, value) -> redisService.setCacheObject(BlogConstants.BLOG_SORT_BY_MONTH + BlogConstants.REDIS_SEGMENTATION + key, JsonUtils.objectToJson(value)));

        //将从数据库查询的数据缓存到redis中
        redisService.setCacheObject(BlogConstants.MONTH_SET, JsonUtils.objectToJson(monthSet));
        if(flag.equals(Constants.NO)){
            return monthSet;
        }else {
            return map.get(monthDate);
        }

    }


}

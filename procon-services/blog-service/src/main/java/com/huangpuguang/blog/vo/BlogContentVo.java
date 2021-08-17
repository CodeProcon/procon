package com.huangpuguang.blog.vo;

import com.huangpuguang.blog.domain.BlogContent;
import com.huangpuguang.blog.domain.BlogSort;
import com.huangpuguang.blog.domain.BlogTag;

import java.util.List;

/**
 * <p>博客管理Vo</p>
 *
 * @author procon
 * Created on 2020/10/29.
 */
public class BlogContentVo extends BlogContent {
    /**
     * 标签,一篇博客对应多个标签
     */
    private List<BlogTag> tagList;
    /**
     * 标题图
     */
    private List<String> photoList;
    /**
     * 博客分类
     */
    private List<BlogSort> blogSortList;
    /**
     * 点赞数
     */
    private Integer praiseCount;
    /**
     * 版权申明
     */
    private String copyright;

    /**
     * 博客等级关键字，仅用于getList
     */
    private String levelKeyword;

    /**
     * 使用Sort字段进行排序 （0：不使用， 1：使用），默认为0
     */
    private Integer useSort;


    /**
     * 是否开启评论(0:否， 1:是)
     */
    private String openComment;
    /** 版权声明 */
    private BlogSort blogSort;

    public List<BlogTag> getTagList() {
        return tagList;
    }

    public void setTagList(List<BlogTag> tagList) {
        this.tagList = tagList;
    }

    public List<String> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<String> photoList) {
        this.photoList = photoList;
    }

    public List<BlogSort> getBlogSortList() {
        return blogSortList;
    }

    public void setBlogSortList(List<BlogSort> blogSortList) {
        this.blogSortList = blogSortList;
    }

    public Integer getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getLevelKeyword() {
        return levelKeyword;
    }

    public void setLevelKeyword(String levelKeyword) {
        this.levelKeyword = levelKeyword;
    }

    public Integer getUseSort() {
        return useSort;
    }

    public void setUseSort(Integer useSort) {
        this.useSort = useSort;
    }



    public String getOpenComment() {
        return openComment;
    }

    public void setOpenComment(String openComment) {
        this.openComment = openComment;
    }

    public BlogSort getBlogSort() {
        return blogSort;
    }

    public void setBlogSort(BlogSort blogSort) {
        this.blogSort = blogSort;
    }

    @Override
    public String toString() {
        return "BlogContentVo{" +
                "tagList=" + tagList +
                ", photoList=" + photoList +
                ", blogSortList=" + blogSortList +
                ", praiseCount=" + praiseCount +
                ", copyright='" + copyright + '\'' +
                ", levelKeyword='" + levelKeyword + '\'' +
                ", useSort=" + useSort +
                ", openComment='" + openComment + '\'' +
                '}';
    }
}

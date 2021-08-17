package com.huangpuguang.blog.domain;

import com.huangpuguang.common.core.annotation.Excel;
import com.huangpuguang.common.core.web.domain.BaseEntity;


/**
 * 博客对象 blog_content
 *
 * @author procon
 * @date 2020-10-27
 */
public class BlogContent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 博客Id */
    private Long id;

    /** 博客标题 */
    @Excel(name = "博客标题")
    private String title;

    /** 博客简介 */
    @Excel(name = "博客简介")
    private String summary;

    /** 博客内容 */
    @Excel(name = "博客内容")
    private String content;

    /** 标签id */
    @Excel(name = "标签id")
    private String tagId;

    /** 博客点击数 */
    @Excel(name = "博客点击数")
    private Long clickCount;

    /** 博客收藏数 */
    @Excel(name = "博客收藏数")
    private Long collectCount;

    /** 标题图片id */
    @Excel(name = "标题图片id")
    private Long fileId;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 管理员id */
    @Excel(name = "管理员id")
    private Long adminId;

    /** 是否原创（0:不是 1：是） */
    @Excel(name = "是否原创", readConverterExp = "0=:不是,1=：是")
    private String isOriginal;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** 文章出处 */
    @Excel(name = "文章出处")
    private String articlesPart;

    /** 博客分类ID */
    @Excel(name = "博客分类id")
    private Long blogSortId;

    /** 推荐等级(0:正常) */
    @Excel(name = "推荐等级(0:正常)")
    private Integer level;

    /** 是否发布：0：否，1：是 */
    @Excel(name = "是否发布：0：否，1：是")
    private String isPublish;

    /** 排序字段 */
    @Excel(name = "排序字段")
    private Long sort;

    /** 是否开启评论(0:否 1:是) */
    @Excel(name = "是否开启评论(0:否 1:是)")
    private Integer startComment;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public String getSummary()
    {
        return summary;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }

    public void setClickCount(Long clickCount)
    {
        this.clickCount = clickCount;
    }

    public Long getClickCount()
    {
        return clickCount;
    }
    public void setCollectCount(Long collectCount)
    {
        this.collectCount = collectCount;
    }

    public Long getCollectCount()
    {
        return collectCount;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public void setIsOriginal(String isOriginal)
    {
        this.isOriginal = isOriginal;
    }

    public String getIsOriginal()
    {
        return isOriginal;
    }
    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getAuthor()
    {
        return author;
    }
    public void setArticlesPart(String articlesPart)
    {
        this.articlesPart = articlesPart;
    }

    public String getArticlesPart()
    {
        return articlesPart;
    }

    public void setLevel(Integer level)
    {
        this.level = level;
    }

    public Integer getLevel()
    {
        return level;
    }
    public void setIsPublish(String isPublish)
    {
        this.isPublish = isPublish;
    }

    public String getIsPublish()
    {
        return isPublish;
    }
    public void setSort(Long sort)
    {
        this.sort = sort;
    }

    public Long getSort()
    {
        return sort;
    }
    public void setStartComment(Integer startComment)
    {
        this.startComment = startComment;
    }

    public Integer getStartComment()
    {
        return startComment;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getBlogSortId() {
        return blogSortId;
    }

    public void setBlogSortId(Long blogSortId) {
        this.blogSortId = blogSortId;
    }

    @Override
    public String toString() {
        return "BlogContent{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", tagId=" + tagId +
                ", clickCount=" + clickCount +
                ", collectCount=" + collectCount +
                ", fileId=" + fileId +
                ", status=" + status +
                ", adminId='" + adminId + '\'' +
                ", isOriginal='" + isOriginal + '\'' +
                ", author='" + author + '\'' +
                ", articlesPart='" + articlesPart + '\'' +
                ", blogSortId=" + blogSortId +
                ", level=" + level +
                ", isPublish='" + isPublish + '\'' +
                ", sort=" + sort +
                ", startComment=" + startComment +
                '}';
    }
}

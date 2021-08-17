package com.huangpuguang.blog.domain;

import com.huangpuguang.common.core.annotation.Excel;
import com.huangpuguang.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;


/**
 * 博客分类对象 blog_sort
 *
 * @author procon
 * @date 2020-10-27
 */
public class BlogSort extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一id */
    private Long id;

    /** 分类内容 */
    @Excel(name = "分类内容")
    private String sortName;

    /** 分类简介 */
    @Excel(name = "分类简介")
    private String content;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 排序字段，越大越靠前 */
    @Excel(name = "排序字段，越大越靠前")
    private Long sort;

    /** 点击数 */
    @Excel(name = "点击数")
    private Long clickCount;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setSortName(String sortName)
    {
        this.sortName = sortName;
    }

    public String getSortName()
    {
        return sortName;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setSort(Long sort)
    {
        this.sort = sort;
    }

    public Long getSort()
    {
        return sort;
    }
    public void setClickCount(Long clickCount)
    {
        this.clickCount = clickCount;
    }

    public Long getClickCount()
    {
        return clickCount;
    }

    private List<String> photoList;

    public List<String> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<String> photoList) {
        this.photoList = photoList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sortName", getSortName())
            .append("content", getContent())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .append("sort", getSort())
            .append("clickCount", getClickCount())
            .toString();
    }
}

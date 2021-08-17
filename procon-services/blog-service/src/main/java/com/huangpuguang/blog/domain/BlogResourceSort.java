package com.huangpuguang.blog.domain;

import com.huangpuguang.common.core.annotation.Excel;
import com.huangpuguang.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 资源分类对象 blog_resource_sort
 *
 * @author procon
 * @date 2020-10-27
 */
public class BlogResourceSort extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一id */
    private Long id;

    /** 分类图片id */
    @Excel(name = "分类图片id")
    private Long fileId;

    /** 分类名 */
    @Excel(name = "分类名")
    private String sortName;

    /** 分类介绍 */
    @Excel(name = "分类介绍")
    private String content;

    /** 点击数 */
    @Excel(name = "点击数")
    private String clickCount;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 父ID */
    @Excel(name = "父ID")
    private Long parentId;

    /** 排序字段 */
    @Excel(name = "排序字段")
    private Long sort;

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
    public void setClickCount(String clickCount)
    {
        this.clickCount = clickCount;
    }

    public String getClickCount()
    {
        return clickCount;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setSort(Long sort)
    {
        this.sort = sort;
    }

    public Long getSort()
    {
        return sort;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fileId", getFileId())
            .append("sortName", getSortName())
            .append("content", getContent())
            .append("clickCount", getClickCount())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("parentId", getParentId())
            .append("sort", getSort())
            .toString();
    }
}

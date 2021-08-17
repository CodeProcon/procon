package com.huangpuguang.blog.domain;

import com.huangpuguang.common.core.annotation.Excel;
import com.huangpuguang.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 标签对象 blog_tag
 *
 * @author procon
 * @date 2020-10-27
 */
public class BlogTag extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一id */
    private Long id;

    /** 标签内容 */
    @Excel(name = "标签内容")
    private String content;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 标签简介 */
    @Excel(name = "标签简介")
    private Long clickCount;

    /** 排序字段，越大越靠前 */
    @Excel(name = "排序字段，越大越靠前")
    private Long sort;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
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
    public void setClickCount(Long clickCount)
    {
        this.clickCount = clickCount;
    }

    public Long getClickCount()
    {
        return clickCount;
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
            .append("content", getContent())
            .append("status", getStatus())
            .append("clickCount", getClickCount())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("sort", getSort())
            .toString();
    }
}

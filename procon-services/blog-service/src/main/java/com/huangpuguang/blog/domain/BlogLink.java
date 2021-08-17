package com.huangpuguang.blog.domain;

import com.huangpuguang.common.core.annotation.Excel;
import com.huangpuguang.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 友情链接对象 blog_link
 *
 * @author procon
 * @date 2020-10-27
 */
public class BlogLink extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一id */
    private Long id;

    /** 友情链接标题 */
    @Excel(name = "友情链接标题")
    private String title;

    /** 友情链接介绍 */
    @Excel(name = "友情链接介绍")
    private String summary;

    /** 友情链接URL */
    @Excel(name = "友情链接URL")
    private String url;

    /** 点击数 */
    @Excel(name = "点击数")
    private Long clickCount;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 排序字段，越大越靠前 */
    @Excel(name = "排序字段，越大越靠前")
    private Long sort;

    /** 友链状态： 0 申请中， 1：已上线，  2：已下架 */
    @Excel(name = "友链状态： 0 申请中， 1：已上线，  2：已下架")
    private Integer linkStatus;

    /** 申请用户ID */
    @Excel(name = "申请用户ID")
    private Long userId;

    /** 操作管理员ID */
    @Excel(name = "操作管理员ID")
    private Long adminId;

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
    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }
    public void setClickCount(Long clickCount)
    {
        this.clickCount = clickCount;
    }

    public Long getClickCount()
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
    public void setSort(Long sort)
    {
        this.sort = sort;
    }

    public Long getSort()
    {
        return sort;
    }
    public void setLinkStatus(Integer linkStatus)
    {
        this.linkStatus = linkStatus;
    }

    public Integer getLinkStatus()
    {
        return linkStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("summary", getSummary())
            .append("url", getUrl())
            .append("clickCount", getClickCount())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .append("sort", getSort())
            .append("linkStatus", getLinkStatus())
            .append("userId", getUserId())
            .append("adminId", getAdminId())
            .toString();
    }
}

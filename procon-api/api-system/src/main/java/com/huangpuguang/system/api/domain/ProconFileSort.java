package com.huangpuguang.system.api.domain;

import com.huangpuguang.common.core.annotation.Excel;
import com.huangpuguang.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 文件分类对象 t_file_sort
 *
 * @author procon
 * @date 2020-08-11
 */
public class ProconFileSort extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一id */
    private Long id;

    /** 项目名 */
    @Excel(name = "项目名")
    private String projectName;

    /** 分类名 */
    @Excel(name = "分类名")
    private String sortName;

    /** 分类路径 */
    @Excel(name = "分类路径")
    private String url;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    public String getProjectName()
    {
        return projectName;
    }
    public void setSortName(String sortName)
    {
        this.sortName = sortName;
    }

    public String getSortName()
    {
        return sortName;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectName", getProjectName())
            .append("sortName", getSortName())
            .append("url", getUrl())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

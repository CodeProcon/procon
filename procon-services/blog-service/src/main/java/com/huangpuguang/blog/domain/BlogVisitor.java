package com.huangpuguang.blog.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huangpuguang.common.core.annotation.Excel;
import com.huangpuguang.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 游客对象 blog_visitor
 *
 * @author procon
 * @date 2020-10-27
 */
public class BlogVisitor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一id */
    private Long id;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 登录次数 */
    @Excel(name = "登录次数")
    private Integer loginCount;

    /** 最后登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastLoginTime;

    /** 最后登录IP */
    @Excel(name = "最后登录IP")
    private String lastLoginIp;

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
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
    public void setLoginCount(Integer loginCount)
    {
        this.loginCount = loginCount;
    }

    public Integer getLoginCount()
    {
        return loginCount;
    }
    public void setLastLoginTime(Date lastLoginTime)
    {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastLoginTime()
    {
        return lastLoginTime;
    }
    public void setLastLoginIp(String lastLoginIp)
    {
        this.lastLoginIp = lastLoginIp;
    }

    public String getLastLoginIp()
    {
        return lastLoginIp;
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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userName", getUserName())
            .append("email", getEmail())
            .append("loginCount", getLoginCount())
            .append("lastLoginTime", getLastLoginTime())
            .append("lastLoginIp", getLastLoginIp())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

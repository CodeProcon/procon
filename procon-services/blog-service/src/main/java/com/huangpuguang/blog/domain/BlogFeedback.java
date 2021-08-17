package com.huangpuguang.blog.domain;

import com.huangpuguang.common.core.annotation.Excel;
import com.huangpuguang.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 反馈对象 blog_feedback
 *
 * @author procon
 * @date 2020-10-27
 */
public class BlogFeedback extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 反馈的内容 */
    @Excel(name = "反馈的内容")
    private String content;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 反馈状态： 0：已开启  1：进行中  2：已完成  3：已拒绝 */
    @Excel(name = "反馈状态： 0：已开启  1：进行中  2：已完成  3：已拒绝")
    private Integer feedbackStatus;

    /** 回复 */
    @Excel(name = "回复")
    private String reply;

    /** 管理员Id */
    @Excel(name = "管理员Id")
    private Long adminId;

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
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setFeedbackStatus(Integer feedbackStatus)
    {
        this.feedbackStatus = feedbackStatus;
    }

    public Integer getFeedbackStatus()
    {
        return feedbackStatus;
    }
    public void setReply(String reply)
    {
        this.reply = reply;
    }

    public String getReply()
    {
        return reply;
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
            .append("userId", getUserId())
            .append("content", getContent())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("title", getTitle())
            .append("feedbackStatus", getFeedbackStatus())
            .append("reply", getReply())
            .append("adminId", getAdminId())
            .toString();
    }
}

package com.huangpuguang.blog.domain;

import com.huangpuguang.common.core.annotation.Excel;
import com.huangpuguang.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 评论举报对象 blog_comment_report
 *
 * @author procon
 * @date 2020-10-27
 */
public class BlogCommentReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一id */
    private Long id;

    /** 举报人id */
    @Excel(name = "举报人id")
    private Long userId;

    /** 被举报的评论id */
    @Excel(name = "被举报的评论id")
    private Long reportCommentId;

    /** 被举报的用户id */
    @Excel(name = "被举报的用户id")
    private Long reportUserId;

    /** 举报的原因 */
    @Excel(name = "举报的原因")
    private String content;

    /** 进展状态: 0 未查看   1: 已查看  2：已处理 */
    @Excel(name = "进展状态: 0 未查看   1: 已查看  2：已处理")
    private Integer progress;

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

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setProgress(Integer progress)
    {
        this.progress = progress;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getReportCommentId() {
        return reportCommentId;
    }

    public void setReportCommentId(Long reportCommentId) {
        this.reportCommentId = reportCommentId;
    }

    public Long getReportUserId() {
        return reportUserId;
    }

    public void setReportUserId(Long reportUserId) {
        this.reportUserId = reportUserId;
    }

    public Integer getProgress()
    {
        return progress;
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
            .append("userId", getUserId())
            .append("reportCommentId", getReportCommentId())
            .append("reportUserId", getReportUserId())
            .append("content", getContent())
            .append("progress", getProgress())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

package com.huangpuguang.blog.domain;

import com.huangpuguang.common.core.annotation.Excel;
import com.huangpuguang.common.core.web.domain.BaseEntity;
import com.huangpuguang.system.api.domain.SysUser;

import java.util.List;


/**
 * 评论对象 blog_comment
 *
 * @author procon
 * @date 2020-10-27
 */
public class BlogComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 回复某条评论的id */
    @Excel(name = "回复某条评论的id")
    private Long toId;

    /** 回复某个人的id */
    @Excel(name = "回复某个人的id")
    private Long toUserId;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String content;

    /** 博客id */
    @Excel(name = "博客id")
    private Long blogId;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 评论来源： MESSAGE_BOARD，ABOUT，BLOG_INFO 等 */
    @Excel(name = "评论来源： MESSAGE_BOARD，ABOUT，BLOG_INFO 等")
    private String source;

    /** 评论类型 1:点赞 0:评论 */
    @Excel(name = "评论类型 1:点赞 0:评论")
    private Integer commentType;

    /** 一级评论ID */
    @Excel(name = "一级评论ID")
    private Long firstCommentId;

    /**  0 查询一级评论 1查询非一级评论*/
    private Integer firstCommentFlag;

    /**
     * 本条评论是哪个用户说的
     */
    private SysUser user;

    /**
     * 发表评论的用户名
     */
    private String userName;

    /**
     * 被回复的用户名
     */
    private String toUserName;


    /**
     * 本条评论对哪个用户说的，如果没有则为一级评论
     */

    private SysUser toUser;

    /**
     * 本条评论下的回复
     */

    private List<BlogComment> replyList;

    /**
     * 本条评论回复的那条评论
     */
    private BlogComment toComment;

    /**
     * 评论来源名称
     */
    private String sourceName;

    /**
     * 该评论来源的博客
     */
    private BlogContent blog;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getCommentType() {
        return commentType;
    }

    public void setCommentType(Integer commentType) {
        this.commentType = commentType;
    }

    public Long getFirstCommentId() {
        return firstCommentId;
    }

    public void setFirstCommentId(Long firstCommentId) {
        this.firstCommentId = firstCommentId;
    }

    public Integer getFirstCommentFlag() {
        return firstCommentFlag;
    }

    public void setFirstCommentFlag(Integer firstCommentFlag) {
        this.firstCommentFlag = firstCommentFlag;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public SysUser getToUser() {
        return toUser;
    }

    public void setToUser(SysUser toUser) {
        this.toUser = toUser;
    }

    public List<BlogComment> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<BlogComment> replyList) {
        this.replyList = replyList;
    }

    public BlogComment getToComment() {
        return toComment;
    }

    public void setToComment(BlogComment toComment) {
        this.toComment = toComment;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public BlogContent getBlog() {
        return blog;
    }

    public void setBlog(BlogContent blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "BlogComment{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", toId='" + toId + '\'' +
                ", toUserId='" + toUserId + '\'' +
                ", content='" + content + '\'' +
                ", blogId='" + blogId + '\'' +
                ", status=" + status +
                ", source='" + source + '\'' +
                ", type=" + commentType +
                ", firstCommentId='" + firstCommentId + '\'' +
                ", user=" + user +
                ", userName='" + userName + '\'' +
                ", toUserName='" + toUserName + '\'' +
                ", toUser=" + toUser +
                ", replyList=" + replyList +
                ", toComment=" + toComment +
                ", sourceName='" + sourceName + '\'' +
                ", blog=" + blog +
                '}';
    }
}

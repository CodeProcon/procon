package com.huangpuguang.blog.domain;

import com.huangpuguang.common.core.annotation.Excel;
import com.huangpuguang.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;


/**
 * 【请填写功能名称】对象 blog_web_config
 *
 * @author procon
 * @date 2020-10-27
 */
public class BlogWebConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** logo(文件ID) */
    @Excel(name = "logo(文件ID)")
    private String logo;

    /** 网站名称 */
    @Excel(name = "网站名称")
    private String name;

    @Excel(name = "配置类型")
    private int configType;
    /** 介绍 */
    @Excel(name = "介绍")
    private String summary;

    /** 关键字 */
    @Excel(name = "关键字")
    private String keyword;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** 备案号 */
    @Excel(name = "备案号")
    private String recordNum;

    /** 是否开启评论(0:否 1:是) */
    @Excel(name = "是否开启评论(0:否 1:是)")
    private String startComment;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 支付宝收款码FileId */
    @Excel(name = "支付宝收款码FileId")
    private String aliPay;

    /** 微信收款码FileId */
    @Excel(name = "微信收款码FileId")
    private String weixinPay;

    /** github地址 */
    @Excel(name = "github地址")
    private String github;

    /** gitee地址 */
    @Excel(name = "gitee地址")
    private String gitee;

    /** QQ号 */
    @Excel(name = "QQ号")
    private String qqNumber;

    /** QQ群 */
    @Excel(name = "QQ群")
    private String qqGroup;

    /** 微信号 */
    @Excel(name = "微信号")
    private String weChat;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 显示的列表（用于控制邮箱、QQ、QQ群、Github、Gitee、微信是否显示在前端） */
    @Excel(name = "显示的列表", readConverterExp = "用=于控制邮箱、QQ、QQ群、Github、Gitee、微信是否显示在前端")
    private String showList;

    /** 登录方式列表（用于控制前端登录方式，如账号密码,码云,Github,QQ,微信） */
    @Excel(name = "登录方式列表", readConverterExp = "用=于控制前端登录方式，如账号密码,码云,Github,QQ,微信")
    private String loginTypeList;

    private List<String> photoList;
    private String aliPayPhoto;
    private String weChatPayPhoto;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setLogo(String logo)
    {
        this.logo = logo;
    }

    public String getLogo()
    {
        return logo;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public String getSummary()
    {
        return summary;
    }
    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }

    public String getKeyword()
    {
        return keyword;
    }
    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getAuthor()
    {
        return author;
    }
    public void setRecordNum(String recordNum)
    {
        this.recordNum = recordNum;
    }

    public String getRecordNum()
    {
        return recordNum;
    }
    public void setStartComment(String startComment)
    {
        this.startComment = startComment;
    }

    public String getStartComment()
    {
        return startComment;
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
    public void setAliPay(String aliPay)
    {
        this.aliPay = aliPay;
    }

    public String getAliPay()
    {
        return aliPay;
    }
    public void setWeixinPay(String weixinPay)
    {
        this.weixinPay = weixinPay;
    }

    public String getWeixinPay()
    {
        return weixinPay;
    }
    public void setGithub(String github)
    {
        this.github = github;
    }

    public String getGithub()
    {
        return github;
    }
    public void setGitee(String gitee)
    {
        this.gitee = gitee;
    }

    public String getGitee()
    {
        return gitee;
    }
    public void setQqNumber(String qqNumber)
    {
        this.qqNumber = qqNumber;
    }

    public String getQqNumber()
    {
        return qqNumber;
    }
    public void setQqGroup(String qqGroup)
    {
        this.qqGroup = qqGroup;
    }

    public String getQqGroup()
    {
        return qqGroup;
    }
    public void setWeChat(String weChat)
    {
        this.weChat = weChat;
    }

    public String getWeChat()
    {
        return weChat;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
    public void setShowList(String showList)
    {
        this.showList = showList;
    }

    public String getShowList()
    {
        return showList;
    }
    public void setLoginTypeList(String loginTypeList)
    {
        this.loginTypeList = loginTypeList;
    }

    public String getLoginTypeList()
    {
        return loginTypeList;
    }

    public int getConfigType() {
        return configType;
    }

    public void setConfigType(int configType) {
        this.configType = configType;
    }

    public List<String> getPhotoList() {
        return photoList;
    }

    public String getAliPayPhoto() {
        return aliPayPhoto;
    }

    public void setAliPayPhoto(String aliPayPhoto) {
        this.aliPayPhoto = aliPayPhoto;
    }

    public String getWeChatPayPhoto() {
        return weChatPayPhoto;
    }

    public void setWeChatPayPhoto(String weChatPayPhoto) {
        this.weChatPayPhoto = weChatPayPhoto;
    }

    public void setPhotoList(List<String> photoList) {
        this.photoList = photoList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("logo", getLogo())
            .append("name", getName())
            .append("summary", getSummary())
            .append("keyword", getKeyword())
            .append("author", getAuthor())
            .append("recordNum", getRecordNum())
            .append("startComment", getStartComment())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("title", getTitle())
            .append("aliPay", getAliPay())
            .append("weixinPay", getWeixinPay())
            .append("github", getGithub())
            .append("gitee", getGitee())
            .append("qqNumber", getQqNumber())
            .append("qqGroup", getQqGroup())
            .append("weChat", getWeChat())
            .append("email", getEmail())
            .append("showList", getShowList())
            .append("loginTypeList", getLoginTypeList())
            .toString();
    }


}

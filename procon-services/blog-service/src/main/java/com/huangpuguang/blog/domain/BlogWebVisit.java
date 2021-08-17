package com.huangpuguang.blog.domain;

import com.huangpuguang.common.core.annotation.Excel;
import com.huangpuguang.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * Web访问记录对象 blog_web_visit
 *
 * @author procon
 * @date 2020-10-27
 */
public class BlogWebVisit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 访问ip地址 */
    @Excel(name = "访问ip地址")
    private String ip;

    /** 用户行为 */
    @Excel(name = "用户行为")
    private String behavior;

    /** 模块id（文章id，标签id，分类id） */
    @Excel(name = "模块id", readConverterExp = "文章id，标签id，分类id")
    private String moduleId;

    /** 附加数据(比如搜索内容) */
    @Excel(name = "附加数据(比如搜索内容)")
    private String otherData;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 操作系统 */
    @Excel(name = "操作系统")
    private String os;

    /** 浏览器 */
    @Excel(name = "浏览器")
    private String browser;

    /** ip来源 */
    @Excel(name = "ip来源")
    private String ipSource;

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

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getIp()
    {
        return ip;
    }
    public void setBehavior(String behavior)
    {
        this.behavior = behavior;
    }

    public String getBehavior()
    {
        return behavior;
    }
    public void setModuleId(String moduleId)
    {
        this.moduleId = moduleId;
    }

    public String getModuleId()
    {
        return moduleId;
    }
    public void setOtherData(String otherData)
    {
        this.otherData = otherData;
    }

    public String getOtherData()
    {
        return otherData;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setOs(String os)
    {
        this.os = os;
    }

    public String getOs()
    {
        return os;
    }
    public void setBrowser(String browser)
    {
        this.browser = browser;
    }

    public String getBrowser()
    {
        return browser;
    }
    public void setIpSource(String ipSource)
    {
        this.ipSource = ipSource;
    }

    public String getIpSource()
    {
        return ipSource;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("ip", getIp())
            .append("behavior", getBehavior())
            .append("moduleId", getModuleId())
            .append("otherData", getOtherData())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("os", getOs())
            .append("browser", getBrowser())
            .append("ipSource", getIpSource())
            .toString();
    }
}

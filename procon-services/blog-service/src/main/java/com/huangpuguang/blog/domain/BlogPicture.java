package com.huangpuguang.blog.domain;

import com.huangpuguang.common.core.annotation.Excel;
import com.huangpuguang.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 图片对象 blog_picture
 *
 * @author procon
 * @date 2020-10-27
 */
public class BlogPicture extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一id */
    private Long id;

    /** 图片id */
    @Excel(name = "图片id")
    private Long fileId;

    /** 图片名 */
    @Excel(name = "图片名")
    private String picName;

    /** 分类id */
    @Excel(name = "分类id")
    private Long pictureSortId;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;
    /** 图片url */
    private String fileUrl;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public void setPicName(String picName)
    {
        this.picName = picName;
    }

    public String getPicName()
    {
        return picName;
    }

    public Long getPictureSortId() {
        return pictureSortId;
    }

    public void setPictureSortId(Long pictureSortId) {
        this.pictureSortId = pictureSortId;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fileId", getFileId())
            .append("picName", getPicName())
            .append("pictureSortId", getPictureSortId())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }

}

package com.huangpuguang.blog.domain;

import com.huangpuguang.common.core.annotation.Excel;
import com.huangpuguang.common.core.web.domain.BaseEntity;
import com.huangpuguang.system.api.domain.ProconFile;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;


/**
 * 图片分类对象 blog_picture_sort
 *
 * @author procon
 * @date 2020-10-27
 */
public class BlogPictureSort extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一id */
    private Long id;

    /** 分类图片id */
    @Excel(name = "分类图片id")
    private Long fileId;

    /** 分类名 */
    @Excel(name = "分类名")
    private String name;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** $column.columnComment */
    @Excel(name = "状态")
    private Long parentId;

    /** 排序字段，越大越靠前 */
    @Excel(name = "排序字段，越大越靠前")
    private Long sort;

    /** 是否显示，1：是，0，否 */
    @Excel(name = "是否显示，1：是，0，否")
    private Integer isShow;

    private List<ProconFile> photoList;

    public List<ProconFile> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<ProconFile> photoList) {
        this.photoList = photoList;
    }

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

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setSort(Long sort)
    {
        this.sort = sort;
    }

    public Long getSort()
    {
        return sort;
    }
    public void setIsShow(Integer isShow)
    {
        this.isShow = isShow;
    }

    public Integer getIsShow()
    {
        return isShow;
    }



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fileId", getFileId())
            .append("name", getName())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("parentId", getParentId())
            .append("sort", getSort())
            .append("isShow", getIsShow())
            .toString();
    }
}

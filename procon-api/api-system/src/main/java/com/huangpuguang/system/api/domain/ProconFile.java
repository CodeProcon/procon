package com.huangpuguang.system.api.domain;

import com.huangpuguang.common.core.annotation.Excel;
import com.huangpuguang.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 文件对象 t_file
 *
 * @author procon
 * @date 2020-08-11
 */
public class ProconFile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一id */
    private Long id;

    /** 旧文件名 */
    @Excel(name = "旧文件名")
    private String fileOldName;

    /** 文件名 */
    @Excel(name = "文件名")
    private String fileName;

    /** 文件地址 */
    @Excel(name = "文件地址")
    private String fileUrl;

    /** 文件扩展名 */
    @Excel(name = "文件扩展名")
    private String fileExpandedName;

    /** 文件大小 */
    @Excel(name = "文件大小")
    private Long fileSize;

    /** 文件分类id */
    @Excel(name = "文件分类id")
    private String fileSortId;

    /** 管理员id */
    @Excel(name = "管理员id")
    private Long adminId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 阿里云地址 */
    @Excel(name = "阿里云地址")
    private Integer fileType;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setFileOldName(String fileOldName)
    {
        this.fileOldName = fileOldName;
    }

    public String getFileOldName()
    {
        return fileOldName;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }
    public void setFileUrl(String fileUrl)
    {
        this.fileUrl = fileUrl;
    }

    public String getFileUrl()
    {
        return fileUrl;
    }
    public void setFileExpandedName(String fileExpandedName)
    {
        this.fileExpandedName = fileExpandedName;
    }

    public String getFileExpandedName()
    {
        return fileExpandedName;
    }
    public void setFileSize(Long fileSize)
    {
        this.fileSize = fileSize;
    }

    public Long getFileSize()
    {
        return fileSize;
    }
    public void setFileSortId(String fileSortId)
    {
        this.fileSortId = fileSortId;
    }

    public String getFileSortId()
    {
        return fileSortId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }



    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fileOldName", getFileOldName())
            .append("picName", getFileName())
            .append("picUrl", getFileUrl())
            .append("picExpandedName", getFileExpandedName())
            .append("fileSize", getFileSize())
            .append("fileSortId", getFileSortId())
            .append("adminId", getAdminId())
            .append("userId", getUserId())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("fileType", getFileType())
            .toString();
    }
}

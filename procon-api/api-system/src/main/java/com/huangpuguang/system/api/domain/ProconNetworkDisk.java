package com.huangpuguang.system.api.domain;

import com.huangpuguang.common.core.annotation.Excel;
import com.huangpuguang.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 网盘文件对象 t_network_disk
 *
 * @author procon
 * @date 2020-08-11
 */
public class ProconNetworkDisk extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一uid */
    private Long id;

    /** 管理员uid */
    @Excel(name = "管理员id")
    private Long adminId;

    /** 扩展名 */
    @Excel(name = "扩展名")
    private String extendName;

    /** 文件名 */
    @Excel(name = "文件名")
    private String fileName;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String filePath;

    /** 文件大小 */
    @Excel(name = "文件大小")
    private Long fileSize;

    /** 是否目录 */
    @Excel(name = "是否目录")
    private Long isDir;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 本地文件URL */
    @Excel(name = "本地文件URL")
    private String localUrl;

    /** 七牛云URL */
    @Excel(name = "阿里云URL")
    private String remoteUrl;

    /** 上传前文件名 */
    @Excel(name = "上传前文件名")
    private String fileOldName;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setExtendName(String extendName)
    {
        this.extendName = extendName;
    }

    public String getExtendName()
    {
        return extendName;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public String getFilePath()
    {
        return filePath;
    }
    public void setFileSize(Long fileSize)
    {
        this.fileSize = fileSize;
    }

    public Long getFileSize()
    {
        return fileSize;
    }
    public void setIsDir(Long isDir)
    {
        this.isDir = isDir;
    }

    public Long getIsDir()
    {
        return isDir;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setLocalUrl(String localUrl)
    {
        this.localUrl = localUrl;
    }

    public String getLocalUrl()
    {
        return localUrl;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    public void setFileOldName(String fileOldName)
    {
        this.fileOldName = fileOldName;
    }

    public String getFileOldName()
    {
        return fileOldName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("adminUid", getAdminId())
            .append("extendName", getExtendName())
            .append("fileName", getFileName())
            .append("filePath", getFilePath())
            .append("fileSize", getFileSize())
            .append("isDir", getIsDir())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("localUrl", getLocalUrl())
            .append("fileOldName", getFileOldName())
            .toString();
    }
}

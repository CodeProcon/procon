package com.huangpuguang.system.api.domain;

import com.huangpuguang.common.core.annotation.Excel;
import com.huangpuguang.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 存储信息对象 t_storage
 *
 * @author procon
 * @date 2020-08-11
 */
public class ProconStorage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一id */
    private Long id;

    /** 管理员id */
    @Excel(name = "管理员id")
    private Long adminId;

    /** $column.columnComment */
    @Excel(name = "存储大小")
    private Long storageSize;

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

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public void setStorageSize(Long storageSize)
    {
        this.storageSize = storageSize;
    }

    public Long getStorageSize()
    {
        return storageSize;
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
            .append("adminId", getAdminId())
            .append("storageSize", getStorageSize())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

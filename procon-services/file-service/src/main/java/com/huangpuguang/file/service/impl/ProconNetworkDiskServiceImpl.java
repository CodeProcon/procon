package com.huangpuguang.file.service.impl;

import com.huangpuguang.common.core.utils.DateUtils;
import com.huangpuguang.file.mapper.ProconNetworkDiskMapper;
import com.huangpuguang.file.service.ProconNetworkDiskService;
import com.huangpuguang.system.api.domain.ProconNetworkDisk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 网盘文件Service业务层处理
 *
 * @author procon
 * @date 2020-08-11
 */
@Service
public class ProconNetworkDiskServiceImpl implements ProconNetworkDiskService
{
    @Autowired
    private ProconNetworkDiskMapper networkDiskMapper;

    /**
     * 查询网盘文件
     *
     * @param id 网盘文件ID
     * @return 网盘文件
     */
    @Override
    public ProconNetworkDisk selectNetworkDiskById(Long id)
    {
        return networkDiskMapper.selectNetworkDiskById(id);
    }

    /**
     * 查询网盘文件列表
     *
     * @param networkDisk 网盘文件
     * @return 网盘文件
     */
    @Override
    public List<ProconNetworkDisk> selectNetworkDiskList(ProconNetworkDisk networkDisk)
    {
        return networkDiskMapper.selectNetworkDiskList(networkDisk);
    }

    /**
     * 新增网盘文件
     *
     * @param networkDisk 网盘文件
     * @return 结果
     */
    @Override
    public int insertNetworkDisk(ProconNetworkDisk networkDisk)
    {
        networkDisk.setCreateTime(DateUtils.getNowDate());
        return networkDiskMapper.insertNetworkDisk(networkDisk);
    }

    /**
     * 修改网盘文件
     *
     * @param networkDisk 网盘文件
     * @return 结果
     */
    @Override
    public int updateNetworkDisk(ProconNetworkDisk networkDisk)
    {
        networkDisk.setUpdateTime(DateUtils.getNowDate());
        return networkDiskMapper.updateNetworkDisk(networkDisk);
    }

    /**
     * 批量删除网盘文件
     *
     * @param ids 需要删除的网盘文件ID
     * @return 结果
     */
    @Override
    public int deleteNetworkDiskByIds(Long[] ids)
    {
        return networkDiskMapper.deleteNetworkDiskByIds(ids);
    }

    /**
     * 删除网盘文件信息
     *
     * @param id 网盘文件ID
     * @return 结果
     */
    @Override
    public int deleteNetworkDiskById(Long id)
    {
        return networkDiskMapper.deleteNetworkDiskById(id);
    }
}

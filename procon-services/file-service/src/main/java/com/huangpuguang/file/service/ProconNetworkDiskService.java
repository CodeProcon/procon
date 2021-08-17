package com.huangpuguang.file.service;




import com.huangpuguang.system.api.domain.ProconNetworkDisk;

import java.util.List;


/**
 * 网盘文件Service接口
 *
 * @author procon
 * @date 2020-08-11
 */
public interface ProconNetworkDiskService
{
    /**
     * 查询网盘文件
     *
     * @param id 网盘文件ID
     * @return 网盘文件
     */
    public ProconNetworkDisk selectNetworkDiskById(Long id);

    /**
     * 查询网盘文件列表
     *
     * @param networkDisk 网盘文件
     * @return 网盘文件集合
     */
    public List<ProconNetworkDisk> selectNetworkDiskList(ProconNetworkDisk networkDisk);

    /**
     * 新增网盘文件
     *
     * @param networkDisk 网盘文件
     * @return 结果
     */
    public int insertNetworkDisk(ProconNetworkDisk networkDisk);

    /**
     * 修改网盘文件
     *
     * @param networkDisk 网盘文件
     * @return 结果
     */
    public int updateNetworkDisk(ProconNetworkDisk networkDisk);

    /**
     * 批量删除网盘文件
     *
     * @param ids 需要删除的网盘文件ID
     * @return 结果
     */
    public int deleteNetworkDiskByIds(Long[] ids);

    /**
     * 删除网盘文件信息
     *
     * @param id 网盘文件ID
     * @return 结果
     */
    public int deleteNetworkDiskById(Long id);
}

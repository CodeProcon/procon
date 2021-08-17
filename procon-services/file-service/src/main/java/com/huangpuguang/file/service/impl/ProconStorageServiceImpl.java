package com.huangpuguang.file.service.impl;

import com.huangpuguang.common.core.utils.DateUtils;
import com.huangpuguang.file.mapper.ProconStorageMapper;
import com.huangpuguang.file.service.ProconStorageService;
import com.huangpuguang.system.api.domain.ProconStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 存储信息Service业务层处理
 *
 * @author procon
 * @date 2020-08-11
 */
@Service
public class ProconStorageServiceImpl implements ProconStorageService
{
    @Autowired
    private ProconStorageMapper storageMapper;

    /**
     * 查询存储信息
     *
     * @param id 存储信息ID
     * @return 存储信息
     */
    @Override
    public ProconStorage selectStorageById(Long id)
    {
        return storageMapper.selectStorageById(id);
    }

    /**
     * 查询存储信息列表
     *
     * @param storage 存储信息
     * @return 存储信息
     */
    @Override
    public List<ProconStorage> selectStorageList(ProconStorage storage)
    {
        return storageMapper.selectStorageList(storage);
    }

    /**
     * 新增存储信息
     *
     * @param storage 存储信息
     * @return 结果
     */
    @Override
    public int insertStorage(ProconStorage storage)
    {
        storage.setCreateTime(DateUtils.getNowDate());
        return storageMapper.insertStorage(storage);
    }

    /**
     * 修改存储信息
     *
     * @param storage 存储信息
     * @return 结果
     */
    @Override
    public int updateStorage(ProconStorage storage)
    {
        storage.setUpdateTime(DateUtils.getNowDate());
        return storageMapper.updateStorage(storage);
    }

    /**
     * 批量删除存储信息
     *
     * @param ids 需要删除的存储信息ID
     * @return 结果
     */
    @Override
    public int deleteStorageByIds(Long[] ids)
    {
        return storageMapper.deleteStorageByIds(ids);
    }

    /**
     * 删除存储信息信息
     *
     * @param id 存储信息ID
     * @return 结果
     */
    @Override
    public int deleteStorageById(Long id)
    {
        return storageMapper.deleteStorageById(id);
    }
}

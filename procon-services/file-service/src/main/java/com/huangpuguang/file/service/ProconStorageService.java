package com.huangpuguang.file.service;




import com.huangpuguang.system.api.domain.ProconStorage;

import java.util.List;


/**
 * 存储信息Service接口
 *
 * @author procon
 * @date 2020-08-11
 */
public interface ProconStorageService
{
    /**
     * 查询存储信息
     *
     * @param id 存储信息ID
     * @return 存储信息
     */
    public ProconStorage selectStorageById(Long id);

    /**
     * 查询存储信息列表
     *
     * @param storage 存储信息
     * @return 存储信息集合
     */
    public List<ProconStorage> selectStorageList(ProconStorage storage);

    /**
     * 新增存储信息
     *
     * @param storage 存储信息
     * @return 结果
     */
    public int insertStorage(ProconStorage storage);

    /**
     * 修改存储信息
     *
     * @param storage 存储信息
     * @return 结果
     */
    public int updateStorage(ProconStorage storage);

    /**
     * 批量删除存储信息
     *
     * @param ids 需要删除的存储信息ID
     * @return 结果
     */
    public int deleteStorageByIds(Long[] ids);

    /**
     * 删除存储信息信息
     *
     * @param id 存储信息ID
     * @return 结果
     */
    public int deleteStorageById(Long id);
}

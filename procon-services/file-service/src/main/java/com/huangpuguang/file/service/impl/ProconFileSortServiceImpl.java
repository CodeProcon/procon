package com.huangpuguang.file.service.impl;

import com.huangpuguang.common.core.utils.ProconDateUtils;
import com.huangpuguang.common.core.web.service.impl.BaseServiceImpl;
import com.huangpuguang.file.mapper.ProconFileSortMapper;
import com.huangpuguang.file.service.ProconFileSortService;
import com.huangpuguang.system.api.domain.ProconFileSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 文件分类Service业务层处理
 *
 * @author procon
 * @date 2020-08-11
 */
@Service
public class ProconFileSortServiceImpl extends BaseServiceImpl<ProconFileSortMapper,ProconFileSort> implements ProconFileSortService
{

    private final ProconFileSortMapper fileSortMapper;

    @Autowired
    public ProconFileSortServiceImpl(ProconFileSortMapper fileSortMapper) {
        this.fileSortMapper = fileSortMapper;
    }

    /**
     * 查询文件分类
     *
     * @param id 文件分类ID
     * @return 文件分类
     */
    @Override
    public ProconFileSort selectFileSortById(Long id)
    {
        return fileSortMapper.selectFileSortById(id);
    }

    /**
     * 查询文件分类列表
     *
     * @param fileSort 文件分类
     * @return 文件分类
     */
    @Override
    public List<ProconFileSort> selectFileSortList(ProconFileSort fileSort)
    {
        return fileSortMapper.selectFileSortList(fileSort);
    }

    /**
     * 新增文件分类
     *
     * @param fileSort 文件分类
     * @return 结果
     */
    @Override
    public int insertFileSort(ProconFileSort fileSort)
    {
        fileSort.setCreateTime(ProconDateUtils.getNowDate());
        return fileSortMapper.insertFileSort(fileSort);
    }

    /**
     * 修改文件分类
     *
     * @param fileSort 文件分类
     * @return 结果
     */
    @Override
    public int updateFileSort(ProconFileSort fileSort)
    {
        fileSort.setUpdateTime(ProconDateUtils.getNowDate());
        return fileSortMapper.updateFileSort(fileSort);
    }

    /**
     * 批量删除文件分类
     *
     * @param ids 需要删除的文件分类ID
     * @return 结果
     */
    @Override
    public int deleteFileSortByIds(Long[] ids)
    {
        return fileSortMapper.deleteFileSortByIds(ids);
    }

    /**
     * 删除文件分类信息
     *
     * @param id 文件分类ID
     * @return 结果
     */
    @Override
    public int deleteFileSortById(Long id)
    {
        return fileSortMapper.deleteFileSortById(id);
    }
}

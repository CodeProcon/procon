package com.huangpuguang.file.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huangpuguang.system.api.domain.ProconFileSort;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 文件分类Mapper接口
 *
 * @author procon
 * @date 2020-08-11
 */
@Repository
public interface ProconFileSortMapper extends BaseMapper<ProconFileSort>
{
    /**
     * 查询文件分类
     *
     * @param id 文件分类ID
     * @return 文件分类
     */

    public ProconFileSort selectFileSortById(Long id);

    /**
     * 查询文件分类列表
     *
     * @param fileSort 文件分类
     * @return 文件分类集合
     */
    public List<ProconFileSort> selectFileSortList(ProconFileSort fileSort);

    /**
     * 新增文件分类
     *
     * @param fileSort 文件分类
     * @return 结果
     */
    public int insertFileSort(ProconFileSort fileSort);

    /**
     * 修改文件分类
     *
     * @param fileSort 文件分类
     * @return 结果
     */
    public int updateFileSort(ProconFileSort fileSort);

    /**
     * 删除文件分类
     *
     * @param id 文件分类ID
     * @return 结果
     */
    public int deleteFileSortById(Long id);

    /**
     * 批量删除文件分类
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFileSortByIds(Long[] ids);
}

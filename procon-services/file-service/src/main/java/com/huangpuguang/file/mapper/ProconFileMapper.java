package com.huangpuguang.file.mapper;



import com.huangpuguang.system.api.domain.ProconFile;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 文件Mapper接口
 *
 * @author procon
 * @date 2020-08-11
 */
@Repository
public interface ProconFileMapper
{
    /**
     * 查询文件
     *
     * @param id 文件ID
     * @return 文件
     */

    public ProconFile selectFileById(Long id);

    /**
     * 查询文件列表
     *
     * @param file 文件
     * @return 文件集合
     */
    public List<ProconFile> selectFileList(ProconFile file);

    /**
     * 新增文件
     *
     * @param file 文件
     * @return 结果
     */
    public int insertFile(ProconFile file);

    /**
     * 修改文件
     *
     * @param file 文件
     * @return 结果
     */
    public int updateFile(ProconFile file);

    /**
     * 删除文件
     *
     * @param id 文件ID
     * @return 结果
     */
    public int deleteFileById(Long id);

    /**
     * 批量删除文件
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFileByIds(Long[] ids);

    /**
     * <p> 根据ids查询文件 </p>
     * @return java.util.List<com.huangpuguang.file.api.domain.File>
     * @date 2020/11/11 22:51
     */
    public List<ProconFile> selectBlogSortByIds(List<Long> ids);
}

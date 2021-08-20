package com.huangpuguang.file.service;

import com.huangpuguang.common.core.domain.ResultModel;
import com.huangpuguang.common.core.web.domain.AjaxResult;
import com.huangpuguang.system.api.domain.ProconFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


/**
 * 文件Service接口
 *
 * @author procon
 * @date 2020-08-11
 */
public interface ProconFileService
{
    /**
     * 查询文件
     *
     * @param id 文件ID
     * @return 文件
     */
    ProconFile selectFileById(Long id);

    /**
     * 查询文件列表
     *
     * @param file 文件
     * @return 文件集合
     */
    List<ProconFile> selectFileList(ProconFile file);

    /**
     * 新增文件
     *
     * @param file 文件
     * @param destPath 文件上传路径
     * @param request http请求
     * @return 结果
     */
    AjaxResult insertFile(MultipartFile file, Integer uploadType, String destPath,boolean notSave, HttpServletRequest request) throws IOException;

    /**
     * 保存文件
     * @param file  文件参数
     * @return 结果
     */
    ResultModel<String> saveFile(ProconFile file);
    /**   * 修改文件
     *
     * @param file 文件
     * @return 结果
     */
    int updateFile(ProconFile file);

    /**
     * 批量删除文件
     *
     * @param ids 需要删除的文件ID
     * @return 结果
     */
    int deleteFileByIds(Long[] ids);

    /**
     * 删除文件信息
     *
     * @param id 文件ID
     * @return 结果
     */
    int deleteFileById(Long id);


    /**
     * <p> 功能描述 </p>
     * @param ids id集合
     * @param splitCode 分隔符
     * @return com.huangpuguang.file.api.domain.File
     * @date 2020/11/11 22:53
     */
    public List<ProconFile> selectFileByIds(String ids, String splitCode);
}

package com.huangpuguang.file.service;

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
     * @param destPath 文件上传路径
     * @param request http请求
     * @return 结果
     */
    public AjaxResult insertFile(MultipartFile file, Integer uploadType, String destPath, HttpServletRequest request) throws IOException;

    /**
     * 修改文件
     *
     * @param file 文件
     * @return 结果
     */
    public int updateFile(ProconFile file);

    /**
     * 批量删除文件
     *
     * @param ids 需要删除的文件ID
     * @return 结果
     */
    public int deleteFileByIds(Long[] ids);

    /**
     * 删除文件信息
     *
     * @param id 文件ID
     * @return 结果
     */
    public int deleteFileById(Long id);

    /**
     * <p> 上传图片 </p>
     * @param request 请求参数
     * @return java.lang.Object
     * @date 2020/11/5 16:52
     */
    public Object imgUpload(HttpServletRequest request) throws IOException;

    /**
     * <p> 多图上传 </p>
     * @param uploadType, request, fileDatas
     * @return com.huangpuguang.common.core.web.domain.AjaxResult
     * @date 2020/11/5 17:47
     */
    public AjaxResult uploadImages(String path, Integer uploadType, HttpServletRequest request, List<MultipartFile> fileDatas) throws IOException;


    /**
     * <p> 功能描述 </p>
     * @param ids id集合
     * @param splitCode 分隔符
     * @return com.huangpuguang.file.api.domain.File
     * @date 2020/11/11 22:53
     */
    public List<ProconFile> selectFileByIds(String ids, String splitCode);
}

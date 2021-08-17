package com.huangpuguang.system.api;

import com.huangpuguang.common.core.constant.ServiceNameConstants;
import com.huangpuguang.common.core.domain.ResultModel;
import com.huangpuguang.common.core.web.domain.AjaxResult;
import com.huangpuguang.system.api.domain.ProconFile;
import com.huangpuguang.system.api.domain.SysFile;
import com.huangpuguang.system.api.factory.RemoteFileFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 文件服务
 *
 * @author procon
 */
@FeignClient(contextId = "remoteFileService", value = ServiceNameConstants.FILE_SERVICE, fallbackFactory = RemoteFileFallbackFactory.class)
public interface RemoteFileService
{
    /**
     * 上传文件
     *
     * @param file 文件信息
     * @return 结果
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResultModel<SysFile> upload(@RequestPart(value = "file") MultipartFile file);

    /**
     * 文件条件feign
     * @param file 文件
     * @param uploadType 上传类型
     * @param request 请求request
     * @return com.huangpuguang.common.core.web.domain.AjaxResult
     */
    @PostMapping("file/add")
    public AjaxResult add(@RequestParam("file") MultipartFile file,
                          @RequestParam("uploadType") Integer uploadType,
                          HttpServletRequest request);

    /**
     * 根据id集合获取文件
     * @param fileIds id列表
     * @param splitCode 分隔符
     * @return com.huangpuguang.common.core.web.domain.AjaxResult
     */
    @GetMapping(value = "/file/getInfos")
    public List<ProconFile> getInfos(@RequestParam("fileIds") String fileIds,
                                     @RequestParam("splitCode") String splitCode);

    /**
     * <p> 功能描述 </p>
     * @param id 文件id
     * @return com.huangpuguang.common.core.web.domain.AjaxResult
     * @date 2020/11/11 23:24
     */
    @GetMapping(value = "file/{id}")
    public ProconFile getInfo(@PathVariable("id") Long id);

}

package com.huangpuguang.file.controller;

import com.huangpuguang.file.service.SysFileService;
import com.huangpuguang.common.core.domain.ResultModel;
import com.huangpuguang.system.api.domain.SysFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件请求处理
 *
 * @author procon
 */
@RestController
public class SysFileController
{
    private static final Logger log = LoggerFactory.getLogger(SysFileController.class);

    @Autowired
    private SysFileService sysFileService;

    /**
     * 文件上传请求
     */
    @PostMapping("upload")
    public ResultModel<SysFile> upload(MultipartFile file)
    {
        try
        {
            // 上传并返回访问地址
            String url = sysFileService.uploadFile(file);
            SysFile sysFile = new SysFile();
            sysFile.setName(file.getOriginalFilename());
            sysFile.setUrl(url);
            return ResultModel.ok(sysFile);
        }
        catch (Exception e)
        {
            log.error("上传文件失败", e);
            return ResultModel.fail(e.getMessage());
        }
    }
}

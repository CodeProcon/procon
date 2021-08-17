package com.huangpuguang.file.oss.controller;

import com.huangpuguang.common.core.domain.ResultModel;
import com.huangpuguang.file.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 阿里OSS
 * @author procon
 * @date 2020-07-28 23:18
 */
@RestController
@CrossOrigin
@RequestMapping(value = "oss")
public class OssController {

    @Autowired
    private OssService ossService;
    /**
     *上传文件到oss
     */
    @PostMapping("upload")
    public ResultModel<String> upload(@RequestParam("file") MultipartFile file,
                                      @RequestParam("category") String category){
        String uploadUrl = ossService.upload(file,category);
        return ResultModel.ok(uploadUrl,"上传成功!");
    }

    /**
     * 下载文件到本地
     * @param ossKeyId oss文件保存名称
     * @param destDir  本地保存路径
     * @return com.huangpuguang.common.core.domain.R<java.lang.String>
     */
    @PostMapping("download")
    public ResultModel<String> download (@RequestParam("ossKeyId") String ossKeyId,
                                         @RequestParam("destDir") String destDir){
        ossService.download(ossKeyId,destDir);
        return ResultModel.ok("下载成功!");
    }

    @GetMapping("downloadAsStream")
    public void downloadAsStream (@RequestParam("ossKeyId") String ossKeyId,
                                                 HttpServletResponse response){
        ossService.downloadAsStream(ossKeyId,response);

    }

    /**
     *
     * @param fileKey oss文件名称
     * @param expires 过期时间
     * @return com.huangpuguang.common.core.domain.R<java.lang.String>
     */
    @GetMapping("getFileUrl")
    public ResultModel<String> getFileUrl(@RequestParam(value = "fileKey") String fileKey,
                                          @RequestParam(value = "expires") Integer expires){
        String fileUrl = ossService.getFileUrl(fileKey, expires);
        return ResultModel.ok(fileUrl,"获取链接成功!");
    }

    /**
     * 删除OSS文件
     * @param fileKey
     * @return com.huangpuguang.common.core.domain.R
     */
    @DeleteMapping("deleteFile/{fileKey}")
    public ResultModel deleteFile(@PathVariable(value = "fileKey") String fileKey){
        ossService.deleteFile(fileKey);
        return ResultModel.ok("删除文件成功!");
    }


}

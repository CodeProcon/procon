package com.huangpuguang.file.controller;

import com.huangpuguang.common.core.web.controller.BaseController;
import com.huangpuguang.common.core.web.domain.AjaxResult;
import com.huangpuguang.file.service.ProconFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>描述功能 </p>
 *
 * @author procon
 * Created on 2020/11/11.
 */
@Slf4j
@RestController
@RequestMapping("/CKEditor")
public class CKEditorController extends BaseController {

    @Value("${file.destPath}")
    private String destPath;

    private final ProconFileService fileService;

    public CKEditorController(ProconFileService fileService) {
        this.fileService = fileService;
    }

    /**
     * 新增文件
     */

    @PostMapping("/add")
    public String add(@RequestParam MultipartFile upload,
                          @RequestParam Integer uploadType,
                          HttpServletRequest request)
    {
        try {
            AjaxResult result = fileService.insertFile(upload, uploadType, destPath,false, request);
            Object data = result.get("data");
            return String.format("{\"url\":\"%s\"}", data);
        } catch (IOException e) {
            log.error("===文件上传异常==="+e.getMessage());
            return String.format("{\"error\":{\"message\":\"%s\"}}", e.getMessage());
        }
    }
}

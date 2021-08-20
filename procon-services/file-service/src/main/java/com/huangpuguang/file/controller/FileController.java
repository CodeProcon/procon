package com.huangpuguang.file.controller;


import com.huangpuguang.common.core.domain.ResultModel;
import com.huangpuguang.common.core.utils.poi.ExcelUtil;
import com.huangpuguang.common.core.web.controller.BaseController;
import com.huangpuguang.common.core.web.domain.AjaxResult;
import com.huangpuguang.common.core.web.page.TableDataInfo;
import com.huangpuguang.file.service.ProconFileService;
import com.huangpuguang.system.api.domain.ProconFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * 文件Controller
 *
 * @author procon
 * @date 2020-08-11
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController extends BaseController
{
    @Value("${file.destPath}")
    private String destPath;

    private final ProconFileService fileService;

    public FileController(ProconFileService fileService) {
        this.fileService = fileService;
    }

    /**
     * 查询文件列表
     */

    @GetMapping("/list")
    public TableDataInfo list(ProconFile file)
    {
        startPage();
        List<ProconFile> list = fileService.selectFileList(file);
        return getDataTable(list);
    }

    /**
     * 导出文件列表
     * @param response
     * @param file
     * @return void
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProconFile file) throws IOException
    {
        List<ProconFile> list = fileService.selectFileList(file);
        ExcelUtil<ProconFile> util = new ExcelUtil<ProconFile>(ProconFile.class);
        util.exportExcel(response, list, "file");
    }

    /**
     * 获取文件详细信息
     */

    @GetMapping(value = "/{id}")
    public ProconFile getInfo(@PathVariable("id") Long id)
    {
        return fileService.selectFileById(id);
    }

    /**
     * 新增文件
     */

    @PostMapping("/add")
    public AjaxResult add(@RequestParam MultipartFile upload,
                          @RequestParam Integer uploadType,
                          @RequestParam (required = false) boolean notSave,
                          HttpServletRequest request)
    {
        try {
            return fileService.insertFile(upload,uploadType,destPath,notSave,request);
        } catch (IOException e) {
           log.error("===文件上传异常==={}",e.getMessage());
           return AjaxResult.error(e.getMessage());
        }
    }


    @PostMapping("/save")
    public ResultModel<String> save(@RequestBody ProconFile file)
    {
        return fileService.saveFile(file);
    }

    /**
     * 修改文件
     */

    @PutMapping
    public AjaxResult edit(@RequestBody ProconFile file)
    {
        return toAjax(fileService.updateFile(file));
    }

    /**
     * 删除文件
     */

	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(fileService.deleteFileByIds(ids));
    }


    /**
     * 获取文件详细信息
     */

    @GetMapping(value = "/getInfos")
    public List<ProconFile> getInfos(@RequestParam String fileIds, @RequestParam String splitCode)
    {

        return fileService.selectFileByIds(fileIds,splitCode);
    }
}

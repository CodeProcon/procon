package com.huangpuguang.file.controller;


import com.huangpuguang.file.service.ProconFileSortService;
import com.huangpuguang.common.core.utils.poi.ExcelUtil;
import com.huangpuguang.common.core.web.controller.BaseController;
import com.huangpuguang.common.core.web.domain.AjaxResult;
import com.huangpuguang.common.core.web.page.TableDataInfo;
import com.huangpuguang.system.api.domain.ProconFileSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 文件分类Controller
 *
 * @author procon
 * @date 2020-08-11
 */
@RestController
@RequestMapping("/sort")
public class FileSortController extends BaseController
{
    @Autowired
    private ProconFileSortService fileSortService;

    /**
     * 查询文件分类列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ProconFileSort fileSort)
    {
        startPage();
        List<ProconFileSort> list = fileSortService.selectFileSortList(fileSort);
        return getDataTable(list);
    }

    /**
     * 导出文件分类列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProconFileSort fileSort) throws IOException
    {
        List<ProconFileSort> list = fileSortService.selectFileSortList(fileSort);
        ExcelUtil<ProconFileSort> util = new ExcelUtil<>(ProconFileSort.class);
        util.exportExcel(response, list, "sort");
    }

    /**
     * 获取文件分类详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(fileSortService.selectFileSortById(id));
    }

    /**
     * 新增文件分类
     */
    @PostMapping
    public AjaxResult add(@RequestBody ProconFileSort fileSort)
    {
        return toAjax(fileSortService.insertFileSort(fileSort));
    }

    /**
     * 修改文件分类
     */
    @PutMapping
    public AjaxResult edit(@RequestBody ProconFileSort fileSort)
    {
        return toAjax(fileSortService.updateFileSort(fileSort));
    }

    /**
     * 删除文件分类
     */
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(fileSortService.deleteFileSortByIds(ids));
    }
}

package com.huangpuguang.file.controller;


import com.huangpuguang.file.service.ProconStorageService;
import com.huangpuguang.common.core.utils.poi.ExcelUtil;
import com.huangpuguang.common.core.web.controller.BaseController;
import com.huangpuguang.common.core.web.domain.AjaxResult;
import com.huangpuguang.common.core.web.page.TableDataInfo;
import com.huangpuguang.system.api.domain.ProconStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * 存储信息Controller
 *
 * @author procon
 * @date 2020-08-11
 */
@RestController
@RequestMapping("/storage")
public class StorageController extends BaseController
{
    @Autowired
    private ProconStorageService storageService;

    /**
     * 查询存储信息列表
     */

    @GetMapping("/list")
    public TableDataInfo list(ProconStorage storage)
    {
        startPage();
        List<ProconStorage> list = storageService.selectStorageList(storage);
        return getDataTable(list);
    }

    /**
     * 导出存储信息列表
     */

    @PostMapping("/export")
    public void export(HttpServletResponse response, ProconStorage storage) throws IOException
    {
        List<ProconStorage> list = storageService.selectStorageList(storage);
        ExcelUtil<ProconStorage> util = new ExcelUtil<>(ProconStorage.class);
        util.exportExcel(response, list, "storage");
    }

    /**
     * 获取存储信息详细信息
     */

    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(storageService.selectStorageById(id));
    }

    /**
     * 新增存储信息
     */

    @PostMapping
    public AjaxResult add(@RequestBody ProconStorage storage)
    {
        return toAjax(storageService.insertStorage(storage));
    }

    /**
     * 修改存储信息
     */

    @PutMapping
    public AjaxResult edit(@RequestBody ProconStorage storage)
    {
        return toAjax(storageService.updateStorage(storage));
    }

    /**
     * 删除存储信息
     */

	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(storageService.deleteStorageByIds(ids));
    }
}

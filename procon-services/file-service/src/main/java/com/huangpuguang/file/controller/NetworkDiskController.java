package com.huangpuguang.file.controller;


import com.huangpuguang.file.service.ProconNetworkDiskService;
import com.huangpuguang.common.core.utils.poi.ExcelUtil;
import com.huangpuguang.common.core.web.controller.BaseController;
import com.huangpuguang.common.core.web.domain.AjaxResult;
import com.huangpuguang.common.core.web.page.TableDataInfo;
import com.huangpuguang.system.api.domain.ProconNetworkDisk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 网盘文件Controller
 *
 * @author procon
 * @date 2020-08-11
 */
@RestController
@RequestMapping("/disk")
public class NetworkDiskController extends BaseController
{
    @Autowired
    private ProconNetworkDiskService networkDiskService;

    /**
     * 查询网盘文件列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ProconNetworkDisk networkDisk)
    {
        startPage();
        List<ProconNetworkDisk> list = networkDiskService.selectNetworkDiskList(networkDisk);
        return getDataTable(list);
    }

    /**
     * 导出网盘文件列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProconNetworkDisk networkDisk) throws IOException
    {
        List<ProconNetworkDisk> list = networkDiskService.selectNetworkDiskList(networkDisk);
        ExcelUtil<ProconNetworkDisk> util = new ExcelUtil<>(ProconNetworkDisk.class);
        util.exportExcel(response, list, "disk");
    }

    /**
     * 获取网盘文件详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(networkDiskService.selectNetworkDiskById(id));
    }

    /**
     * 新增网盘文件
     */
    @PostMapping
    public AjaxResult add(@RequestBody ProconNetworkDisk networkDisk)
    {
        return toAjax(networkDiskService.insertNetworkDisk(networkDisk));
    }

    /**
     * 修改网盘文件
     */
    @PutMapping
    public AjaxResult edit(@RequestBody ProconNetworkDisk networkDisk)
    {
        return toAjax(networkDiskService.updateNetworkDisk(networkDisk));
    }

    /**
     * 删除网盘文件
     */

	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(networkDiskService.deleteNetworkDiskByIds(ids));
    }
}

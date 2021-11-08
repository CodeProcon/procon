package com.huangpuguang.blog.controller.admin;

import com.huangpuguang.blog.service.BlogWebConfigService;
import com.huangpuguang.blog.domain.BlogWebConfig;
import com.huangpuguang.common.core.utils.poi.ExcelUtil;
import com.huangpuguang.common.core.web.controller.BaseController;
import com.huangpuguang.common.core.web.domain.AjaxResult;
import com.huangpuguang.common.core.web.page.TableDataInfo;
import com.huangpuguang.common.log.annotation.Log;
import com.huangpuguang.common.log.enums.BusinessType;

import com.huangpuguang.common.security.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>网站配置</p>
 *
 * @author procon
 * @since 2020-11-26 11:42
 */
@RestController
@RequestMapping("admin/config")
public class WebConfigController extends BaseController {

   private final BlogWebConfigService blogWebConfigService;

    @Autowired
    public WebConfigController(BlogWebConfigService blogWebConfigService) {
        this.blogWebConfigService = blogWebConfigService;
    }

    /**
     * 查询博客配置列表
     */
    @RequiresPermissions("blog:config:list")
    @GetMapping("/list")
    public TableDataInfo list(BlogWebConfig blogWebConfig)
    {
        startPage();
        List<BlogWebConfig> list = blogWebConfigService.selectBlogWebConfigList(blogWebConfig);
        return getDataTable(list);
    }

    /**
     * 导出【博客配置】列表
     */
    @RequiresPermissions("blog:config:export")
    @Log(title = "导出【博客配置】列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlogWebConfig blogWebConfig) throws IOException
    {
        List<BlogWebConfig> list = blogWebConfigService.selectBlogWebConfigList(blogWebConfig);
        ExcelUtil<BlogWebConfig> util = new ExcelUtil<>(BlogWebConfig.class);
        util.exportExcel(response, list, "config");
    }

    /**
     * 获取【博客配置】详细信息
     */
    @RequiresPermissions("blog:config:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(blogWebConfigService.selectBlogWebConfigById(id));
    }

    /**
     * 新增【博客配置】
     */
    @RequiresPermissions("blog:config:add")
    @Log(title = "新增【博客配置】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlogWebConfig blogWebConfig)
    {
        return toAjax(blogWebConfigService.insertBlogWebConfig(blogWebConfig));
    }

    /**
     * 修改【博客配置】
     */

    @Log(title = "修改【博客配置】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlogWebConfig blogWebConfig)
    {
        return toAjax(blogWebConfigService.updateBlogWebConfig(blogWebConfig));
    }

    /**
     * 删除【博客配置】
     */
    @RequiresPermissions("blog:config:remove")
    @Log(title = "删除【博客配置】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blogWebConfigService.deleteBlogWebConfigByIds(ids));
    }
}

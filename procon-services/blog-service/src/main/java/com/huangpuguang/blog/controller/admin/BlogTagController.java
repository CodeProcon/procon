package com.huangpuguang.blog.controller.admin;

import com.huangpuguang.blog.domain.BlogTag;
import com.huangpuguang.blog.service.BlogTagService;
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
 * <p>标签管理</p>
 *
 * @author procon
 * Created on 2020/10/28.
 */
@RestController
@RequestMapping("/admin/blogTag")
public class BlogTagController extends BaseController {
    private final BlogTagService blogTagService;

    @Autowired
    public BlogTagController(BlogTagService blogTagService) {
        this.blogTagService = blogTagService;
    }

    /**
     * 获取博客标签列表
     * @param blogTag 参数
     * @return TableDataInfo
     */
    @GetMapping("getList")
    public TableDataInfo getList(BlogTag blogTag){
        List<BlogTag> blogTagList = blogTagService.selectBlogTagList(blogTag);
        return getDataTable(blogTagList);
    }

    /**
     * 导出标签列表
     */
    @RequiresPermissions("blog:tag:export")
    @Log(title = "标签", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlogTag blogTag) throws IOException
    {
        List<BlogTag> list = blogTagService.selectBlogTagList(blogTag);
        ExcelUtil<BlogTag> util = new ExcelUtil<>(BlogTag.class);
        util.exportExcel(response, list, "tag");
    }

    /**
     * 获取标签详细信息
     */
    @RequiresPermissions("blog:tag:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(blogTagService.selectBlogTagById(id));
    }

    /**
     * 新增标签
     */
    @RequiresPermissions("blog:tag:add")
    @Log(title = "标签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlogTag blogTag)
    {
        return toAjax(blogTagService.insertBlogTag(blogTag));
    }

    /**
     * 修改标签
     */
    @RequiresPermissions("blog:tag:edit")
    @Log(title = "标签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlogTag blogTag)
    {
        return toAjax(blogTagService.updateBlogTag(blogTag));
    }

    /**
     * 删除标签
     */
    @RequiresPermissions("blog:tag:remove")
    @Log(title = "标签", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blogTagService.deleteBlogTagByIds(ids));
    }
}

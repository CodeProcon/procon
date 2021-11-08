package com.huangpuguang.blog.controller.admin;

import com.huangpuguang.blog.domain.BlogSort;
import com.huangpuguang.blog.service.BlogSortService;
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
 * <p>博客排序controller</p>
 *
 * @author procon
 * Created on 2020/10/30.
 */
@RestController
@RequestMapping("admin/blogSort")
public class BlogSortController extends BaseController {
    private final BlogSortService blogSortService;
    @Autowired
    public BlogSortController(BlogSortService blogSortService) {
        this.blogSortService = blogSortService;
    }

    /**
     * 获取分类列表
     * @param blogSort 参数
     * @return blogSortList
     */
    @GetMapping("/getList")
    public TableDataInfo getBlogSortList(BlogSort blogSort){
        List<BlogSort> blogSortList = blogSortService.selectBlogSortList(blogSort);
        return getDataTable(blogSortList);
    }

    /**
     * 导出博客分类列表
     */
    @RequiresPermissions("blog:sort:export")
    @Log(title = "博客分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlogSort blogSort) throws IOException
    {
        List<BlogSort> list = blogSortService.selectBlogSortList(blogSort);
        ExcelUtil<BlogSort> util = new ExcelUtil<>(BlogSort.class);
        util.exportExcel(response, list, "sort");
    }

    /**
     * 获取博客分类详细信息
     */
    @RequiresPermissions("blog:sort:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(blogSortService.selectBlogSortById(id));
    }

    /**
     * 新增博客分类
     */
    @RequiresPermissions("blog:sort:add")
    @Log(title = "博客分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlogSort blogSort)
    {
        return toAjax(blogSortService.insertBlogSort(blogSort));
    }

    /**
     * 修改博客分类
     */
    @RequiresPermissions("blog:sort:edit")
    @Log(title = "博客分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlogSort blogSort)
    {
        return toAjax(blogSortService.updateBlogSort(blogSort));
    }

    /**
     * 删除博客分类
     */
    @RequiresPermissions("blog:sort:remove")
    @Log(title = "博客分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blogSortService.deleteBlogSortByIds(ids));
    }

}

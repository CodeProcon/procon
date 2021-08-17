package com.huangpuguang.blog.controller.web;

import com.huangpuguang.blog.domain.BlogSort;
import com.huangpuguang.blog.service.BlogSortService;
import com.huangpuguang.common.core.web.controller.BaseController;
import com.huangpuguang.common.core.web.domain.AjaxResult;
import com.huangpuguang.common.core.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 博客分类相关接口
 *
 * @author procon
 * @version 1.0
 * @since 2020/12/9
 */
@RestController
@RequestMapping("/web/classify")
public class ClassifyController extends BaseController {
    private final BlogSortService sortService;

    @Autowired
    public ClassifyController(BlogSortService sortService) {
        this.sortService = sortService;
    }


    /**
     * 获取分类列表
     * @param blogSort 参数
     * @return blogSortList
     */
    @GetMapping("/getBlogSortList")
    public TableDataInfo getBlogSortList(BlogSort blogSort){
        startPage();
        List<BlogSort> blogSortList = sortService.selectBlogSortList(blogSort);
        return getDataTable(blogSortList);
    }

    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sortService.selectBlogSortById(id));
    }



}

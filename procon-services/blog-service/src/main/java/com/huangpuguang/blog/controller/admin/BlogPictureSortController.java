package com.huangpuguang.blog.controller.admin;

import com.huangpuguang.blog.domain.BlogPictureSort;
import com.huangpuguang.blog.service.BlogPictureSortService;
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
 * <p>描述功能 </p>
 *
 * @author procon
 * Created on 2020/11/11.
 */
@RestController
@RequestMapping("/picSort")
public class BlogPictureSortController extends BaseController {
    private final BlogPictureSortService blogPictureSortService;

    @Autowired
    public BlogPictureSortController(BlogPictureSortService blogPictureSortService) {
        this.blogPictureSortService = blogPictureSortService;
    }

    @GetMapping("/getList")
    public TableDataInfo getBlogSortList(BlogPictureSort blogSort){
        startPage();
        List<BlogPictureSort> blogSortList = blogPictureSortService.selectBlogPictureSortList(blogSort);
        return getDataTable(blogSortList);
    }

    @GetMapping("/get/{id}")
    public AjaxResult getBlogList(@PathVariable("id") Long id){
        BlogPictureSort blogPictureSort = blogPictureSortService.selectBlogPictureSortById(id);
        return AjaxResult.success(blogPictureSort);
    }
}

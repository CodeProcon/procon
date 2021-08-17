package com.huangpuguang.blog.controller.web;

import com.huangpuguang.blog.service.BlogContentService;
import com.huangpuguang.common.core.web.controller.BaseController;
import com.huangpuguang.common.core.web.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 博客归档相关接口
 *
 * @author procon
 * @version 1.0
 * @since 2020/12/9
 */
@Slf4j
@RestController
@RequestMapping("/web/archive")
public class ArchiveController extends BaseController {
    private final BlogContentService blogService;

    @Autowired
    public ArchiveController(BlogContentService blogService) {
        this.blogService = blogService;
    }

    /**
     * 获取归档的信息
     */
    @GetMapping("/getArchiveList")
    public AjaxResult getArchiveList() {
        log.info("获取归档日期");
        return AjaxResult.success(blogService.getBlogTimeSortList());
    }

    /**
     * 通过月份获取文章
     * @param monthDate
     * @return com.huangpuguang.common.core.web.domain.AjaxResult
     */
    @GetMapping("/getArticleByMonth")
    public AjaxResult getArticleByMonth(@RequestParam(name = "monthDate", required = false) String monthDate) {
        log.info("通过月份获取文章列表");
        return AjaxResult.success(blogService.getArticleByMonth(monthDate));
    }
}

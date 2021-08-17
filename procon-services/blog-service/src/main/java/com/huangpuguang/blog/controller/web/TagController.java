package com.huangpuguang.blog.controller.web;

import com.huangpuguang.blog.domain.BlogTag;
import com.huangpuguang.blog.service.BlogContentService;
import com.huangpuguang.blog.service.BlogTagService;
import com.huangpuguang.blog.domain.BlogContent;
import com.huangpuguang.blog.vo.BlogContentVo;
import com.huangpuguang.common.core.web.controller.BaseController;
import com.huangpuguang.common.core.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 标签相关接口
 *
 * @author procon
 * @version 1.0
 * @since 2020/12/9
 */
@RestController
@RequestMapping("web/tag")
public class TagController extends BaseController {

    private final BlogTagService tagService;
    private final BlogContentService blogContentService;

    @Autowired
    public TagController(BlogTagService tagService, BlogContentService blogContentService, BlogContentService blogContentService1) {
        this.tagService = tagService;
        this.blogContentService = blogContentService1;
    }

    @GetMapping("/getList")
    public TableDataInfo getList(BlogTag blogTag){
        List<BlogTag> blogTagList = tagService.selectBlogTagList(blogTag);
        return getDataTable(blogTagList);
    }

    @GetMapping("/getBlogList")
    public TableDataInfo getBlogList(BlogContent blogContent) {
        List<BlogContentVo> blogContentList = blogContentService.selectBlogContentList(blogContent,1);
        startPage();
        List<BlogContentVo> blogContents = blogContentService.selectBlogContentList(blogContent,1);
        TableDataInfo dataTable = getDataTable(blogContents);
        dataTable.setTotal(blogContentList.size());
        return dataTable;
    }
}

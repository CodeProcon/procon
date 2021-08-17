package com.huangpuguang.blog.controller.admin;

import com.huangpuguang.blog.domain.BlogContent;
import com.huangpuguang.blog.service.BlogContentService;
import com.huangpuguang.blog.vo.BlogContentVo;
import com.huangpuguang.common.core.web.controller.BaseController;
import com.huangpuguang.common.core.web.domain.AjaxResult;
import com.huangpuguang.common.core.web.page.TableDataInfo;
import com.huangpuguang.common.security.annotation.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>博客功能管理</p>
 *
 * @author procon
 * Created on 2020/10/28.
 */
@RestController
@RequestMapping("admin/blogMgr")
public class BlogMgrController extends BaseController {
    private final BlogContentService blogContentService;

    @Autowired
    public BlogMgrController(BlogContentService blogContentService) {
        this.blogContentService = blogContentService;

    }


    /**
     * 获取博客列表
     *
     * @param blogContent 参数
     * @return blogContents
     */
    @PreAuthorize(hasPermi = "blog:content:list")
    @GetMapping("/getList")
    public TableDataInfo getBlogList(BlogContent blogContent) {
        startPage();
        List<BlogContentVo> blogContents = blogContentService.selectBlogContentList(blogContent,null);
        return getDataTable(blogContents);
    }

    /**
     * 获取博客详情
     *
     * @param id 参数
     * @return blogContents
     */
    @PreAuthorize(hasPermi = "blog:content:query")
    @GetMapping("/get/{id}")
    public AjaxResult getBlogList(@PathVariable("id") Long id) {
        BlogContent blogContent = blogContentService.selectBlogContentById(id);
        return AjaxResult.success(blogContent);
    }

    /**
     * 添加博客
     *
     * @param blogContent 博客实体
     * @return add
     */
    @PreAuthorize(hasPermi = "blog:content:add")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody BlogContent blogContent) {
        return AjaxResult.success(blogContentService.insertBlogContent(blogContent));
    }

    /**
     * <p>更新博客</p>
     *
     * @param blogContent 博客实体
     * @return com.huangpuguang.common.core.web.domain.AjaxResult
     * @author procon
     * @time 2020/11/24 16:55
     */
    @PreAuthorize(hasPermi = "blog:content:edit")
    @PostMapping("/update")
    public AjaxResult update(@RequestBody BlogContent blogContent) {
        return AjaxResult.success(blogContentService.updateBlogContent(blogContent));
    }

    /**
     * <p>根据id删除</p>
     * @param ids ids
     * @return com.huangpuguang.common.core.web.domain.AjaxResult
     * @author procon
     * @time 2020/11/24 17:17
     */
    @PreAuthorize(hasPermi = "blog:content:remove")
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") Long[] ids) {
        return AjaxResult.success(blogContentService.deleteBlogContentByIds(ids));
    }

    @PreAuthorize(hasPermi = "blog:content:edit")
    @PostMapping("/editBatch")
    public  AjaxResult editBatch(@RequestBody List<BlogContentVo> blogVOList){
        return AjaxResult.success(blogContentService.editBatch(blogVOList));
    }
}

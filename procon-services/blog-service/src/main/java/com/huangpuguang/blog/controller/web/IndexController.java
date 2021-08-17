package com.huangpuguang.blog.controller.web;

import com.huangpuguang.blog.domain.BlogContent;
import com.huangpuguang.blog.domain.BlogLink;
import com.huangpuguang.blog.domain.BlogTag;
import com.huangpuguang.blog.service.BlogContentService;
import com.huangpuguang.blog.service.BlogLinkService;
import com.huangpuguang.blog.service.BlogTagService;
import com.huangpuguang.blog.service.BlogWebConfigService;
import com.huangpuguang.blog.vo.BlogContentVo;
import com.huangpuguang.common.core.web.controller.BaseController;
import com.huangpuguang.common.core.web.domain.AjaxResult;
import com.huangpuguang.common.core.web.page.TableDataInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>博客主页</p>
 * @author procon
 * Created on 2020/10/28.
 */
@RestController
@RequestMapping("web/index")
public class IndexController extends BaseController {

    private final BlogWebConfigService blogWebConfigService;
    private final BlogContentService blogContentService;
    private final BlogTagService blogTagService;
    private final BlogLinkService blogLinkService;

    @Autowired
    public IndexController(BlogWebConfigService blogWebConfigService, BlogContentService blogContentService, BlogTagService blogTagService, BlogLinkService blogLinkService) {
        this.blogWebConfigService = blogWebConfigService;
        this.blogContentService = blogContentService;
        this.blogTagService = blogTagService;
        this.blogLinkService = blogLinkService;
    }

    /**
     * 获取网站配置
     * @return 网站配置
     */
    @GetMapping("/getWebConfig")
    public AjaxResult getWebConfig(){
        startPage();
        return AjaxResult.success(blogWebConfigService.selectBlogWebConfig());
    }

    /**
     * 获取博客列表
     * @param blogContent  参数
     * @return 博客列表
     */
    @GetMapping("/getBlogList")
    public TableDataInfo getBlogList(BlogContent blogContent) {
        //TODO: pageHelper 分页问题
        List<BlogContentVo> blogContentList = blogContentService.selectBlogContentList(blogContent,null);
        startPage();
        List<BlogContentVo> blogContents = blogContentService.selectBlogContentList(blogContent,null);
        TableDataInfo dataTable = getDataTable(blogContents);
        dataTable.setTotal(blogContentList.size());
        return dataTable;
    }


    @GetMapping("/get/{id}")
    public AjaxResult getBlogById(@PathVariable("id") Long id) {
        BlogContent blogContent = blogContentService.selectBlogContentById(id);
        return AjaxResult.success(blogContent);
    }

    /**
     * 更新博客链接点击数
     * @param id 博客id
     * @return com.huangpuguang.common.core.web.domain.AjaxResult
     */
    @GetMapping("/addBlogClickCount/{id}")
    public AjaxResult addBlogClickCount(@PathVariable("id") Long id){
        BlogContentVo blogContentVo = blogContentService.selectBlogContentById(id);
        BlogContent blogContent = new BlogContent();
        BeanUtils.copyProperties(blogContentVo,blogContent);
        Long clickCount = blogContent.getClickCount();
        //增加点击数
        clickCount = clickCount + 1;
        blogContent.setClickCount(clickCount);
        blogContentService.updateBlogContent(blogContent);
        return AjaxResult.success("增加博客点击数成功");
    }

    /**
     * 获取标签
     * @return 获取标签列表
     */
    @GetMapping("getTagList")
    public TableDataInfo getTagList(BlogTag blogTag){
        //分页
        startPage();
        return getDataTable(blogTagService.selectBlogTagList(blogTag));
    }

    /**
     * 获取友情链接
     * @param blogLink 链接参数
     * @return com.huangpuguang.common.core.web.page.TableDataInfo
     */
    @GetMapping("getLinkList")
    public TableDataInfo getLinkList(BlogLink blogLink){
        startPage();
        return getDataTable(blogLinkService.selectBlogLinkList(blogLink));
    }

    /**
     * 更新友情链接点击数
     * @param id 友情链接id
     * @return com.huangpuguang.common.core.web.domain.AjaxResult
     */
    @GetMapping("/addLinkClickCount/{id}")
    public AjaxResult addLinkClickCount(@PathVariable("id") Long id){
        BlogLink blogLink = blogLinkService.selectBlogLinkById(id);
        Long clickCount = blogLink.getClickCount();
        //增加点击数
        clickCount = clickCount + 1;
        blogLink.setClickCount(clickCount);
        blogLinkService.updateBlogLink(blogLink);
        return AjaxResult.success("增加友情链接点击数成功");
    }
}

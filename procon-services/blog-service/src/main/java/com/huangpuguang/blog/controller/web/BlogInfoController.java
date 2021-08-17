package com.huangpuguang.blog.controller.web;

import cn.hutool.core.util.ObjectUtil;
import com.huangpuguang.blog.domain.BlogContent;
import com.huangpuguang.blog.service.BlogContentService;
import com.huangpuguang.blog.vo.BlogContentVo;
import com.huangpuguang.common.core.constant.Constants;
import com.huangpuguang.common.core.utils.FileUtils;
import com.huangpuguang.common.core.utils.ServletUtils;
import com.huangpuguang.common.core.utils.ip.IpUtils;
import com.huangpuguang.common.core.web.controller.BaseController;
import com.huangpuguang.common.core.web.domain.AjaxResult;
import com.huangpuguang.common.core.web.page.TableDataInfo;
import com.huangpuguang.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 博客详情相关接口
 *
 * @author procon
 * @version 1.0
 * @since 2020/12/9
 */
@RestController
@RequestMapping("web/blogInfo")
public class BlogInfoController extends BaseController {

    @Value("${blog.copyRight}")
    private String copyRight;
    @Autowired
    private BlogContentService blogContentService;
    @Autowired
    private RedisService redisService;


    /**
     * 获取博客列表
     *
     * @param blogContent 参数
     * @return blogContents
     */
    @GetMapping("/getList")
    public TableDataInfo getBlogList(BlogContent blogContent) {
        startPage();
        List<BlogContentVo> blogContents = blogContentService.selectBlogContentList(blogContent,null);
        return getDataTable(blogContents);
    }

    /**
     * 通过id获取博客信息
     * @param id 博客id
     * @return com.huangpuguang.common.core.web.domain.AjaxResult
     */
    @GetMapping("/get/{id}")
    public AjaxResult getBlogById(@PathVariable("id") Long id) {
        //获取请求的ip地址
        String ip = IpUtils.getIpAddr(Objects.requireNonNull(ServletUtils.getRequest()));
        BlogContentVo blog= blogContentService.selectBlogContentById(id);
        // md转html
        String htmlContent = FileUtils.markdownToHtml(blog.getContent());
        //htmlContent.replace()
        blog.setContent(htmlContent);

        //设置版权信息
        blog.setCopyright(copyRight);
        //从Redis取出数据，判断该用户是否点击过
        Object jsonResult = redisService.getCacheObject("BLOG_CLICK:" + ip + Constants.SYMBOL_WELL + blog.getId());
        if (ObjectUtil.isNull(jsonResult)) {
            //给博客点击数增加
            Long clickCount = blog.getClickCount() + 1;
            blog.setClickCount(clickCount);
            blogContentService.updateBlogContent(blog);
            //将该用户点击记录存储到redis中, 24小时后过期
            redisService.setCacheObject("BLOG_CLICK:" + ip + Constants.SYMBOL_WELL + blog.getId(),blog.getClickCount(),24L, TimeUnit.HOURS);
        }
        return AjaxResult.success("获取博客信息成功",blog);
    }

    /**
     * 通过id获取博客点赞数
     * @param id 博客id
     * @return com.huangpuguang.common.core.web.domain.AjaxResult
     */
    @GetMapping("/getBlogPraiseCount/{id}")
    public AjaxResult getBlogPraiseCount(@PathVariable("id") Long id) {
        Integer praiseCount = blogContentService.getBlogPraiseCountById(id);
        return AjaxResult.success("获取博客点赞数",praiseCount);
    }



    /**
     * 获取相关博客
     * @param id 博客id
     * @return com.huangpuguang.common.core.web.page.TableDataInfo
     */
    @GetMapping("/getSameBlog/{id}")
    public TableDataInfo getSameBlog(@PathVariable("id") Long id){
        BlogContentVo blogContentVo = blogContentService.selectBlogContentById(id);
        BlogContent blogContent = new BlogContentVo();
        blogContent.setBlogSortId(blogContentVo.getBlogSortId());
        List<BlogContentVo> blogContentList = blogContentService.selectBlogContentList(blogContent,null);
        startPage();
        List<BlogContentVo> blogContents = blogContentService.selectBlogContentList(blogContent,null);
        blogContents.removeIf(blogContent1 -> blogContent1.getId().equals(blogContent.getId()));
        TableDataInfo dataTable = getDataTable(blogContents);
        dataTable.setTotal(blogContentList.size()-1);
        return dataTable;
    }
}

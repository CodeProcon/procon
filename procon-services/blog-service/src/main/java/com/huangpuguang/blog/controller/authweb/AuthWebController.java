package com.huangpuguang.blog.controller.authweb;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.huangpuguang.blog.domain.BlogComment;
import com.huangpuguang.blog.domain.BlogCommentReport;
import com.huangpuguang.blog.domain.BlogContent;
import com.huangpuguang.blog.domain.BlogWebConfig;
import com.huangpuguang.blog.service.BlogCommentReportService;
import com.huangpuguang.blog.service.BlogCommentService;
import com.huangpuguang.blog.service.BlogContentService;
import com.huangpuguang.blog.service.BlogWebConfigService;
import com.huangpuguang.common.core.constant.BlogConstants;
import com.huangpuguang.common.security.utils.SecurityUtils;
import com.huangpuguang.common.core.utils.StringUtils;
import com.huangpuguang.common.core.web.controller.BaseController;
import com.huangpuguang.common.core.web.domain.AjaxResult;
import com.huangpuguang.system.api.RemoteUserService;
import com.huangpuguang.system.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 需要登录才能使用的功能
 *
 * @author procon
 * Created on 2020/12/14
 */

@RestController
@RequestMapping("authWeb/blog")
public class AuthWebController extends BaseController {
    @Autowired
    private BlogCommentService blogCommentService;
    @Autowired
    private BlogWebConfigService webConfigService;
    @Autowired
    private BlogContentService blogService;
    @Autowired
    private RemoteUserService remoteUserService;

    private BlogCommentReportService reportService;

    /**
     * 给博客点赞
     * @param id 博客id
     * @return com.huangpuguang.common.core.web.domain.AjaxResult
     */
    @GetMapping("/praiseBlogById/{id}")
    public AjaxResult praiseBlogById(@PathVariable("id") Long id) {
        Long praiseBlog= blogService.praiseBlogById(id);
        Long num20 = -20L;
        Long num10 = -10L;
        if(praiseBlog.equals(num10)){
            return AjaxResult.error("你已经点过赞了");
        }else if(praiseBlog.equals(num20)){
            return AjaxResult.error("登录后才能点赞哦");
        }
        return AjaxResult.success("点赞成功！",praiseBlog);
    }

    /**
     * 查询用户评论回复列表
     * @param comment 评论
     * @param request 请求
     * @return com.huangpuguang.common.core.web.domain.AjaxResult
     */
    @RequestMapping("comment/getListByUser")
    public AjaxResult getListByUser(BlogComment comment, HttpServletRequest request){
        if(comment.getUserId() == null || comment.getToUserId() == null){
            return  AjaxResult.error("请求参数错误!");
        }
        Map<String, Object> commentMap = blogCommentService.getListByUser(request, comment);
        return AjaxResult.success("获取成功",commentMap);
    }

    @RequestMapping("comment/getPraiseListByUser")
    public AjaxResult getPraiseListByUser(BlogComment comment){
        comment.setCommentType(BlogConstants.PRAISE);
        startPage();
        List<BlogComment> praiseList = blogCommentService.getPraiseListByUser(comment);
        return AjaxResult.success("获取成功",getDataTable(praiseList));
    }

    @PostMapping("comment/add")
    public AjaxResult add(@RequestBody BlogComment comment){
        BlogWebConfig webConfig = webConfigService.selectBlogWebConfig();
        // 判断是否开启全局评论功能
        if (BlogConstants.CAN_NOT_COMMENT.equals(webConfig.getStartComment())) {
            return AjaxResult.error("系统未启用评论功能");
        }
        // 判断博客是否开启评论功能
        if (comment.getBlogId() != null) {
            BlogContent blog = blogService.selectBlogContentById(comment.getBlogId());
            if (BlogConstants.CAN_NOT_COMMENT.equals(blog.getStartComment().toString())) {
                return AjaxResult.error("博客未启用评论功能");
            }
        }
        Long userId = SecurityUtils.getUserId();
        if(userId == null){
            return AjaxResult.error("无效的用户信息");
        }
        List<SysUser> allUser = remoteUserService.getAllUser();
        SysUser sysUser = new SysUser();
        if (CollectionUtils.isNotEmpty(allUser)) {
            for (SysUser user : allUser) {
                if (user.getUserId().toString().equals(userId.toString())) {
                    sysUser = user;

                }
            }
        }
        // 判断字数是否超过限制
        if (comment.getContent().length() > BlogConstants.ONE_ZERO_TWO_FOUR) {
            return AjaxResult.error("评论不能超过1024个字符!");
        }
        if (StringUtils.isEmpty(comment.getContent())) {
            return AjaxResult.error("评论不能为空");
        }
        BlogComment blogComment = blogCommentService.add(comment, userId);
        blogComment.setUser(sysUser);
        return AjaxResult.success("添加评论成功",blogComment);
    }

    @PostMapping("comment/report")
    public AjaxResult report(@RequestBody BlogComment comment){
        BlogComment blogComment = blogCommentService.selectBlogCommentById(comment.getId());
        // 判断评论是否被删除
        if (blogComment == null || blogComment.getStatus() == BlogConstants.DISABLED) {
            return AjaxResult.error("评论不存在");
        }
        // 判断举报的评论是否是自己的
        if (comment.getUserId().equals(blogComment.getUserId())) {
            return AjaxResult.error("不能举报自己的评论");
        }
        // 查看该用户是否重复举报该评论
        BlogCommentReport commentReport = new BlogCommentReport();
        commentReport.setUserId(blogComment.getUserId());
        commentReport.setReportCommentId(blogComment.getId());

        List<BlogCommentReport> commentReportList = reportService.selectBlogCommentReportList(commentReport);
        if (!commentReportList.isEmpty()) {
            return AjaxResult.error("不能重复进行举报");
        }
        blogCommentService.reportComment(blogComment);
        return  AjaxResult.success("举报成功");
    }

    @DeleteMapping("comment/delete")
    public AjaxResult delete(@RequestBody BlogComment blogComment){
        BlogComment comment = blogCommentService.selectBlogCommentById(blogComment.getId());
        // 判断该评论是否能够删除
        if (!comment.getUserId().equals(blogComment.getUserId())) {
            return AjaxResult.success("没有删除权限");
        }
        blogCommentService.deleteComment(comment);
        return AjaxResult.success("删除成功");
    }
}

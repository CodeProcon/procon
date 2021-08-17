package com.huangpuguang.blog.controller.web;

import com.huangpuguang.blog.domain.BlogComment;
import com.huangpuguang.blog.service.BlogCommentService;
import com.huangpuguang.common.core.web.controller.BaseController;
import com.huangpuguang.common.core.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 评论相关接口
 *
 * @author procon
 * @version 1.0
 * @since 2020/12/8
 */
@RestController
@RequestMapping("web/comment")
public class CommentController extends BaseController {

    @Autowired
    private BlogCommentService blogCommentService;

    /**
     * 获取评论列表
     * @param comment 请求参数
     * @return com.huangpuguang.common.core.web.domain.AjaxResult
     */
    @RequestMapping("getList")
    public TableDataInfo getList(BlogComment comment){
        return blogCommentService.getBlogCommentResp(comment);
    }

}

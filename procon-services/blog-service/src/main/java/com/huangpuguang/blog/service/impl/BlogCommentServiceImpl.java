package com.huangpuguang.blog.service.impl;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.github.pagehelper.PageInfo;
import com.huangpuguang.blog.domain.BlogComment;
import com.huangpuguang.blog.domain.BlogCommentReport;
import com.huangpuguang.blog.mapper.BlogCommentMapper;
import com.huangpuguang.blog.mapper.BlogCommentReportMapper;
import com.huangpuguang.blog.service.BlogCommentService;
import com.huangpuguang.blog.service.BlogContentService;
import com.huangpuguang.blog.vo.BlogContentVo;
import com.huangpuguang.common.core.constant.BlogConstants;
import com.huangpuguang.common.core.constant.Constants;
import com.huangpuguang.common.core.constant.HttpStatus;
import com.huangpuguang.common.core.utils.DateUtils;
import com.huangpuguang.common.core.web.page.PageOper;
import com.huangpuguang.common.core.web.page.TableDataInfo;
import com.huangpuguang.system.api.RemoteUserService;
import com.huangpuguang.system.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 评论Service业务层处理
 *
 * @author procon
 * @date 2020-10-27
 */
@Service
public class BlogCommentServiceImpl extends PageOper<BlogComment> implements BlogCommentService {
    @Autowired
    private BlogCommentMapper blogCommentMapper;

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private BlogContentService blogContentService;

    @Autowired
    private BlogCommentReportMapper commentReportMapper;

    /**
     * 查询评论
     *
     * @param id 评论ID
     * @return 评论
     */
    @Override
    public BlogComment selectBlogCommentById(Long id) {
        return blogCommentMapper.selectBlogCommentById(id);
    }

    /**
     * 查询评论列表
     *
     * @param blogComment 评论
     * @return 评论
     */
    @Override
    public List<BlogComment> selectBlogCommentList(BlogComment blogComment) {

        return blogCommentMapper.selectBlogCommentList(blogComment);
    }

    /**
     * 新增评论
     *
     * @param blogComment 评论
     * @return 结果
     */
    @Override
    public int insertBlogComment(BlogComment blogComment) {
        return blogCommentMapper.insertBlogComment(blogComment);
    }

    /**
     * 修改评论
     *
     * @param blogComment 评论
     * @return 结果
     */
    @Override
    public int updateBlogComment(BlogComment blogComment) {
        blogComment.setUpdateTime(DateUtils.getNowDate());
        return blogCommentMapper.updateBlogComment(blogComment);
    }

    /**
     * 批量删除评论
     *
     * @param ids 需要删除的评论ID
     * @return 结果
     */
    @Override
    public int deleteBlogCommentByIds(Long[] ids) {
        return blogCommentMapper.deleteBlogCommentByIds(ids);
    }

    /**
     * 删除评论信息
     *
     * @param id 评论ID
     * @return 结果
     */
    @Override
    public int deleteBlogCommentById(Long id) {
        return blogCommentMapper.deleteBlogCommentById(id);
    }

    /**
     * 查询评论列表
     *
     * @param blogComment 参数
     * @return com.huangpuguang.common.core.web.page.TableDataInfo
     */
    @Override
    public TableDataInfo getBlogCommentResp(BlogComment blogComment) {
        blogComment.setFirstCommentFlag(Constants.NO);
        startPage();
        //查询一级评论需要按照一级评论分页
        List<BlogComment> firstBlogCommentList = blogCommentMapper.selectFirstBlogCommentList(blogComment);
        PageInfo<BlogComment> pageInfo = new PageInfo<>(firstBlogCommentList);
        long total = pageInfo.getTotal();
        TableDataInfo rspData = new TableDataInfo();
        rspData.setTotal(total);

        //一级评论id集合
        List<String> firstIdList = new ArrayList<>();
        firstBlogCommentList.forEach(item -> firstIdList.add(item.getId().toString()));
        if (CollectionUtils.isNotEmpty(firstIdList)) {
            //查询一级评论下的子评论
            blogComment.setFirstCommentFlag(Constants.NUM_ONE);
            List<BlogComment> notFirstBlogCommentList = blogCommentMapper.selectFirstBlogCommentList(blogComment);
            //合并评论
            firstBlogCommentList.addAll(notFirstBlogCommentList);
        }


        // 设置一级评论下的子评论
        Map<String, List<BlogComment>> toCommentListMap = new HashMap<>(16);
        for (int a = 0; a < firstBlogCommentList.size(); a++) {
            List<BlogComment> tempList = new ArrayList<>();
            for (BlogComment comment : firstBlogCommentList) {
                if (firstBlogCommentList.get(a).getId().equals(comment.getToId())) {
                    tempList.add(comment);
                }
            }
            toCommentListMap.put(firstBlogCommentList.get(a).getId().toString(), tempList);
        }
        List<BlogComment> firstComment = new ArrayList<>();
        firstBlogCommentList.forEach(item -> {
            if (item.getToId() != null) {
                firstComment.add(item);
            }
        });
        List<BlogComment> userMap = getCommentListWithUser(firstComment);
        List<BlogComment> commentReply = getCommentReply(userMap, toCommentListMap);
        rspData.setRows(commentReply);
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        return rspData;
    }

    /**
     * 获取用户的评论列表
     *
     * @param request     请求request
     * @param blogComment 用户
     * @return java.lang.String
     */
    @Override
    public Map<String, Object> getListByUser(HttpServletRequest request, BlogComment blogComment) {
        // 查找出 我的评论 和 我的回复进行分页
        List<BlogComment> blogCommentList = blogCommentMapper.selectUserCommentList(blogComment);
        // 用户信息设置
        List<BlogComment> blogComments = getCommentListWithUser(blogCommentList);
        //评论列表
        List<BlogComment> commentList = new ArrayList<>();
        //回复列表
        List<BlogComment> replyList = new ArrayList<>();
        blogComments.forEach(item -> {
            if (item.getUserId() != null) {
                commentList.add(item);
            }
            if (item.getToUserId() != null) {
                replyList.add(item);
            }
        });
        Map<String, Object> resultMap = new HashMap<>(16);
        resultMap.put(BlogConstants.COMMENT_LIST, commentList);
        resultMap.put(BlogConstants.REPLY_LIST, replyList);
        return resultMap;
    }

    /**
     * 获取用户点赞列表
     *
     * @param blogComment 参数
     * @return com.huangpuguang.common.core.web.page.TableDataInfo
     */
    @Override
    public List<BlogComment> getPraiseListByUser(BlogComment blogComment) {
        List<BlogComment> praiseList = blogCommentMapper.selectBlogCommentList(blogComment);
        //获取博客id列表
        praiseList.forEach(item -> {
            if (item.getBlogId() != null) {
                BlogContentVo blogContentVo = blogContentService.selectBlogContentById(item.getBlogId());
                item.setBlog(blogContentVo);
            }
        });
        return praiseList;
    }


    @Override
    public BlogComment add(BlogComment blogComment, Long userId) {
        BlogComment comment = new BlogComment();
        comment.setUserId(userId);
        comment.setToId(blogComment.getToId());
        comment.setSource(blogComment.getSource());
        comment.setBlogId(blogComment.getBlogId());
        comment.setContent(blogComment.getContent());
        comment.setCreateTime(DateUtils.getNowDate());

        // 当该评论不是一级评论时，需要设置一级评论UID字段
        if (blogComment.getToId() != null) {
            BlogComment toComment = blogCommentMapper.selectBlogCommentById(blogComment.getToId());
            // 表示 toComment是非一级评论
            if (toComment != null && toComment.getFirstCommentId() != null) {
                comment.setFirstCommentId(toComment.getFirstCommentId());
            } else {
                // 表示父评论是一级评论，直接获取UID
                if (toComment != null) {
                    comment.setFirstCommentId(toComment.getId());
                }
            }
            comment.setToId(blogComment.getToId());
            comment.setToUserId(toComment != null?toComment.getUserId():null);
        }
        blogCommentMapper.insertBlogComment(comment);
        return comment;
    }

    /**
     * 获取评论所有回复
     *
     * @param list             博客列表
     * @param toCommentListMap 评论下的子评论集合
     * @return 评论列表
     */
    private List<BlogComment> getCommentReply(List<BlogComment> list, Map<String, List<BlogComment>> toCommentListMap) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        } else {
            List<BlogComment> commentListWithUser = getCommentListWithUser(list);
            commentListWithUser.forEach(item -> {
                String commentId = item.getId().toString();
                List<BlogComment> replyCommentList = toCommentListMap.get(commentId);
                List<BlogComment> replyComments = getCommentReply(replyCommentList, toCommentListMap);

                item.setReplyList(replyComments);
            });
            return list;
        }
    }

    /**
     * 获取某条评论下的所有子评论
     */
    private void getToCommentList(BlogComment comment, List<BlogComment> commentList, List<BlogComment> resultList) {
        if (comment == null) {
            return;
        }
        Long commentId = comment.getId();
        for (BlogComment item : commentList) {
            if (commentId.equals(item.getToId())) {
                resultList.add(item);
                // 寻找子评论的子评论
                getToCommentList(item, commentList, resultList);
            }
        }
    }


    /**
     * 获取用户列表
     *
     * @param blogCommentList 博客列表
     * @return java.util.Map<java.lang.String, com.huangpuguang.system.api.domain.SysUser>
     */
    private List<BlogComment> getCommentListWithUser(List<BlogComment> blogCommentList) {
        //查询所有用户列表
        List<SysUser> allUser = remoteUserService.getAllUser();
        Set<SysUser> userSet = new HashSet<>();
        //发表过评论的用户列表
        blogCommentList.forEach(item -> {
            Long userId = item.getUserId();
            Long toUserId = item.getToUserId();
            allUser.forEach(user -> {
                if (user.getUserId().equals(userId)) {
                    userSet.add(user);
                }
                if (user.getUserId().equals(toUserId)) {
                    userSet.add(user);
                }
            });
        });

        Map<Long, SysUser> userMap = new HashMap<>(16);
        //过滤用户敏感信息
        userSet.forEach(item -> {
            SysUser user = new SysUser();
            user.setAvatar(item.getAvatar());
            user.setUserId(item.getUserId());
            user.setNickName(item.getNickName());
            userMap.put(item.getUserId(), user);
        });
        blogCommentList.forEach(item -> {
            if (item.getUserId() != null) {
                item.setUser(userMap.get(item.getUserId()));
            }

            if (item.getToUserId() != null) {
                item.setToUser(userMap.get(item.getToUserId()));
            }
        });
        return blogCommentList;
    }

    /**
     * 举报评论
     *
     * @param blogComment 评论
     * @return void
     */
    @Override
    public void reportComment(BlogComment blogComment) {
        BlogCommentReport commentReport = new BlogCommentReport();
        commentReport.setContent(blogComment.getContent());
        commentReport.setProgress(0);
        // 从VO中获取举报的用户id
        commentReport.setUserId(blogComment.getUserId());
        commentReport.setReportCommentId(blogComment.getId());
        // 从entity中获取被举报的用户uid
        commentReport.setReportUserId(blogComment.getUserId());
        commentReport.setStatus(BlogConstants.ENABLE);
        commentReportMapper.insertBlogCommentReport(commentReport);
    }

    @Override
    public void deleteComment(BlogComment comment) {
        comment.setStatus(BlogConstants.DISABLED);
        blogCommentMapper.updateBlogComment(comment);
        comment.setFirstCommentFlag(Constants.NUM_ONE);
        List<BlogComment> sonCommentList = blogCommentMapper.selectFirstBlogCommentList(comment);
        List<BlogComment> resultList = new ArrayList<>();
        this.getToCommentList(comment, sonCommentList, resultList);
        // 将所有的子评论也删除
        if(!resultList.isEmpty()) {
            resultList.forEach(item -> {
                item.setStatus(BlogConstants.DISABLED);
                item.setUpdateTime(new Date());
                blogCommentMapper.updateBlogComment(item);
            });

        }
    }
}

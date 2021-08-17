package com.huangpuguang.blog.controller.admin;

import com.huangpuguang.blog.service.BlogPictureService;
import com.huangpuguang.blog.domain.BlogPicture;
import com.huangpuguang.common.core.web.controller.BaseController;
import com.huangpuguang.common.core.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author procon
 *
 *
 */
@RestController
@RequestMapping("/pic")
public class BlogPictureController extends BaseController {

    @Autowired
    private BlogPictureService pictureService;

    @GetMapping("/getList")
    public TableDataInfo getBlogSortList(BlogPicture picture){
        startPage();
        List<BlogPicture> blogPictures = pictureService.selectBlogPictureList(picture);
        return getDataTable(blogPictures);
    }
}

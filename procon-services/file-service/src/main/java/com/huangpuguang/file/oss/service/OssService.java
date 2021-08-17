package com.huangpuguang.file.oss.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 *  </br>
 * @author procon
 * @date 2020-07-28 22:53
 */
public interface OssService {

    /**
     * 文件上传到阿里云
     * @param  file 将要上传的文件
     * @param category 文件分类
     * @return  java.lang.String
     */
    String upload(MultipartFile file,String category);

    /**
     * 下载文件
     * @param fileName 文件名称
     * @param destDir 文件保存地址
     *
     */
    void download(String fileName,String destDir);

    /**
     * 流式下载oss文件
     * @param ossKeyId
     * @param response
     * @return void
     */
    void downloadAsStream(String ossKeyId, HttpServletResponse response);

    /**
     * 根据文件名获取文件访问URL(外网访问-下行收费)
     * @param ossFileKey 文件名
     * @param expires URL有效时间（分钟）
     * @return
     */
    String getFileUrl(String ossFileKey, int expires);

    /**
     * 删除文件
     * @param fileName 文件名
     * @return
     */
    void deleteFile(String fileName);


    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     *
     * @param fileName 文件名
     * @return 文件的contentType
     */
    String getContentType(String fileName);
}

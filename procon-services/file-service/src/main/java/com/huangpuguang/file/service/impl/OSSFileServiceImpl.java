package com.huangpuguang.file.service.impl;

import cn.hutool.core.date.DateUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.huangpuguang.common.core.exception.ServiceException;
import com.huangpuguang.common.core.utils.ProconDateUtils;
import com.huangpuguang.file.oss.config.OssConfig;
import com.huangpuguang.file.oss.service.OssService;
import com.huangpuguang.file.service.SysFileService;
import com.huangpuguang.file.utils.FileUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * oss文件上传
 *
 * @author Procon
 * @since 2021/5/8
 */
@Primary
@Service
@Slf4j
public class OSSFileServiceImpl implements SysFileService {

    @Autowired
    private OssService ossService;
    @Override
    public String uploadFile(MultipartFile file) {

        String endpoint = OssConfig.END_POINT;
        String accessKeyId = OssConfig.ACCESS_KEY_ID;
        String accessKeySecret = OssConfig.ACCESS_KEY_SECRET;
        String bucketName = OssConfig.BUCKET_NAME;

        OSS ossClient = null;
        String ossFileKey;
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 上传文件流。
            InputStream inputStream = file.getInputStream();
            //构建日期路径：category/20200801文件名
            String datePath = ProconDateUtils.dateTime();
            //文件名：uuid.扩展名
            String fileName = UUID.randomUUID().toString();
            String fileType = FileUploadUtils.getExtension(file);
            String newName;
            newName = fileName + fileType;
            String fileUrl = "blog/"+ datePath + "/" + newName;

            long fileSize = file.getSize();
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(inputStream.available());
            metadata.setCacheControl("no-cache");
            metadata.setHeader("Pragma", "no-cache");
            metadata.setContentEncoding("utf-8");
            metadata.setContentType(ossService.getContentType(fileUrl));
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件
            ossClient.putObject(bucketName, fileUrl, inputStream, metadata);
            ossFileKey = ossClient.generatePresignedUrl(bucketName, fileUrl, DateUtil.offsetDay(new Date(),36500)).toString();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new ServiceException("文件上传异常");
        }finally {
            // 关闭OSSClient。
            if(ossClient != null){
                ossClient.shutdown();
            }
        }
        return ossFileKey;
    }
}

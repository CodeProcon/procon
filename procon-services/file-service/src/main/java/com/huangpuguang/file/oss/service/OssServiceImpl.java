package com.huangpuguang.file.oss.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.huangpuguang.file.oss.config.OssConfig;
import com.huangpuguang.common.core.exception.CustomException;
import com.huangpuguang.common.core.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 *  </br>
 * @author procon
 * @date 2020-07-28 23:00
 */

@Slf4j
@Service
public class OssServiceImpl implements OssService {

    private  String endpoint = OssConfig.END_POINT;
    private  String accessKeyId = OssConfig.ACCESS_KEY_ID;
    private  String accessKeySecret = OssConfig.ACCESS_KEY_SECRET;
    private  String bucketName = OssConfig.BUCKET_NAME;

    @Override
    public String upload(MultipartFile file,String category) {

        endpoint = OssConfig.END_POINT;
        accessKeyId = OssConfig.ACCESS_KEY_ID;
        accessKeySecret = OssConfig.ACCESS_KEY_SECRET;
        bucketName = OssConfig.BUCKET_NAME;

        OSS ossClient = null;
        String ossFileKey;
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 上传文件流。
            InputStream inputStream = file.getInputStream();
            //构建日期路径：category/20200801文件名
            String datePath = DateUtils.dateTime();
            //文件名：uuid.扩展名
            String original = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString();
            assert original != null;
            String fileType = original.substring(original.lastIndexOf("."));
            String newName;
            newName = fileName + fileType;
            String fileUrl = category+"/"+ datePath + "/" + newName;

            Long fileSize = file.getSize();
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(inputStream.available());
            metadata.setCacheControl("no-cache");
            metadata.setHeader("Pragma", "no-cache");
            metadata.setContentEncoding("utf-8");
            metadata.setContentType(getContentType(fileUrl));
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件
            ossClient.putObject(bucketName, fileUrl, inputStream, metadata);
            long time = System.currentTimeMillis();
            Date expiration = new Date(time + 3600L * 1000 * 24 * 365 * 10);
            //获取可以之间访问的oss链接
            String url = ossClient.generatePresignedUrl(bucketName, fileUrl, expiration).toString();
            ossFileKey = url;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new CustomException("文件上传异常");
        }finally {
            // 关闭OSSClient。
            if(ossClient != null){
                ossClient.shutdown();
            }

        }
        return ossFileKey;
    }

    @Override
    public void download(String ossFileKey,String destDir) {
        // 创建OSSClient实例。
        try {
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
            ossClient.getObject(new GetObjectRequest(bucketName, ossFileKey), new File(destDir));
            // 关闭OSSClient。
            ossClient.shutdown();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public void downloadAsStream(String ossKeyId, HttpServletResponse response) {
        OSS ossClient = null;
        InputStream in = null;
        OutputStream os = null;
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
            OSSObject ossObject = ossClient.getObject(bucketName, ossKeyId);
            // 读取文件内容。
            String fileName = ossKeyId.substring(ossKeyId.lastIndexOf("/")+1);
            in = new BufferedInputStream(ossObject.getObjectContent());
            // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" +fileName );
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setContentType("application/octet-stream");
            os = new BufferedOutputStream(response.getOutputStream());
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) != -1){
                os.write(buffer,0,len);
                os.flush();
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CustomException(e.getMessage(),e);
        } finally {
            // 关闭OSSClient。
            assert ossClient != null;
            ossClient.shutdown();
            //关闭輸入输出流
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }


    }

    @Override
    public  String getFileUrl(String ossFileKey, int expires) {
        OSS client=new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        URL url = client.generatePresignedUrl(bucketName, ossFileKey, DateUtils.dateAfter(new Date(),expires, Calendar.MINUTE));

        String path = url.toString();
        try {
            URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }
        return path;
    }



    @Override
    public void deleteFile(String ossFileKey) {
        OSS client=new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        client.deleteObject(bucketName,ossFileKey);
        client.shutdown();
    }


    @Override
    public  String getContentType(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
        if ("bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if ("gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if ("jpeg".equalsIgnoreCase(fileExtension) || "jpg".equalsIgnoreCase(fileExtension) || "png".equalsIgnoreCase(fileExtension)) {
            return "image/jpeg";
        }
        if ("html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if ("txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if ("vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if ("ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if ("doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if ("xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        return "text/html";
    }
}

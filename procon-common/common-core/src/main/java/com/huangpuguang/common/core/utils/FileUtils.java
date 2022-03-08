package com.huangpuguang.common.core.utils;


import com.huangpuguang.common.core.constant.Constants;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;


/**
 * 文件有关的工具类
 *
 * @author procon
 * @date 2017年10月2日12:16:27
 */
@Slf4j
public class FileUtils {
    protected static final String[] IMG_FILE = {"bmp", "jpg", "png", "tif", "gif", "jpeg", "webp"};
    protected static final String[] DOC_FILE = {"doc", "docx", "txt", "hlp", "wps", "rtf", "html", "pdf", "md", "sql", "css", "js", "vue", "java"};
    protected static final String[] VIDEO_FILE = {"avi", "mp4", "mpg", "mov", "swf"};
    protected static final String[] MUSIC_FILE = {"wav", "aif", "au", "mp3", "ram", "wma", "mmf", "amr", "aac", "flac"};
    protected static final String[] ALL_FILE = {"bmp", "jpg", "png", "tif", "gif", "jpeg", "webp",
            "doc", "docx", "txt", "hlp", "wps", "rtf", "html", "pdf", "md", "sql", "css", "js", "vue", "java",
            "avi", "mp4", "mpg", "mov", "swf",
            "wav", "aif", "au", "mp3", "ram", "wma", "mmf", "amr", "aac", "flac"
    };

    /**
     * 判断是否是图片
     * @param fileName 文件名
     * @return 结果
     */
    public static Boolean isPicture(String fileName) {
        if(StringUtils.isEmpty(fileName)) {
            return false;
        }
        String expandName = getPicExpandedName(fileName);
        return Arrays.asList(IMG_FILE).contains(expandName);
    }

    /**
     * 判断是否为markdown文件
     * @param fileName 文件名
     * @return 结果
     */
    public static Boolean isMarkdown(String fileName) {
        if(StringUtils.isEmpty(fileName)) {
            return false;
        }
        String expandName = getPicExpandedName(fileName);

        return "md".equalsIgnoreCase(expandName);
    }

    /**
     * Markdown转Html
     * @param markdown 文件
     * @return 结果
     */
    public static String markdownToHtml(String markdown) {
        MutableDataSet options = new MutableDataSet();
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        Node document = parser.parse(markdown);
        return renderer.render(document);
    }

    /**
     * Html 转 Markdown
     * @param html 文件
     * @return 结果
     */
    public static String htmlToMarkdown(String html) {
        MutableDataSet options = new MutableDataSet();
        return FlexmarkHtmlConverter.builder(options).build().convert(html);
    }

    /**
     * 上传文件
     *
     * @param pathRoot 物理路径webapp所在路径
     * @return 返回图片上传的地址
     * @throws IOException IO异常
     * @throws IllegalStateException 非法参数异常
     */
    public static String uploadFile(String pathRoot, String baseUrl, MultipartFile avatar) throws IllegalStateException, IOException {
        String path = "";
        if (!avatar.isEmpty()) {
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType = avatar.getContentType();
            //获得文件后缀名称
            if(StringUtils.isNotEmpty(contentType)){
                String imageName = contentType.substring(contentType.indexOf("/") + 1);
                path = baseUrl + UUID.randomUUID().toString() + "." + imageName;
                avatar.transferTo(new File(pathRoot + path));
            }


        }
        return path;
    }


    /**
     * 获取后缀名
     *
     * @param fileName 文件名
     * @return 结果
     */
    public static String getPicExpandedName(String fileName) {
        String ext = "";
        if (StringUtils.isNotBlank(fileName) &&
                StringUtils.contains(fileName, ".")) {
            ext = ProconStrUtils.substring(fileName, fileName.lastIndexOf(".") + 1);
        }
        ext = ext.toLowerCase();
        if (ext.length() < 1) {
            ext = "jpg";
        }

        return ext;
    }

    /**
     * 从Request中获取文件
     * @return
     */
    public static List<MultipartFile> getMultipartFileList(HttpServletRequest request) {
        List<MultipartFile> files = new ArrayList<>();
        try {
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            if (request instanceof MultipartHttpServletRequest) {
                // 将request变成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                Iterator<String> iter = multiRequest.getFileNames();
                // 检查form中是否有enctype="multipart/form-data"
                if (multipartResolver.isMultipart(request) && iter.hasNext()) {
                    // 获取multiRequest 中所有的文件名
                    while (iter.hasNext()) {
                        List<MultipartFile> fileRows = multiRequest
                                .getFiles(iter.next().toString());
                        if (fileRows != null && fileRows.size() != 0) {
                            for (MultipartFile file : fileRows) {
                                if (file != null && !file.isEmpty()) {
                                    files.add(file);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            log.error("解析MultipartRequest错误", ex);
        }
        return files;
    }

    /**
     * 判断一个文件是否存在，不存在就创建它 Method execute,只能建最后面那个目录
     *
     * @param path
     * @return
     */
    public boolean creatFile(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            log.error("该目录不存在");
        } else {
          return  file.mkdir();
        }
        return false;
    }

    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName 要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            log.error("删除文件失败: {} 不存在！", fileName);
            return false;
        } else {
            if (file.isFile()) {
                return deleteFile(fileName);
            } else {
                return deleteDirectory(fileName);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                log.info("删除单个文件：{} 成功！", fileName);
                return true;
            } else {
                log.error("删除单个文件：{} 失败！", fileName);
                return false;
            }
        } else {
            log.error("删除单个文件失败：{} 不存在！", fileName);
            return false;
        }
    }

    /**
     * 批量删除文件
     *
     * @param fileNameList
     * @return
     */
    public static boolean deleteFileList(List<String> fileNameList) {
        int successCount = 0;
        for (String fileName : fileNameList) {
            File file = new File(fileName);
            // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    log.info("删除单个文件：{} 成功！", fileName);
                    successCount += 1;
                } else {
                    log.error("删除单个文件：{} 失败！", fileName);
                }
            } else {
                log.error("删除单个文件失败：{} 不存在！", fileName);
            }
        }
        if (successCount == fileNameList.size()) {
            log.info("所有文件删除成功！");
            return true;
        } else {
            log.error("存在删除失败的文件！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator)) {
            dir = dir + File.separator;
        }
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            log.info("===删除目录失败：" + dir + "不存在！===");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {
            log.error("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            log.error("删除目录 {} 成功！", dir);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 从文件名中得到其后缀名
     *
     * @param filename
     * @return 后缀名
     */
    public String getFileSuffix(String filename) {
        String suffix;
        suffix = filename.substring(
                filename.lastIndexOf(".") + 1);
        return suffix;
    }

    /**
     * 通过其后缀名判断其是否合法,合法后缀名为常见的
     *
     * @param suffix 后缀名
     * @return 合法返回true，不合法返回false
     */
    public boolean isSafe(String suffix) {
        suffix = suffix.toLowerCase();
        for (int i = 0; i < ALL_FILE.length; i++) {
            if (ALL_FILE[i].equals(suffix)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 通过其后缀名判断其是否是图片
     *
     * @param suffix 后缀名
     * @return 合法返回true，不合法返回false
     */
    public boolean isPic(String suffix) {
        suffix = suffix.toLowerCase();
        for (int i = 0; i < IMG_FILE.length; i++) {
            if (IMG_FILE[i].equals(suffix)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 进行上传文件的相关操作
     *
     * @param file
     */
    public int uploadFile(File file, String fileLocation) {

        int result = 1;
        try (FileInputStream fis = new FileInputStream(file);
             FileOutputStream fos = new FileOutputStream(new File(fileLocation));
             BufferedInputStream bis = new BufferedInputStream(fis);
             BufferedOutputStream bos = new BufferedOutputStream(fos)){
            int bytesRead;
            byte[] buffer = new byte[1024];
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        } catch (Exception e){
            result = 0;
            log.error(e.getMessage(),e);
        }
        return result;
    }

    /**
     * 计算文件大小，将long类型转换为String类型
     *
     * @param fileSize 文件大小
     * @return 结果
     */
    public String getFileStringSize(long fileSize) {
        double temp = 0.0;
        String ssize = "";
        temp = (double) fileSize / Constants.NUM_1024;
        if (temp >= fileSize / Constants.NUM_1024) {
            temp = temp / fileSize / Constants.NUM_1024;
            if (temp >= fileSize / Constants.NUM_1024) {
                temp = temp / fileSize / Constants.NUM_1024;
                ssize = temp + "000";
                ssize = ssize.substring(Constants.NUM_ZERO, ssize.indexOf(Constants.SYMBOL_POINT) + Constants.NUM_THREE) + "GB";
            } else {
                ssize = temp + "000";
                ssize = ssize.substring(Constants.NUM_ZERO, ssize.indexOf(Constants.SYMBOL_POINT) + Constants.NUM_THREE) + "MB";
            }
        } else {
            ssize = temp + "000";
            ssize = ssize.substring(Constants.NUM_ZERO, ssize.indexOf(Constants.SYMBOL_POINT) + Constants.NUM_THREE) + "KB";
        }
        return ssize;
    }


    /**
     * 图片上传
     *
     * @param request
     * @param directoryName 文件上传目录：比如upload(无需带前面的/) upload/news ..
     * @return
     * @throws IllegalStateException
     * @throws IOException
     * @Title upload
     */
    public static String upload(HttpServletRequest request,String basePath, String directoryName) throws IllegalStateException,
            IOException {
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession()
                .getServletContext());
        // 图片名称
        String fileName = null;
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 记录上传过程起始时的时间，用来计算上传时间
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    // 取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "") {
                        // 获得图片的原始名称
                        String originalFilename = file.getOriginalFilename();
                        // 获得图片后缀名称,如果后缀不为图片格式，则不上传
                        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
                        if (!Arrays.asList(IMG_FILE).contains(suffix)) {
                            continue;
                        }
                        // 获得上传路径的绝对路径地址(/upload)-->
                        String realPath = basePath + directoryName;
                        // 如果路径不存在，则创建该路径
                        File realPathDirectory = new File(realPath);
                        if (realPathDirectory == null || !realPathDirectory.exists()) {
                            realPathDirectory.mkdirs();
                        }
                        // 重命名上传后的文件名 111112323.jpg
                        fileName = UUID.randomUUID() + suffix;
                        // 定义上传路径 .../upload/111112323.jpg
                        File uploadFile = new File(realPathDirectory + "\\" + fileName);
                        file.transferTo(uploadFile);
                    }
                }
            }
        }
        return fileName;
    }


    /**
     * ckeditor文件上传功能，回调，传回图片路径，实现预览效果。
     *
     * @param request
     * @param response
     * @param directoryName 文件上传目录：比如upload(无需带前面的/) upload/..
     * @throws IOException
     * @Title ckeditor
     */
    public static void ckeditor(HttpServletRequest request, HttpServletResponse response,String basePath, String imgUrl, String directoryName)
            throws IOException {
        String fileName = upload(request,basePath, directoryName);
        // 结合ckeditor功能
        // imageContextPath为图片在服务器地址，如upload/123.jpg,非绝对路径
        String imageContextPath = imgUrl + "/" + directoryName + "/" + fileName;
        response.setContentType("text/html;charset=UTF-8");
        String callback = request.getParameter("CKEditorFuncNum");
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + imageContextPath + "',''" + ")");
        out.println("</script>");
        out.flush();
        out.close();
    }
}

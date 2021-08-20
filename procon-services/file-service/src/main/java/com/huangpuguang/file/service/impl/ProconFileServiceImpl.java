package com.huangpuguang.file.service.impl;

import cn.hutool.core.collection.ListUtil;
import com.huangpuguang.common.core.constant.BlogConstants;
import com.huangpuguang.common.core.domain.ResultModel;
import com.huangpuguang.common.core.text.UUID;
import com.huangpuguang.common.core.utils.*;
import com.huangpuguang.common.core.web.domain.AjaxResult;
import com.huangpuguang.file.mapper.ProconFileMapper;
import com.huangpuguang.file.oss.service.OssService;
import com.huangpuguang.file.service.ProconFileService;
import com.huangpuguang.file.service.ProconFileSortService;
import com.huangpuguang.system.api.domain.ProconFile;
import com.huangpuguang.system.api.domain.ProconFileSort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件Service业务层处理
 *
 * @author procon
 * @date 2020-08-11
 */
@Service
@Slf4j
public class ProconFileServiceImpl implements ProconFileService {

    @Value(value = "${file.destPath}")
    private String path;
    private static final String FILE_SEPARATOR  ="/";
    private final ProconFileMapper fileMapper;
    private final OssService ossService;
    private final ProconFileSortService fileSortService;

    @Autowired
    public ProconFileServiceImpl(ProconFileMapper fileMapper, OssService ossService, ProconFileSortService fileSortService) {
        this.fileMapper = fileMapper;
        this.ossService = ossService;
        this.fileSortService = fileSortService;
    }

    /**
     * 查询文件
     *
     * @param id 文件ID
     * @return 文件
     */
    @Override
    public ProconFile selectFileById(Long id) {
        return fileMapper.selectFileById(id);
    }

    /**
     * 查询文件列表
     *
     * @param file 文件
     * @return 文件
     */
    @Override
    public List<ProconFile> selectFileList(ProconFile file) {
        return fileMapper.selectFileList(file);
    }

    @Override
    public ResultModel<String> saveFile(ProconFile file) {
        Long userId = SecurityUtils.getUserId();
        file.setUserId(userId);
        int ret = fileMapper.insertFile(file);
        if (ret > 0){
            return ResultModel.ok("保存成功！");
        }
        return ResultModel.fail("文件保存异常!");
    }

    @Override
    public AjaxResult insertFile(MultipartFile file, Integer uploadType, String destPath, HttpServletRequest request) throws IOException {

        //如果是用户上传，则包含用户uid
        Long userUid = null;
        //如果是管理员上传，则包含管理员uid
        Long adminUid = null;
        String oldName = file.getOriginalFilename();
        long size = file.getSize();
        //以前的文件名
        log.info("===上传文件===：" + oldName);
        //获取扩展名，默认是jpg
        String picExpandedName = FileUtils.getPicExpandedName(oldName);

        //获取新文件名
        String newFileName = UUID.fastUUID() + "." + picExpandedName;
        log.info(newFileName + ":" + oldName);

        //文件路径问题
        log.info("path====" + destPath);
        String newPath = destPath + FILE_SEPARATOR + picExpandedName + FILE_SEPARATOR + DateUtils.dateTime() + FILE_SEPARATOR;
        log.info("newPath====" + newPath);
        String saveUrl = newPath + newFileName;
        ProconFileSort fileSort = new ProconFileSort();
        List<ProconFileSort> fileSorts = fileSortService.selectFileSortList(fileSort);

        if (!fileSorts.isEmpty()) {
            fileSort = fileSorts.get(0);
            log.info("====fileSort====" + JsonUtils.objectToJson(fileSort));
        } else {
            return AjaxResult.error("文件不允许上传！");
        }

        java.io.File saveFile;

        ProconFile fileStorage = new ProconFile();
        //本地上次
        if (uploadType.equals(BlogConstants.UPLOAD_LOCAL)) {
            // 保存本地，创建目录
            java.io.File file1 = new java.io.File(newPath);
            if (!file1.exists()) {
                boolean mkdirs = file1.mkdirs();
                Assert.isTrue(mkdirs,"创建文件失败");
            }
            saveFile = new java.io.File(saveUrl);
            boolean newFile = saveFile.createNewFile();
            if(newFile){
                file.transferTo(saveFile);
                fileStorage.setFileType(BlogConstants.UPLOAD_LOCAL);
            }

        } else if (uploadType.equals(BlogConstants.UPLOAD_OSS)) {
            saveUrl = ossService.upload(file, destPath);
            fileStorage.setFileType(BlogConstants.UPLOAD_OSS);
        }



        fileStorage.setCreateTime(DateUtils.getNowDate());
        fileStorage.setFileSortId(fileSort.getId().toString());
        insertFileStorage(userUid, adminUid, oldName, size, picExpandedName, newFileName, saveUrl, fileStorage);
        fileMapper.insertFile(fileStorage);
        return AjaxResult.success("文件上传成功！",fileStorage);
    }

    /**
     * 修改文件
     *
     * @param file 文件
     * @return 结果
     */
    @Override
    public int updateFile(ProconFile file) {
        file.setUpdateTime(DateUtils.getNowDate());
        return fileMapper.updateFile(file);
    }

    /**
     * 批量删除文件
     *
     * @param ids 需要删除的文件ID
     * @return 结果
     */
    @Override
    public int deleteFileByIds(Long[] ids) {
        return fileMapper.deleteFileByIds(ids);
    }

    /**
     * 删除文件信息
     *
     * @param id 文件ID
     * @return 结果
     */
    @Override
    public int deleteFileById(Long id) {
        return fileMapper.deleteFileById(id);
    }

    /**
     * <p> 插入文件表 </p>
     *
     * @date 2020/11/6 16:01
     */
    private void insertFileStorage(Long userId, Long adminId, String oldName, long size, String picExpandedName, String newFileName, String saveUrl, ProconFile fileStorage) {
        fileStorage.setFileOldName(oldName);
        fileStorage.setFileSize(size);
        fileStorage.setFileExpandedName(picExpandedName);
        fileStorage.setFileName(newFileName);
        fileStorage.setFileUrl(saveUrl);
        fileStorage.setStatus(BlogConstants.ENABLE);
        fileStorage.setUserId(userId);
        fileStorage.setAdminId(adminId);
    }

    @Override
    public List<ProconFile> selectFileByIds(String ids, String splitCode) {

        if(StringUtils.isNotNull(ids)){
            List<String> stringList = StringUtils.changeStringToString(ids, splitCode);
            List<Long> fileIds = new ArrayList<>();
            stringList.forEach(item -> fileIds.add(Long.parseLong(item)));
            return fileMapper.selectBlogSortByIds(fileIds);
        }

            return ListUtil.empty();
    }
}

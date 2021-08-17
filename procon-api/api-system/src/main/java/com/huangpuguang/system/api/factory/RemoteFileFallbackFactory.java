package com.huangpuguang.system.api.factory;

import com.huangpuguang.common.core.web.domain.AjaxResult;
import com.huangpuguang.system.api.domain.ProconFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.huangpuguang.common.core.domain.ResultModel;
import com.huangpuguang.system.api.RemoteFileService;
import com.huangpuguang.system.api.domain.SysFile;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 文件服务降级处理
 *
 * @author procon
 */
@Component
public class RemoteFileFallbackFactory implements FallbackFactory<RemoteFileService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteFileFallbackFactory.class);

    @Override
    public RemoteFileService create(Throwable throwable)
    {
        log.error("文件服务调用失败:{}", throwable.getMessage());
        return new RemoteFileService()
        {
            @Override
            public ResultModel<SysFile> upload(MultipartFile file)
            {
                return ResultModel.fail("上传文件失败:" + throwable.getMessage());
            }

            @Override
            public AjaxResult add(MultipartFile file, Integer uploadType, HttpServletRequest request) {
                return null;
            }

            @Override
            public List<ProconFile> getInfos(String fileIds, String splitCode) {
                return null;
            }

            @Override
            public ProconFile getInfo(Long id) {
                return null;
            }
        };
    }
}

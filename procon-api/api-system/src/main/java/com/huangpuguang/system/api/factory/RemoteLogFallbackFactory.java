package com.huangpuguang.system.api.factory;


import com.huangpuguang.system.api.domain.SysLogininfor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import com.huangpuguang.common.core.domain.ResultModel;
import com.huangpuguang.system.api.RemoteLogService;
import com.huangpuguang.system.api.domain.SysOperLog;


/**
 * 日志服务降级处理
 *
 * @author procon
 */
@Component
public class RemoteLogFallbackFactory implements FallbackFactory<RemoteLogService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteLogFallbackFactory.class);

    @Override
    public RemoteLogService create(Throwable throwable)
    {
        log.error("日志服务调用失败:{}", throwable.getMessage());
        return new RemoteLogService()
        {

            @Override
            public ResultModel<Boolean> saveLog(SysOperLog sysOperLog, String source) {
                return null;
            }

            @Override
            public ResultModel<Boolean> saveLogininfor(SysLogininfor sysLogininfor, String source) {
                return null;
            }
        };

    }
}

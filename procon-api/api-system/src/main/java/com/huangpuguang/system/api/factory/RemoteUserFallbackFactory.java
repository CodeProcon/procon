package com.huangpuguang.system.api.factory;

import com.huangpuguang.system.api.domain.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import com.huangpuguang.common.core.domain.ResultModel;
import com.huangpuguang.system.api.RemoteUserService;
import com.huangpuguang.system.api.model.LoginUser;


import java.util.List;

/**
 * 用户服务降级处理
 *
 * @author procon
 */
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteUserFallbackFactory.class);

    @Override
    public RemoteUserService create(Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService()
        {


            @Override
            public ResultModel<LoginUser> getUserInfo(String username, String source) {
                return ResultModel.fail("获取用户失败:" + throwable.getMessage());
            }

            @Override
            public ResultModel<Boolean> registerUserInfo(SysUser sysUser, String source) {
                return ResultModel.fail("注册用户失败:" + throwable.getMessage());
            }

            @Override
            public SysUser getUserInfoBySource(String username, String source) {
                log.error("用户服务调用失败:{}", throwable.getMessage());
                return null;
            }

            @Override
            public SysUser saveAndUpdate(SysUser sysUser) {
                log.error("获取用户失败:{}",throwable.getMessage());
                return null;
            }

            @Override
            public List<SysUser> getAllUser() {
                log.error("获取用户失败:{}",throwable.getMessage());
                return null;
            }
        };
    }
}

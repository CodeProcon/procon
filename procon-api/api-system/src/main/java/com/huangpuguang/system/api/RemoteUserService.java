package com.huangpuguang.system.api;

import com.huangpuguang.common.core.constant.SecurityConstants;
import com.huangpuguang.common.core.constant.ServiceNameConstants;
import com.huangpuguang.common.core.domain.ResultModel;
import com.huangpuguang.common.core.web.domain.AjaxResult;
import com.huangpuguang.system.api.domain.SysUser;
import com.huangpuguang.system.api.factory.RemoteUserFallbackFactory;
import com.huangpuguang.system.api.model.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户服务
 *
 * @author procon
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService
{
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @param source 请求来源
     * @return 结果
     */
    @GetMapping("/user/info/{username}")
    public ResultModel<LoginUser> getUserInfo(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 注册用户信息
     *
     * @param sysUser 用户信息
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/user/register")
    public ResultModel<Boolean> registerUserInfo(@RequestBody SysUser sysUser, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);


    /**
     * 根据渠道来源获取用户信息
     * @param username 用户名
     * @param source 来源
     * @return com.huangpuguang.system.api.domain.SysUser
     */
    @GetMapping("/user/info/{username}/{source}")
    public SysUser getUserInfoBySource(@PathVariable("username") String username, @PathVariable("source")String source);


    /**
     * 更新删除用户
     * @param sysUser 用户
     * @return void
     */
    @PostMapping("/user/saveAndUpdate")
    public SysUser saveAndUpdate(@RequestBody SysUser sysUser);

    /**
     * 查询所有用户
     *
     * @return java.util.List<com.huangpuguang.system.api.domain.SysUser>
     */
    @GetMapping("/user/getAllUser")
    public List<SysUser> getAllUser();

    @GetMapping(value =   "/{userId}" )
    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId);
}

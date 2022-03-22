package com.huangpuguang.gateway.service;

import com.huangpuguang.common.core.exception.CaptchaException;
import com.huangpuguang.common.core.web.domain.AjaxResult;

import java.io.IOException;

/**
 * 验证码处理
 *
 * @author procon
 */
public interface ValidateCodeService
{
    /**
     * 生成验证码
     */
    AjaxResult createCaptcha() throws IOException, CaptchaException;

    /**
     * 校验验证码
     */
    void checkCaptcha(String key, String value) throws CaptchaException;
}

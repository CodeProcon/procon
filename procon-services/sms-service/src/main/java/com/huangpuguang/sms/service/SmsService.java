package com.huangpuguang.sms.service;

import com.huangpuguang.sms.param.SmsSendModel;

/**
 * <p>短信发送服务 </p>
 *
 * @author Procon
 * @since 2021/7/15
 */

public interface SmsService {
    /**
     * 发送短信
     * @param smsSendModel
     * @return 发送结果
     */
    String sendSms(SmsSendModel smsSendModel);
}

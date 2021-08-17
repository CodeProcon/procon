package com.huangpuguang.sms.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.huangpuguang.sms.param.SmsSendModel;
import com.huangpuguang.sms.service.SmsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>短信发送 </p>
 *
 * @author Procon
 * @since 2021/7/15
 */
@Slf4j
@AllArgsConstructor
@Service
public class SmsServiceImpl implements SmsService {

    public final Client client;

    @Override
    public String sendSms(SmsSendModel smsSendModel) {
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(smsSendModel.getPhone())
                .setTemplateParam(JSON.toJSONString(smsSendModel.getTemplateParam()))
                .setTemplateCode(smsSendModel.getTemplateCode())
                .setSignName(smsSendModel.getSignName());
        String response = null;
        try {
            SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
            log.info("短信发送结果:{}", JSON.toJSONString(sendSmsResponse));
            response = sendSmsResponse.getBody().getMessage();
        } catch (Exception e) {
            log.error("短信发送异常:{}",e.getMessage());
        }

        return response;
    }
}

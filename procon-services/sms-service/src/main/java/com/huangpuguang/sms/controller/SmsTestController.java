package com.huangpuguang.sms.controller;

import com.huangpuguang.sms.param.SmsSendModel;
import com.huangpuguang.sms.utils.SendMailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>功能描述 </p>
 *
 * @author Procon
 * @since 2021/7/16
 */
@RestController
@RequestMapping("/sms")
public class SmsTestController {

    @Value("${aliyun.sms.templateCode}")
    private String templateCode;
    @Value("${aliyun.sms.signName}")
    private String signName;

    @Autowired
    private SendMailUtils sendMailUtils;

    @PostMapping("/send")
    public void send(@RequestBody SmsSendModel smsSendModel){
        sendMailUtils.sendEmail("测试邮件","1073768708@qq.com","<em>这是一封测试邮件</em>",false,null,null);
    }
}

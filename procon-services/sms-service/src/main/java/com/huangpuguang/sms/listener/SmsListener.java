package com.huangpuguang.sms.listener;

import com.alibaba.fastjson.JSON;
import com.huangpuguang.sms.param.SmsSendModel;
import com.huangpuguang.sms.service.SmsService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * <p>短信监听类用于发送松短信 </p>
 *
 * @author Procon
 * @since 2021/7/21
 */

@Slf4j
@Component
public class SmsListener {

    @Autowired
    private SmsService smsService;

    @RabbitListener(queues = "procon.queue.sms")
    public void sendSms(Channel channel, Message message) {
        try {
            String msgStr = new String(message.getBody(), StandardCharsets.UTF_8);
            log.info("接收到消息:{}", msgStr);
            SmsSendModel sendModel = JSON.parseObject(msgStr, SmsSendModel.class);
            smsService.sendSms(sendModel);
        } finally {
            try {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e) {
                log.error("发送短信异常:{}",e.getMessage());
            }
        }
    }
}

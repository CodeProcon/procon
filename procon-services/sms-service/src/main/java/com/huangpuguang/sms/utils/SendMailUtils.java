package com.huangpuguang.sms.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

/**
 * <p>邮件发送类 </p>
 *
 * @author Procon
 * @since 2021/7/20
 */

@Slf4j
@RefreshScope
@Component
public class SendMailUtils {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Value(value = "${spring.mail.username}")
    public String sender;


    /**
     * 发送邮件
     * @param subject 主题
     * @param receiver 接收者
     * @param text 文本
     * @param isAddAttachment 是否上传附件
     * @param fileName 文件名
     * @param path 文件路径
     */
    public void sendEmail(String subject, String receiver, String text,boolean isAddAttachment,String fileName,String path) {
        try{
            //创建一个复杂的消息邮件
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            //multipart:true
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            //邮件主题
            helper.setSubject(subject);
            //邮件正文
            helper.setText(text, true);
            //邮件接收人
            helper.setTo(receiver);
            //邮件发送者
            helper.setFrom(sender);
            //邮件抄送人
            helper.setCc(new String[]{"huangpuguang@foxmail.com"});
            //邮件密送人
            helper.setBcc("huangpuguang@outlook.com");
            //邮件发送日期
            helper.setSentDate(new Date());
            //添加附件
            if(isAddAttachment){
                helper.addAttachment(fileName, new File(path));
            }
            javaMailSender.send(mimeMessage);
            log.info("邮件发送成功");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}

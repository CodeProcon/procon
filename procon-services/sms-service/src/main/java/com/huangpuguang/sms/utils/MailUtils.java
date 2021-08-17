package com.huangpuguang.sms.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

/**
 * <p>邮件发送工具类 </p>
 *
 * @author Procon
 * @since 2021/7/20
 */
@Slf4j
@Component
public class MailUtils {

    @Autowired
    private  JavaMailSender mailSender;

    /**
     * 注入常量
     */

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送文本邮件
     *
     * @param toAddr  收件人
     * @param title   标题
     * @param content 内容
     */
    public void sendTextMail(String toAddr, String title, String content) {
        // 纯文本邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(toAddr);
        message.setSubject(title);
        message.setText(content);
        try {
            mailSender.send(message);
            log.info("Text邮件已经发送。");
        } catch (Exception e) {
            log.error("发送Text邮件时发生异常！", e);
        }

    }

    /**
     * 发送 html 邮件
     *
     * @param toAddr  收件人
     * @param title   标题
     * @param content 内容（HTML）
     */
    public void sendHtmlMail(String toAddr, String title, String content) {
        // html 邮件对象
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(toAddr);
            helper.setSubject(title);
            helper.setText(content, true);
            //邮件抄送人
            helper.setCc(new String[]{"huangpuguang@foxmail.com"});
            //邮件密送人
            helper.setBcc("huangpuguang@outlook.com");
            //邮件发送日期
            helper.setSentDate(new Date());
            mailSender.send(message);
            log.info("html邮件发送成功");
        } catch (MessagingException e) {
            log.error("发送html邮件时发生异常！", e);
        }
    }


    /**
     * 发送带附件的邮件
     *
     * @param toAddr   收件人
     * @param title    标题
     * @param content  内容
     * @param filePath 附件地址
     */
    public void sendAttachmentsMail(String toAddr, String title, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(toAddr);
            helper.setSubject(title);
            helper.setText(content, true);
            //邮件抄送人
            helper.setCc(new String[]{"huangpuguang@foxmail.com"});
            //邮件密送人
            helper.setBcc("huangpuguang@outlook.com");
            //邮件发送日期
            helper.setSentDate(new Date());
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            mailSender.send(message);
            log.info("带附件的邮件已经发送。");
        } catch (MessagingException e) {
            log.error("发送带附件的邮件时发生异常！", e);
        }
    }


    /**
     * 发送文本中有静态资源（图片）的邮件
     *
     * @param toAddr  收件人
     * @param title   标题
     * @param content 内容
     * @param rscPath 资源路径
     * @param rscId   资源id (可能有多个图片)
     */
    public void sendInlineResourceMail(String toAddr, String title, String content, String rscPath, String rscId) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(toAddr);
            helper.setSubject(title);
            helper.setText(content, true);
            //邮件抄送人
            helper.setCc(new String[]{"huangpuguang@foxmail.com"});
            //邮件密送人
            helper.setBcc("huangpuguang@outlook.com");
            //邮件发送日期
            helper.setSentDate(new Date());
            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);
            mailSender.send(message);
            log.info("嵌入静态资源的邮件已经发送。");
        } catch (MessagingException e) {
            log.error("发送嵌入静态资源的邮件时发生异常！", e);
        }
    }
}


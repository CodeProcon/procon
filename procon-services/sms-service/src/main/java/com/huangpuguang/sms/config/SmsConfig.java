package com.huangpuguang.sms.config;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * <p>短信配置 </p>
 *
 * @author Procon
 * @since 2021/7/15
 */
@Slf4j
@Configuration
public class SmsConfig {

    @Value("${aliyun.sms.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.sms.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.sms.endpoint}")
    private String endpoint;

    @Bean
    public Client createClient(){
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = endpoint;
        Client client = null;
        try {
            client = new Client(config);
        } catch (Exception e) {
           log.error("短信配置初始化异常:{}",e.getMessage());
        }
        return client;
    }

}

package com.huangpuguang.pay.alipay.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>支付宝客户端配置 </p>
 *
 * @author Procon
 * @since 2021/7/23
 */

@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "alipay")
public class AlipayClientConfig {
    private String gateway;
    private String appId;
    private String aliPublicKey;
    private String privateKey;
    private String signType;


    @Bean
    public AlipayClient createClient(){
        return new DefaultAlipayClient(gateway, appId, privateKey, "json", "GBK", aliPublicKey, signType);
    }
}

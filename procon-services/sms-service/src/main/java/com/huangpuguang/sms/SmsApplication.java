package com.huangpuguang.sms;

import com.huangpuguang.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * <p>消息相关服务 </p>
 *
 * @author Procon
 * @since 2021/7/15
 */


@EnableCustomSwagger2
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class SmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class, args);
    }
}

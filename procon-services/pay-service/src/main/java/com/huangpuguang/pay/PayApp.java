package com.huangpuguang.pay;

import com.huangpuguang.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * <p>功能描述 </p>
 *
 * @author Procon
 * @since 2021/7/23
 */

@EnableCustomSwagger2
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class PayApp {
    public static void main(String[] args) {
        SpringApplication.run(PayApp.class, args);
    }
}

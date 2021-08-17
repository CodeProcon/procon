package com.huangpuguang.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.huangpuguang.common.security.annotation.EnableProconFeignClients;

/**
 * 认证授权中心
 *
 * @author procon
 */
@EnableProconFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AuthApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(AuthApplication.class, args);
    }
}

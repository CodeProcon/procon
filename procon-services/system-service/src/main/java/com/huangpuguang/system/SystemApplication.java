package com.huangpuguang.system;

import com.huangpuguang.common.security.annotation.EnableCustomConfig;
import com.huangpuguang.common.security.annotation.EnableProconFeignClients;
import com.huangpuguang.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 系统模块
 *
 * @author procon
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableProconFeignClients
@SpringBootApplication
public class SystemApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SystemApplication.class, args);
    }
}

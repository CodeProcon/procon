package com.huangpuguang.job;

import com.huangpuguang.common.security.annotation.EnableCustomConfig;
import com.huangpuguang.common.security.annotation.EnableProconFeignClients;
import com.huangpuguang.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 定时任务
 *
 * @author procon
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableProconFeignClients
@SpringBootApplication
public class JobApplication
{
    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class, args);
    }
}

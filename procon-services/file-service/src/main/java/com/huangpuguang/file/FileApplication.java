package com.huangpuguang.file;

import com.huangpuguang.common.security.annotation.EnableCustomConfig;
import com.huangpuguang.common.security.annotation.EnableProconFeignClients;
import com.huangpuguang.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 文件服务
 *
 * @author procon
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableProconFeignClients
@SpringBootApplication
public class FileApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(FileApplication.class, args);
    }
}

package com.huangpuguang.gen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.huangpuguang.common.security.annotation.EnableCustomConfig;
import com.huangpuguang.common.security.annotation.EnableProconFeignClients;
import com.huangpuguang.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 代码生成
 *
 * @author procon
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableProconFeignClients
@SpringBootApplication
public class GenApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(GenApplication.class, args);
    }
}

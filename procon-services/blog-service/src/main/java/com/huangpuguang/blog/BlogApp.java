package com.huangpuguang.blog;

import com.huangpuguang.common.security.annotation.EnableCustomConfig;
import com.huangpuguang.common.security.annotation.EnableProconFeignClients;
import com.huangpuguang.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 *
 * @author procon
 * @date 2020-07-31 23:19
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableProconFeignClients
@SpringBootApplication
public class BlogApp {
    public static void main(String[] args) {
        SpringApplication.run(BlogApp.class,args);
    }
}

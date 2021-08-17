package com.huangpuguang.monitor;

import com.huangpuguang.common.swagger.annotation.EnableCustomSwagger2;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 监控中心
 *
 * @author procon
 */

@EnableAdminServer
@EnableCustomSwagger2
@SpringBootApplication
public class MonitorApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(MonitorApplication.class, args);
    }
}

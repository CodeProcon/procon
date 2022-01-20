package com.huangpuguang.common.datasource.env;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * <p> seata 在 springboot 2.6.x 存在循环引用问题的处理</p>
 *
 * @author Procon
 * @since 2022/1/20
 */
public class ApplicationSeataInitializer implements EnvironmentPostProcessor, Ordered {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application)
    {
        System.setProperty("spring.main.allow-circular-references", "true");
    }

    @Override
    public int getOrder()
    {
        return Ordered.LOWEST_PRECEDENCE;
    }
}

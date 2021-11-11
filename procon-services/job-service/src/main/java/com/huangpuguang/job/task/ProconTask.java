package com.huangpuguang.job.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.huangpuguang.common.core.utils.ProconStringUtils;

/**
 * 定时任务调度测试
 *
 * @author procon
 */
@Slf4j
@Component("proconTask")
public class ProconTask
{
    public void proconMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        log.info(ProconStringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void proconParams(String params)
    {
        log.info("执行有参方法：" + params);
    }

    public void proconNoParams()
    {
        log.info("执行无参方法");
    }
}

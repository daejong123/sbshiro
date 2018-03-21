package com.dottie.sbshiro.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author lindazhong
 * @date 2018/3/20 18:31
 * 定时任务
 * 需要在启动类中加上此注解。
 * @EnableScheduling//增加支持定时任务的注解
 */
@Component
public class SchedulingTest {

    @Scheduled(cron = "0/5 * * * * ?") // 每5秒执行一次
    public void scheduler() {
        System.out.println("哈哈哈哈 测试定时任务 5秒执行一次。。。");
    }
}
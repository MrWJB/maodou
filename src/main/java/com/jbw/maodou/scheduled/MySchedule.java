package com.jbw.maodou.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MySchedule {

    /**
     * fixedDelay表示在当前任务执行结束1秒后开启另一个任务。
     */
    @Scheduled(fixedDelay = 1000)
    public void fixedDelay(){
        System.out.println("fixedDelay: " + new Date());
    }

    /**
     *fixedRate表示在当前任务开始执行2秒后开启另一个定时任务。
     */
    @Scheduled(fixedRate = 2000)
    public void fixedRate(){
        System.out.println("fixedRate: " + new Date());
    }

    /**
     * initialDelay 表示首次执行的延迟时间。
     */
    @Scheduled(initialDelay = 1000,fixedRate = 2000)
    public void initialDelay(){
        System.out.println("initialDelay: " + new Date());
    }

    /**
     * 表示该定时任务每分钟执行一次。
     */
    @Scheduled(cron = "0 * * * * ?")
    public void cron(){
        System.out.println("cron:" + new Date());
    }
}

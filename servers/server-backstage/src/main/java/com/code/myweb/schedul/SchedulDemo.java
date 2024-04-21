package com.code.myweb.schedul;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@EnableAsync
public class SchedulDemo {

    @Async("quartzThreadPool")
    @Scheduled(cron = "${define.quartz.cron}")
    public void schedule() {
        System.out.println(Thread.currentThread().hashCode());
        System.out.println(Thread.currentThread().getName());
    }

    @Async("quartzThreadPool")
    @Scheduled(cron = "${define.quartz.cron}")
    public void schedule2() {
        System.out.println(Thread.currentThread().hashCode());
        System.out.println(Thread.currentThread().getName());

    }

}

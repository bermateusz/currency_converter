package com.bereda.cron;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class CronConfig {

    @Scheduled(cron = "*/5 * * * *")
    public void scheduleRequestTask(){

    }
}

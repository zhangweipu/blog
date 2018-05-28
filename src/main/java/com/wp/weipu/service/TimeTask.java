package com.wp.weipu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author zwp
 */
@Component
@EnableScheduling
public class TimeTask {
    private static final Logger logger= LoggerFactory.getLogger(TimeTask.class);

    /**
     * 时间表达式
     */
    @Scheduled(cron = "0 0 0 0 0 0")
    public void test(){

    }
}

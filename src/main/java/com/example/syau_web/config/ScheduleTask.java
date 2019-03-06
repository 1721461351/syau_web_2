package com.example.syau_web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * create by wangyu
 * 2018/10/28 0028 17:52
 */
//@EnableScheduling
@Component
@Configuration
public class ScheduleTask {

    public void test() {
        System.out.println("you are the best!");
    }
}

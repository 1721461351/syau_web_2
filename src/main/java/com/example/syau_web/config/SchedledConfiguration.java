package com.example.syau_web.config;

import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * create by wangyu
 * 2018/10/28 0028 16:17
 */
@Configuration
public class SchedledConfiguration {

    /**
     * 配置定时任务
     * @param task
     * @return
     */
    @Bean(name = "jobDetail")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(ScheduleTask task){

        MethodInvokingJobDetailFactoryBean detailFactoryBean = new MethodInvokingJobDetailFactoryBean();
        detailFactoryBean.setConcurrent(false);
        /**
         * 为要执行的实体类设置相应对象
         */
        detailFactoryBean.setTargetObject(task);
        detailFactoryBean.setName("addData");//设置任务名
        detailFactoryBean.setGroup("group1");//设置组名
        detailFactoryBean.setTargetMethod("test");//这里设置我们要执行的方法
        return detailFactoryBean;
    }

    @Bean(name = "trigger")
    public CronTriggerFactoryBean triggerFactoryBean(MethodInvokingJobDetailFactoryBean jobDetailFactoryBean){
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();

        cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
//        todo
        cronTriggerFactoryBean.setCronExpression("0 30 20 * * ?");
        cronTriggerFactoryBean.setName("addData");

        return cronTriggerFactoryBean;
    }

    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactoryBean(Trigger trigger){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setOverwriteExistingJobs(true);
//        注册触发器
        schedulerFactoryBean.setTriggers(trigger);
//        启动延迟，程序启动后一秒才启动
        schedulerFactoryBean.setStartupDelay(1);
        return schedulerFactoryBean;
    }
}

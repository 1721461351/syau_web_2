package com.example.syau_web.service.Impl;

import com.example.syau_web.config.AsyncConfig;
import com.example.syau_web.constant.PortConstant;
import com.example.syau_web.domain.PortData;
import com.example.syau_web.enums.MyExceptionEnum;
import com.example.syau_web.exception.MyException;
import com.example.syau_web.repository.PortRepository;
import com.example.syau_web.service.PortService;
import com.example.syau_web.util.SerialSendDataUtil;
import com.example.syau_web.util.SerialUtil;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import org.hibernate.annotations.Source;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;

/**
 * create by wangyu
 * 2018/10/20 0020 21:14
 * <p>
 * 对串口操作的类
 */

@Service
@EnableScheduling//任务定时，此注解必加
//@EnableAsync
public class PortServiceImpl implements PortService {

    private final static Logger logger = LoggerFactory.getLogger(PortServiceImpl.class);
    /**
     * 把bean注入
     */
    /**----这几个暂时没有用到-------*/
    @Resource(name = "jobDetail")
    private JobDetail jobDetail;

    @Resource(name = "trigger")
    private CronTrigger trigger;

    @Resource(name = "scheduler")
    private Scheduler scheduler;

    @Resource(name = "executor")
    private Executor executor;

    @Autowired
    private PortRepository portRepository;

    @Override
    public PortData showPortData(PortData portData) {
        return portData;
    }

    /**掌握cron表达式的写法，如果记不住，可以去百度搜索cron在线生成器*/
    @Override
    @Scheduled(cron = PortConstant.UPDATE_TIME)//每一分钟增加一次
//    @Async
    public PortData insertPortData() {

        PortData portData = new PortData();
        List<Integer> portDataList=null;
        try {
            portDataList = SerialSendDataUtil.getPortData();
        } catch (InterruptedException e) {

            throw new MyException(MyExceptionEnum.INTERRUPTEDEXCEPTION);
        }
        PortData data = new PortData();
        /**id设置为了自动递增*/
//        /**-------------串口的id格式为串口名+当前时间-----------*/
        if(portDataList.size()!=0&&portDataList!=null){
        /**----获取温度-----*/
        double temperature = portDataList.get(0);
        /**----获取湿度-----*/
        double humidity = portDataList.get(1);
        /**----获取光照强度----*/
        double sun = portDataList.get(2);

        portData.setLightIntensity(sun);
        portData.setHumidity(humidity);
        portData.setTemperature(temperature);
//        添加到数据库
         data = portRepository.save(portData);}
        return data;
    }

    @Override
    public PortData deleteOneData(Integer portId) {
        return null;
    }

    /**
     * 数据监测，如果里面的数据大于某个值，就把前面的数据删除掉
     * @param portId
     * @return
     */
   /* @Scheduled(cron = "0/5 * * * * ?")//一分钟执行一次
    @Override
    public PortData deleteOneData(Integer portId) {
        *//**-----------得到数据库中的总条数-----------*//*
        long count = portRepository.count();
        if (count>10){
            int beginId = (int) (count-10);
            int maxData = (int) count;
            List<PortData> portDataList = portRepository.findAll();
            for (PortData portData:portDataList){
                //如果串口里的数据大于10条的话，就把10条以后的数据删除
                if (portData.getPortId()>maxData){
                    portRepository.deleteById(beginId);
                    beginId++;
                    maxData++;
                }
            }
        }
        return null;
    }*/

    @Scheduled(cron = "0/55 * * * * ?")//5秒钟执行一次
    @Override
    public List<PortData> selectAll() {
        List<PortData> portDataList = portRepository.findAll();
        return portDataList;
    }

    @Scheduled(cron = "0/55 * * * * ?")
    @Override
    public PortData findMaxId() {
        return portRepository.findMaxId();
    }
}

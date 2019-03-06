package com.example.syau_web.util;

import com.example.syau_web.constant.PortConstant;
import com.example.syau_web.enums.MyExceptionEnum;
import com.example.syau_web.exception.MyException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * create by wangyu
 * 2018/10/27 0027 15:32
 * <p>
 * 主要是被service层调用，获取温度，湿度，光照强度的工具类
 */
public class SerialSendDataUtil {

    /*@Value("${botelv}")
    private static Integer botelv;*/



    /**
     * 获取所有的数据
     *
     * @return
     */
    public static List<Integer> getPortData() throws InterruptedException {
        //        可用串口列表
        List<String> list = SerialUtil.findUsablePortName();
        String s = list.get(0);
        System.out.println(s);
        SerialPort port = null;
//            设置波特率
        try {
            port = SerialUtil.openPort(s, PortConstant.BOTELV);
        } catch (UnsupportedCommOperationException e) {
            throw new MyException(MyExceptionEnum.UNSUPPORTEDCOMMOPERATION);
        }

//            发送和温度相关的协议
        SerialUtil.sendToPort1(port,Integer.parseInt("FE",16));
        Thread.sleep(PortConstant.SLEEP_TIME);
        SerialUtil.sendToPort1(port,Integer.parseInt("68",16));
        Thread.sleep(PortConstant.SLEEP_TIME);
        SerialUtil.sendToPort1(port,Integer.parseInt("00",16));
        Thread.sleep(PortConstant.SLEEP_TIME);
        SerialUtil.sendToPort1(port,Integer.parseInt("68",16));
        Thread.sleep(PortConstant.SLEEP_TIME);
        SerialUtil.sendToPort1(port,Integer.parseInt("01",16));
        Thread.sleep(PortConstant.SLEEP_TIME);
//            SerialUtil.sendToPort1(port,Integer.parseInt("00",16));
//            SerialUtil.sendToPort1(port,Integer.parseInt("00",16));
//            SerialUtil.sendToPort1(port,Integer.parseInt("00",16));
//            SerialUtil.sendToPort1(port,Integer.parseInt("00",16));
//            SerialUtil.sendToPort1(port,Integer.parseInt("00",16));
        SerialUtil.sendToPort1(port,Integer.parseInt("16",16));
        List<Integer> data = new ArrayList<>();
        List<Integer> dataList = new ArrayList<>();
//            得到返回的数据
        try {
            data = SerialUtil.getData(port);
            /**-----------------把数据取出来------------------*/
            if (data!=null&&data.size()!=0) {
//            代表的是温度
                Integer temperature = data.get(4);
//            代表的是湿度
                Integer humidity = data.get(5);
                //得到是光照强度
                Integer lightIntensity = data.get(6);
                dataList.add(temperature);
                dataList.add(humidity);
                dataList.add(lightIntensity);
                /*dataList = Stream
                        .of(temperature, humidity, lightIntensity)
                        .collect(Collectors.toList());*/
            }

        } catch (IOException e) {
//                抛出io异常
            throw new MyException(MyExceptionEnum.IOEXCEPTION);
        }finally {
            SerialUtil.closePort(port);

        }
        return dataList;
    }
}


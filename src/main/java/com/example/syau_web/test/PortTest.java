package com.example.syau_web.test;

import com.example.syau_web.config.PortConfig;
import com.example.syau_web.constant.PortConstant;
import com.example.syau_web.enums.MyExceptionEnum;
import com.example.syau_web.exception.MyException;
import com.example.syau_web.util.SerialSendDataUtil;
import com.example.syau_web.util.SerialUtil;
import gnu.io.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 关于串口的测试
 * create by wangyu
 * 2018/10/20 0020 18:36
 */
public class PortTest {
    /**------引入波特率-----------*//*

    @Autowired
    private PortConfig portConfig;

    @Value("${botelv}")*/
    private Integer data;
    /**
     * 串口的测试
     * @throws UnsupportedCommOperationException
     * @throws IOException
     */
    @Test
    public void test1() throws UnsupportedCommOperationException, IOException, InterruptedException {
         /**--------能够找到jdk的路径---------------*/
        System.out.println("指向的路径是" + System.getProperty("java.library.path"));

        System.out.println(data);
//        找到可用的串口
        Enumeration<CommPortIdentifier> usablePort = SerialUtil.findUsablePort();


//        可用串口列表
        List<String> list = SerialUtil.findUsablePortName();
        String s1 = list.get(0);
        System.out.println(s1);
        System.out.println(data);
//               波特率
//            SerialPort port = SerialUtil.openPort(s1, PortConstant.BOTELV);
            SerialPort port = SerialUtil.openPort(s1, PortConstant.BOTELV);



            /**--------------------------按照16进制进行发送数据-------------------------*/
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
//        Thread.sleep(1000);
            SerialUtil.sendToPort1(port,Integer.parseInt("16",16));

//            不用循环了，直接就是数组
            List<Integer> data = SerialUtil.getData(port);
            for (Integer i:data){
                System.out.print(i);
            }


            System.out.println(data);

//            取数据域
//            System.out.println(data.get(0));
//            System.out.println(data.get(1));
//            System.out.println(data.get(2));


//            //            从串口中读取数据
//            byte[] bytes = SerialUtil.readFromPort(port);
//            System.out.println(bytes);

            SerialUtil.closePort(port);
        }

    @Test
    public void test2() throws UnsupportedCommOperationException, IOException {
        List<String> list = SerialUtil.findUsablePortName();

        for (String s : list) {

            SerialPort port = SerialUtil.openPort(s, PortConstant.BOTELV);

//            byte[] bytes = SerialUtil.readFromPort(port);
            List<Integer> data = SerialUtil.getData(port);
            System.out.println(data);
            SerialUtil.closePort(port);
        }


    }


    @Test
    public void test3() throws UnsupportedCommOperationException, IOException {
        List<String> list = SerialUtil.findUsablePortName();

        for (String s : list) {
            SerialPortEventListener listener =new SerialPortEventListener() {
                @Override
                public void serialEvent(SerialPortEvent serialPortEvent) {
//                    serialPortEvent.
                }
            };
            SerialPort port = SerialUtil.openPort(s, 4800);
            SerialUtil.getData(port);
            SerialUtil.closePort(port);
        }


    }

    @Test
    public void test4() {
        List<String> list = SerialUtil.findUsablePortName();
        for (String s : list) {
//            SerialUtil.addListener();
        }
    }

    @Test
    public void test5() throws InterruptedException {
        List<String> list = SerialUtil.findUsablePortName();
        String s = list.get(0);
        System.out.println(s);
        SerialPort port = null;
//            设置波特率
        try {
            port = SerialUtil.openPort(s, 4800);
        } catch (UnsupportedCommOperationException e) {
            throw new MyException(MyExceptionEnum.UNSUPPORTEDCOMMOPERATION);
        }

//            发送和温度相关的协议
        SerialUtil.sendToPort1(port,Integer.parseInt("FE",16));
        Thread.sleep(1000);
        SerialUtil.sendToPort1(port,Integer.parseInt("68",16));
        Thread.sleep(1000);
        SerialUtil.sendToPort1(port,Integer.parseInt("00",16));
        Thread.sleep(1000);
        SerialUtil.sendToPort1(port,Integer.parseInt("68",16));
        Thread.sleep(1000);
        SerialUtil.sendToPort1(port,Integer.parseInt("01",16));
        Thread.sleep(1000);
//            SerialUtil.sendToPort1(port,Integer.parseInt("00",16));
//            SerialUtil.sendToPort1(port,Integer.parseInt("00",16));
//            SerialUtil.sendToPort1(port,Integer.parseInt("00",16));
//            SerialUtil.sendToPort1(port,Integer.parseInt("00",16));
//            SerialUtil.sendToPort1(port,Integer.parseInt("00",16));
        Thread.sleep(1000);
        SerialUtil.sendToPort1(port,Integer.parseInt("16",16));
        List<Integer> data = null;
//            得到返回的数据
        try {
            data = SerialUtil.getData(port);
        } catch (IOException e) {
//                抛出io异常
            throw new MyException(MyExceptionEnum.IOEXCEPTION);
        }

        /**-----------------把数据取出来------------------*/
//            代表的是温度
        Integer temperature = data.get(4);
//            代表的是湿度
        Integer humidity = data.get(5);
        Integer lightIntensity =data.get(6);



        /**-----------------把数据存放在list集合中--------------*/
        List<Integer> dataList = Stream
                .of(temperature,humidity,lightIntensity)
                .collect(Collectors.toList());

        dataList.stream().forEach(d-> System.out.println(d));

//            把数据拼装起来
//        double temperature = decate * 10 + unit;

//        关闭串口
        SerialUtil.closePort(port);
    }

    @Test
    public void testService(){
        try {
            List<Integer> portData = SerialSendDataUtil.getPortData();
//            portData.stream().forEach(integer -> System.out.println(integer));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

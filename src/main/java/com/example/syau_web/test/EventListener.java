package com.example.syau_web.test;

import com.example.syau_web.util.SerialUtil;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.TooManyListenersException;

/**
 * create by wangyu
 * 2018/10/21 0021 10:45
 * <p>
 * 串口的监听，
 * 现在没有用到
 */
public class EventListener implements SerialPortEventListener {

    SerialPort serialPort = null;
    InputStream inputStream = null;
    Thread thread = null;

    public EventListener() throws IOException {
        try {
            serialPort = SerialUtil.openPort("COM4", 11520);
            inputStream = serialPort.getInputStream();
//            向串口添加点击事件
            //                serialPort.addEventListener(this);
            SerialUtil.addListener(serialPort, this);
//                设置当窗口有可用数据的时候触发
            serialPort.notifyOnDataAvailable(true);
        } catch (UnsupportedCommOperationException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }


        }

    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        byte[] bytes = new byte[1024];
//        记录到达串口但是未被读取的数据
        int avalidableBytes = 0;
//        如果是数据可用的时间发送，则进行数据的读写
        if (serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                avalidableBytes = inputStream.read(bytes);
                while (avalidableBytes > 0) {
                    inputStream.read(bytes);
                    for (int i = 0; i < bytes.length && i < avalidableBytes; i++) {
//                         输出数据
                        System.out.println("数据：" + (char) bytes[i]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new EventListener();
    }
}



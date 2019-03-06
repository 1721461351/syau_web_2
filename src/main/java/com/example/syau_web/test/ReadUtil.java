package com.example.syau_web.test;

import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;

/**
 * create by wangyu
 * 2018/10/21 0021 19:17
 *
 * 没用上
 */
public class ReadUtil {

    public static String readPort(SerialPort serialPort){
        InputStream inputStream =null;
        String s =null;

        try {
            inputStream = serialPort.getInputStream();
            byte[] bytes = new byte[16];
            int len = 0;
            while ((len=inputStream.read(bytes))!=-1){
                s=new String(bytes,0,len);

//                return s;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return s;
    }
}

package com.example.syau_web.test;

import com.example.syau_web.util.SerialUtil;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

/**
 * create by wangyu
 * 2018/10/21 0021 11:08
 * <p>
 * 没用上
 */
public class Writer {

    public static void main(String[] args) throws UnsupportedCommOperationException {
        SerialPort serialPort = null;
        serialPort = SerialUtil.openPort("COM4", 11520);
        SerialUtil.sendToPort(serialPort, "1".getBytes());
        SerialUtil.closePort(serialPort);
    }
}

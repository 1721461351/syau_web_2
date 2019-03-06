package com.example.syau_web.util;

import com.example.syau_web.constant.PortConstant;
import com.example.syau_web.enums.MyExceptionEnum;
import com.example.syau_web.exception.MyException;
import gnu.io.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;

/**
 * create by wangyu
 * 2018/10/19 0019 15:55
 * <p>
 * 此类为java实现串口通信的工具类
 */
public class SerialUtil {

    /**
     * 获得所有的可用端口
     *
     * @return 所有的可用端口
     */
    public static Enumeration<CommPortIdentifier> findUsablePort() {
        Enumeration<CommPortIdentifier> usablePort = CommPortIdentifier.getPortIdentifiers();
        return usablePort;
    }


    /**
     * 此方法是得到所有串口的可用端口的名字
     *
     * @return 可用端口名列表
     */
    public static List<String> findUsablePortName() {
//        获得所有的可用端口
        Enumeration<CommPortIdentifier> usablePort = findUsablePort();
//        创建端口名字的list集合
        List<String> usablePortNameList = new ArrayList<>();
//        将可用端口名添加到usablePortList集合中
        while (usablePort.hasMoreElements()) {
//            得到端口的名字
            String usablePortName = usablePort.nextElement().getName();
            usablePortNameList.add(usablePortName);
        }
        return usablePortNameList;

    }


    /**
     * 打开串口  就获得了串口对象
     *
     * @param portName 端口名字
     * @param baudrate 波特率
     * @return 串口对象
     * @throws UnsupportedCommOperationException
     */
    public static SerialPort openPort(String portName, int baudrate) throws UnsupportedCommOperationException {

        try {
//            通过端口名识别端口
            CommPortIdentifier commPortIdentifier = CommPortIdentifier.getPortIdentifier(portName);

            try {
//            打开端口
                CommPort commPort = commPortIdentifier.open(portName, 20000);//i代表超时时间

//                判断此端口是否是串口类的实例
                if (commPort instanceof SerialPort) {//如果是
                    SerialPort serialPort = (SerialPort) commPort;

//                    设置串口的参数
                    /**
                     * 此类的参数之后我要提取出来
                     * todo
                     */
                    serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                    return serialPort;
                } else {
                    throw new MyException(MyExceptionEnum.NOTSUCHPORT);
                }
            } catch (PortInUseException e) {
                throw new MyException(MyExceptionEnum.PORTINUSER);
            }
        } catch (NoSuchPortException e) {
//            抛出自定义异常
            throw new MyException(MyExceptionEnum.NOTSUCHPORT);
        }
    }

    /**
     * 关闭串口
     *
     * @param serialPort 要关闭的串口对象
     */
    public static void closePort(SerialPort serialPort) {
        if (serialPort != null) {
            serialPort.close();
            serialPort = null;
        }
    }

    /**
     * 向串口中发送数据
     *
     * @param serialPort 串口对象
     * @param data       待发送的数据
     */
    public static void sendToPort(SerialPort serialPort, byte[] data) {
        OutputStream outputStream = null;
        try {
            outputStream = serialPort.getOutputStream();
//        写数据

            outputStream.write(data);
//        刷新
            outputStream.flush();
        } catch (IOException e) {
            throw new MyException(MyExceptionEnum.IOEXCEPTION);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    throw new MyException(MyExceptionEnum.IOEXCEPTION);
                }
            }
        }
    }


    /**
     * 读取数据
     *
     * @param serialPort
     * @return
     */
    public static byte[] readFromPort(SerialPort serialPort) throws IOException {
        InputStream inputStream = null;
        byte[] bytes = null;
        inputStream = serialPort.getInputStream();
        int len = inputStream.available();
        while (len != 0) {
            bytes = new byte[len];
            int i = inputStream.read(bytes);
            System.out.println(i);
            len = inputStream.available();
        }
        if (inputStream != null) {
            inputStream.close();
        }
        return bytes;
    }

    /**
     * 给串口添加监听器
     *
     * @param serialPort              串口对象
     * @param serialPortEventListener 串口监听器
     */
    public static void addListener(SerialPort serialPort, SerialPortEventListener serialPortEventListener) {
        try {
//            给串口添加监听器
            serialPort.addEventListener(serialPortEventListener);
//            设置当有数据到达，唤醒监听线程
            serialPort.notifyOnDataAvailable(true);
//            设置当通信中断时候唤醒中断的线程
            serialPort.notifyOnBreakInterrupt(true);
        } catch (TooManyListenersException e) {
            throw new MyException(MyExceptionEnum.TOOMANYLISTENERS);
        }

    }


    /**
     * 此方法为修改后的数据读入方法，
     * 解决了死循环的问题 错误原因还是因为按照字符读取了数据
     *
     * @param serialPort
     * @return list集合
     * @throws IOException
     */
    public static List<Integer> getData(SerialPort serialPort) throws IOException {


//        定义一个集合，把数据都存放在集合里
        List<Integer> list = new ArrayList<>();
        InputStream inputStream = serialPort.getInputStream();
        FileInputStream fileInputStream =new FileInputStream("a.txt");
        byte[] bytes = new byte[16];
//        int i = inputStream.read();
//        System.out.println(i);
        int len = 0;
        String data = null;

        FileOutputStream fileOutputStream = new FileOutputStream("a.txt");

        try {
//            为了能够获得数据，这里让程序睡了0.1s。
            Thread.sleep(PortConstant.SLEEP_TIME_RED);
        } catch (InterruptedException e) {
//            抛出自定的异常
            throw new MyException(MyExceptionEnum.INTERRUPTED);
        }
        while ((len = inputStream.read()) != -1) {
            fileOutputStream.write(len);
//            System.out.println("len="+len);
            list.add(len);
//            把元素都放在list集合中
        }

//        当把数组存放在数组里的时候，这里的代码也就可有可无了
        int lenght =0;
        while ((lenght = fileInputStream.read())!=-1){
//            data = new String(bytes,0,lenght);
//            System.out.println("lenght="+lenght);
        }

//         len = inputStream.read(bytes);

        System.out.println(data);


        inputStream.close();
        fileOutputStream.close();
        return list;

    }


    public static String setData(SerialPort serialPort, String data) throws IOException {

        OutputStream outputStream = serialPort.getOutputStream();
//        写数据

        outputStream.write(data.getBytes());
//        刷新
        outputStream.flush();
        if (outputStream != null) {
            outputStream.close();
        }

        return "写书数据成功";
    }

    /**---------------------------------以int类型发送数据------------------------------------*/
    public static void sendToPort1(SerialPort serialPort, int data) {
//        写入int类型的数据
        OutputStream outputStream = null;
        try {
            outputStream = serialPort.getOutputStream();
//        写数据

            outputStream.write(data);
//        刷新
            outputStream.flush();
        } catch (IOException e) {
            throw new MyException(MyExceptionEnum.IOEXCEPTION);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    throw new MyException(MyExceptionEnum.IOEXCEPTION);
                }
            }
        }
    }
}

package com.example.syau_web.util;

import java.util.Random;

/**
 * create by wangyu
 * 2018/10/22 0022 9:22
 * <p>
 * 产生随机数的类
 */
public class RandomUtil {

    /**
     * 生成随机数
     * 格式：日期+随机数
     *
     * @return
     */
    public static synchronized String getUniqueKey() {
        Random random = new Random();
//        随机生成一个五位数
        Integer a = random.nextInt(90000) + 10000;
        return String.valueOf(System.currentTimeMillis() + a);
    }
}

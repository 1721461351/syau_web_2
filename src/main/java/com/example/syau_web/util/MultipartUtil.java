package com.example.syau_web.util;

import com.example.syau_web.domain.ProductInfo;
import com.example.syau_web.enums.MyExceptionEnum;
import com.example.syau_web.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by wangyu
 * 2018/10/23 0023 13:36
 * <p>
 * 此类是文件上传下载的工具类
 */
public class MultipartUtil {
    private final static Logger logger = LoggerFactory.getLogger(MultipartUtil.class);

    public static Map<String, Object> upload(MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        //        判断文件是否为空
        if (file.isEmpty()) {
            logger.info("文件为空");
        }

//        获取文件名
        String filename = file.getOriginalFilename();

//        设置随机数(当成新的文件的名字)
        String uniqueKey = RandomUtil.getUniqueKey();

//        获取文件的后缀名
        String suffixName = filename.substring(filename.lastIndexOf("."));

//        设置文件的存储路径
//        String filePath = "D://SYAUWEB//";
        String filePath = "E://myweb//syau_web_1//src//main//webApp//upload//";
        String path = filePath + filename;

        File dest = new File(path);

//        判断文件夹是否存在
        if (!dest.exists()) {//如果不存在
            dest.getParentFile().mkdirs();//创建文件夹
        }

        try {

            /**-----------------写入文件---------------*/
            file.transferTo(dest);
        } catch (IOException e) {
//            抛出自定义异常
            throw new MyException(MyExceptionEnum.IOEXCEPTION);
        }
        /**------------把结果传入map集合-----------------*/
        map.put("文件名", filename);
        map.put("文件后缀名", suffixName);
        map.put("文件存储路径", filePath);
        map.put("新名字", uniqueKey);

        return map;
    }
}

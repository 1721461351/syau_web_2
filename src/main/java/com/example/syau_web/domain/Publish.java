package com.example.syau_web.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import java.util.Date;

/**
 * create by wangyu
 * 2018/10/25 0025 19:36
 *
 * 发布公告的类
 */
@Data
public class Publish {

    /**
     * 发布动态的唯一编号
     */
    private Integer publishId;

    /**
     * 与学号相对应
     */
    private Integer userNumber;

    /**
     * 发布的内容
     */
    private String publishContent;

    /**
     * 发表时间
     */
    private Date createTime;

    /**
     * 文件的名字
     */
    private String fileName;
}

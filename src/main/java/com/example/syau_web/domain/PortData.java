package com.example.syau_web.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * create by wangyu
 * 2018/10/20 0020 21:05
 *
 * 从串口传来的数据的类
 */
@Entity
@Data
public class PortData {

    /**串口的id*/
    @Id
    @GeneratedValue
    private Integer portId;

    /**温度*/
    private Double temperature;

    /**湿度*/
    private Double humidity;

    /**光照强度*/
    private Double lightIntensity;

    /**插入数据库的时间*/
    private Date createTime;

    }

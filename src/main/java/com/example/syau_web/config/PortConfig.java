package com.example.syau_web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * create by wangyu
 * 2018/11/3 0003 16:14
 */
@ConfigurationProperties(prefix = "port")
public class PortConfig {

    @Value("${botelv}")
    private Integer botelv;

    public Integer getBotelv() {
        return botelv;
    }

    public void setBotelv(Integer botelv) {
        this.botelv = botelv;
    }
}

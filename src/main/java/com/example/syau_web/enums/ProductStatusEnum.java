package com.example.syau_web.enums;

import lombok.Getter;

/**
 * create by wangyu
 * 2018/11/9 0009 12:32
 */
@Getter
public enum ProductStatusEnum {
    UP(0,"在架状态"),
    DOWN(1,"下架状态");

    private Integer code;
    private String msg;

    ProductStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

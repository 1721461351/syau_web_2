package com.example.syau_web.enums;

/**
 * create by wangyu
 * 2018/10/24 0024 14:07
 * <p>
 * 枚举类
 */
public enum DataEnum {
    ISGUARD(1, "是管理员"),
    NOTISGUARD(0, "是普通学生");

    private String msg;
    private Integer code;

    DataEnum(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}

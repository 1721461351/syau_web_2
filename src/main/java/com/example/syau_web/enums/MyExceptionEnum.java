package com.example.syau_web.enums;

import com.example.syau_web.exception.MyException;

/**
 * create by wangyu
 * 2018/10/19 0019 18:38
 */
public enum MyExceptionEnum {
    NOTSUCHPORT(1, "没有这样的端口"),
    PORTINUSER(2, "端口已经被占用"),
    IOEXCEPTION(3, "io转换异常"),
    TOOMANYLISTENERS(4, "监听的类对象过多"),
    UNSUPPORTEDCOMMOPERATION(5, "不支持这样的端口操作"),
    INTERRUPTED(6, "数据延时出现了问题"),
    SERVLETERROR(7, "servlet出现了异常"),
    INTERRUPTEDEXCEPTION(8,"线程中断"),

    PRODUCT_STATUS_ERROR(9,"商品状态不正确"),
    PRODUCT_NOT_NULL(10,"商品状态不能为空"),

    PRODUCT_UP_ERROR(11,"商品上架失败");
    private Integer code;
    private String message;

    MyExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    MyExceptionEnum() {

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

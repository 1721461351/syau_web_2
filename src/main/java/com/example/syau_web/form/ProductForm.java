package com.example.syau_web.form;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

/**
 * create by wangyu
 * 2018/11/10 0010 11:13
 */
@Data
public class ProductForm {
    /**商品编号*/
    private String productId;

    /**商品名字*/
    private String productName;

    /**商品价格*/
    private Double productPrice;

    /**商品库存*/
    private Integer productNum;

    /**商品图片*/
    private String productImg;

    /**商品描述*/
    private String productDescription;

    /**商品所属类型*/
    private String productType;

    /**学号 外键*/
    private Integer userNumber;

   /* *//**商品的上架状态*//*
    private Integer productStatus;*/

    /**创建时间*/
//    private Date createTime;

}

package com.example.syau_web.domain;

import com.example.syau_web.enums.ProductStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * create by wangyu
 * 2018/10/22 0022 8:23
 *
 * 商品详情
 */
@Entity
@Data
public class ProductInfo {

    /**商品编号*/
    @Id
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

    /**商品的上架状态   默认为在架状态*/
    private Integer productStatus=ProductStatusEnum.UP.getCode();

    /**创建时间*/
    private Date createTime;
    //    @JsonIgnore
//    private User user;
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }


    @Override
    public String toString() {
        return "ProductInfo{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productNum=" + productNum +
                ", productImg='" + productImg + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productType='" + productType + '\'' +
                ", userNumber=" + userNumber +
                ", productStatus=" + productStatus +
                ", createTime=" + createTime +
                '}';
    }
}

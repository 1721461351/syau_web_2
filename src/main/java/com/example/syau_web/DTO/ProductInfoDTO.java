package com.example.syau_web.DTO;

import com.example.syau_web.domain.Category;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;

/**
 * create by wangyu
 * 2018/10/22 0022 12:58
 *
 * 加了一个类型属性
 */
public class ProductInfoDTO {


    /**商品编号*/
    private String productId;

    /**商品名字*/
    private String productName;

    /**商品价格*/
    private Double productPrice;

    /**商品库存*/
    private Integer productNum;

    /**
     * 商品图片
     * 注意这里写的是数据类型
     *
     */
    private MultipartFile productImg;

    /**商品描述*/
    private String productDescription;

    /**商品所属类型
     * 因为这里是int类型而jsp界面是String类型的，
     * 所以要把数据传到category的categoryName中
     **/
    private String productType;

    /**商品所属的类型   多对一*/
    private Category category;

    /**学号 外键*/
    private Integer userNumber;

    public Integer getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public MultipartFile getProductImg() {
        return productImg;
    }

    public void setProductImg(MultipartFile productImg) {
        this.productImg = productImg;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

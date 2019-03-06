package com.example.syau_web.DTO;

import com.example.syau_web.domain.ProductInfo;
import com.example.syau_web.enums.DataEnum;
import lombok.Data;

import javax.persistence.Id;
import java.util.List;

/**
 * create by wangyu
 * 2018/11/4 0004 18:03
 */
@Data
public class UserDTO {

    /**学号*/
    private Integer number;

    /**姓名*/
    private String username;

    /**密码*/
    private String password;

    /**性别*/
    private String sex;

    /**年级*/
    private String grade;

    /**专业*/
    private String career;

    /**角色： 0：学生  1：管理员 默认为0*/
    private Integer role= DataEnum.NOTISGUARD.getCode();

    /**用于发布动态*/
    private String publish;

    private Integer telephone;

    private String email;

    private List<ProductInfo> productInfoList;

    public List<ProductInfo> getProductInfoList() {
        return productInfoList;
    }

    public void setProductInfoList(List<ProductInfo> productInfoList) {
        this.productInfoList = productInfoList;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }
}

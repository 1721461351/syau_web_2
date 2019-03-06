package com.example.syau_web.domain;

import com.example.syau_web.enums.DataEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * create by wangyu
 * 2018/10/19 0019 20:13
 */
@Entity
@Data
public class User {

    /**学号*/
    @Id
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

    private String email;

    private String telephone;
}

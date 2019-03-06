package com.example.syau_web.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * create by wangyu
 * 2018/10/19 0019 21:00
 *
 * 类目表
 */
@Entity
@Data
public class Category {

    /**类目id*/
    @Id
    private String categoryId;

    /**类目名称*/
    private String categoryName;

    /**类目类型*/
    private Integer categoryType;


}

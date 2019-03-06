package com.example.syau_web.DTO;

import com.example.syau_web.domain.User;
import com.example.syau_web.enums.DataEnum;
import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

/**
 * create by wangyu
 * 2018/10/26 0026 13:46
 * <p>
 * 此类多了user对象
 */
@Data
public class PublishDTO {
    /**
     * 发布动态的唯一编号
     */
    private Integer publishId;

    /**
     * 与学号相对应
     */
    private Integer userNumber;

    /**
     * 发布的内容
     */
    private String publishContent;

    /**
     * 发表时间
     */
    private Date createTime;

    /**
     * 发布人的用户信息
     */
    private User user;


    private String fileName;

}

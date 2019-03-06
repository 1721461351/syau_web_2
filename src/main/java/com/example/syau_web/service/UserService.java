package com.example.syau_web.service;

import com.example.syau_web.domain.User;

/**
 * create by wangyu
 * 2018/10/19 0019 20:25
 */
public interface UserService {

    /**
     * 登录功能
     * @param number 学号
     * @param password 密码
     * @return
     */
    public abstract User login(Integer number,String password);

    /**
     * 根据学号修改密码
     * @param username
     * @return
     */
    public User savePassword(Integer username,String password);
}

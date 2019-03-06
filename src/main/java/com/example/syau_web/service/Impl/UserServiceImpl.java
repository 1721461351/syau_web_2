package com.example.syau_web.service.Impl;

import com.example.syau_web.domain.User;
import com.example.syau_web.repository.UserRepository;
import com.example.syau_web.service.UserService;
import com.example.syau_web.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * create by wangyu
 * 2018/10/19 0019 20:27
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 用户登录的service层
     *
     * @param number   学号
     * @param password 密码
     * @return
     */
    @Override
    public User login(Integer number, String password) {

        /**------MD5加密算法，保证数据的安全性---------*/
        password = MD5Util.md5(password);
        User user = userRepository.findByNumberAndPassword(number, password);
//        controller需要判断一下用户角色
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User savePassword(Integer username,String password) {
        User user = userRepository.findById(username).get();
        String md5 = MD5Util.md5(password);
        user.setPassword(md5);
        return userRepository.save(user);
    }
}

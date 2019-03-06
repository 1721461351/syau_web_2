package com.example.syau_web.repository;

import com.example.syau_web.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * create by wangyu
 * 2018/10/19 0019 20:19
 */
public interface UserRepository extends JpaRepository<User,Integer> {


    /**
     *          登录功能
     *          根据学号和密码判断登录状态
     * @param number
     *          学号
     * @param password
     *          密码
     * @return
     */
    public User findByNumberAndPassword(Integer number,String password);

    public List<User> findAllByRole(Integer role);
}

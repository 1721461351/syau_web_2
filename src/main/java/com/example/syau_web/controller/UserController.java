package com.example.syau_web.controller;

import com.example.syau_web.domain.User;
import com.example.syau_web.enums.MyExceptionEnum;
import com.example.syau_web.enums.ResultEnum;
import com.example.syau_web.exception.MyException;
import com.example.syau_web.repository.UserRepository;
import com.example.syau_web.service.Impl.UserServiceImpl;
import com.example.syau_web.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.Map;

/**
 * create by wangyu
 * 2018/10/25 0025 21:46
 */
@Controller
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    /**
     * 用户登录
     *
     * @param request  请求
     * @param response 响应
     */
//    @RequestMapping(value = "/admin/login.action", method = RequestMethod.POST)
    @RequestMapping(value = "/login.action", method = RequestMethod.POST)
    public void login(HttpServletRequest request, HttpServletResponse response) {
//        user.setUsername("王宇");
//        user.setNumber(2016188023);
//        session.setAttribute("user",user);
        String number = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.login(Integer.valueOf(number), password);

        /**todo 需要判断用户的权限，管理员还是普通用户*/
//        如果查找到有此用户
        if (user != null) {
            try {
                if (user.getRole() == 0) {//学生的权限
                    request.getSession().setAttribute("user", user);
                    request.getRequestDispatcher("/mypage/student.jsp").forward(request, response);
                }
                if (user.getRole() == 1) {//老师的权限
                    request.getSession().setAttribute("user", user);
                    request.getRequestDispatcher("/mypage/teacher.jsp").forward(request, response);
                }

                if (user.getRole() == 2) {//管理员的权限
                    request.getSession().setAttribute("user", user);
                    request.getRequestDispatcher("/admin/login/home.jsp").forward(request, response);
                }
//                request.getRequestDispatcher("/admin/login/home.jsp").forward(request, response);
//                request.getRequestDispatcher("/mypage/login/home.jsp").forward(request, response);
            } catch (ServletException e) {
                throw new MyException(MyExceptionEnum.SERVLETERROR);
            } catch (IOException e) {
                throw new MyException(MyExceptionEnum.IOEXCEPTION);
            }

        }
//        如果密码错误的情况
        else {
            request.setAttribute("error", "用户名或密码错误");
            try {
                request.getRequestDispatcher("/mypage/login1.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


//        return new ModelAndView();
    }


    /**
     * PathParam注解是获取表单里的参数的
     *
     * @param oldpassword
     * @param newpassword
     * @param number
     * @return
     */
    @RequestMapping("/savePassword")
    public ModelAndView savePassword(@PathParam("oldpassword") String oldpassword,
                                     @PathParam("newpassword") String newpassword,
                                     @PathParam("newpassword") Integer number,
                                     Map<String, Object> map,
                                     HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        User user = userRepository.findById(number).get();
        String md5 = MD5Util.md5(oldpassword);
        //这里因为是字符类型的数据，所以要用到equals
        if (user != null && user.getPassword().equals(md5)) {
            userService.savePassword(number, newpassword);
            map.put("url", "mypage/login1.jsp");
            map.put("msg", ResultEnum.MODIFIED_PASSWORD.getMessage());
            view.setViewName("common/success");
            return view;
        } else {
            request.getSession().setAttribute("number",user.getNumber());
            map.put("url", "login1.action");
            map.put("msg", ResultEnum.NUMBER_PASSWORD_ERROR.getMessage());
            view.setViewName("common/error");
            return view;
        }
//        userService.savePassword();
    }
}


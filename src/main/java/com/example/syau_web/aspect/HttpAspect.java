package com.example.syau_web.aspect;

import com.example.syau_web.constant.CookieConstant;
import com.example.syau_web.domain.User;
import com.example.syau_web.exception.LoginException;
import com.example.syau_web.util.CookieUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * create by wangyu
 * 2018/10/22 0022 15:53
 *
 * 此类是aop处理请求类
 */
@Aspect
@Component
public class HttpAspect {
    /**
     * 这个日志打印的时候logger是spring自带的日志
     */
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    /**--------------------------配置切面，可被其他的方法调用------------------------------*/
    @Pointcut("execution(public * com.example.syau_web.afreemarkeradmin.controller.*Controller.*(..))")
    public void log() {

    }

    /**
     * 方法前的拦截器
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {

//        url
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("url={}", request.getRequestURL());

//        method
        logger.info("method={}", request.getMethod());

//        ip
        logger.info("ip={}", request.getRemoteAddr()); /**注意这个是获取ip的方法*/

//        类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

//        参数
        logger.info("args={}",joinPoint.getArgs());
        logger.info("这是执行前的拦截器");
//        System.out.println("这是执行前的拦截器");


        /**--------------开始写登录拦截-----------------*/

        //查询cookie
        Cookie student_cookie = CookieUtil.getCookie(request, CookieConstant.STUDENT_TOKEN);
        Cookie guard_cookie = CookieUtil.getCookie(request, CookieConstant.TOKEN);
//        User user = (User) request.getSession().getAttribute("user");
        if (student_cookie==null&&guard_cookie==null){
            logger.warn("查询不到cookie");
            throw new LoginException();
        }
        /*if (guard_cookie==null){
            logger.warn("管理员的cookie中查询不到cookie");
            throw new LoginException();
        }*/
    }

    /**
     * 方法启动后的拦截器
     */
    @Before("log()")
    public void doAfter() {
        logger.info("这是方法执行后的拦截器");
//        System.out.println("这是执行后的拦截器");
    }


    /**
     * 利用此方法，打印返回的结果
     * @param object
     */
    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object){
        logger.info("response={}",object);
    }
}

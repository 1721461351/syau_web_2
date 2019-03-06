package com.example.syau_web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * create by wangyu
 * 2018/10/20 0020 9:07
 */
@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping("test1")
    public String test1(){
        return "admin/login/home.jsp";
    }

    @RequestMapping("test2")
    public String test2(){
        return "detail/control.jsp";
    }

    @RequestMapping("test3")
    public String test3(){
        return "detail/screenData.jsp";
    }

    @RequestMapping("test4")
    public String test4(){
        return "detail/2.html";
    }

    @RequestMapping("test5")
    public String test5(){
        return "3.jsp";
    }

    @RequestMapping("test6")
    public String test6(){
        return "myjsp/login1.jsp";
    }

    @RequestMapping("test7")
    public String test7(){
        return "myjsp/index.jsp";
    }

    @RequestMapping("test8")
    public String test8(){
        return "WebContent/eMonitor.jsp";
    }

    @RequestMapping("test9")
    public String test9(){
        return "WebContent/historicalData.jsp";
    }

    @RequestMapping("test10")
    public String test10(){
        return "templates/list.ftl";
    }

    @RequestMapping("test11")
    public String test11(){
        return "product/productList";
    }

    @RequestMapping("test12")
    public String test12(){
        return "common/error";
    }

    @RequestMapping("test14")
    public String test14(){
        return "category/index";
    }



    @RequestMapping("test13")
    public String test13(){
        return "product/index";
    }


    @RequestMapping("test15")
    public String test15(){
        return "category/list";
    }

    @RequestMapping("test20")
    public String test20(){
        return "WEB-INF/myjsp/login1.jsp";
    }

    @RequestMapping("test21")
    public String test21(){
        return "WEB-INF/myjsp/index.jsp";
    }

    @RequestMapping("test22")
    public String test22(){
        return "WEB-INF/myjsp/teacher.jsp";
    }

    @RequestMapping("test23")
    public String test23(){
        return "WEB-INF/myjsp/student.jsp";
    }

    @RequestMapping("test24")
    public String test24(){
        return "WEB-INF/myjsp/edit.jsp";
    }

    @RequestMapping(value = "login")
    public String test25(){
        return "mypage/login1.jsp";
    }

    @RequestMapping("test26")
    public String test26(){
        return "mypage/student.jsp";
    }

    @RequestMapping("test27")
    public String test27(){
        return "mypage/teacher.jsp";
    }

    @RequestMapping("index")
    public String test28(){
        return "mypage/index.jsp";
    }

    @RequestMapping("test29")
    public String test29(){
        return "mypage/exit.jsp";
    }

    @RequestMapping("test31")
    public String test31(){
        return "mypage/teacher.jsp";
    }

    @RequestMapping("test32")
    public String test32(){
        return "mypage/exit.jsp";
    }


}

package com.example.syau_web.controller;

import com.example.syau_web.domain.PortData;
import com.example.syau_web.service.Impl.PortServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * create by wangyu
 * 2018/10/30 0030 14:31
 *
 *串口和前端交互的类
 */
@RestController
public class PortController {

    @Autowired
    private PortServiceImpl portService;

    /**
     * 返回的是串口的list集合
     * @return
     */
    @RequestMapping("listPort.action")
    public ModelAndView selectAllPortData(HttpServletResponse response, HttpServletRequest request){
        List<PortData> portDataList = portService.selectAll();
        ModelAndView view = new ModelAndView();
        view.setViewName("WebContent/historicalData.jsp");
        view.addObject("data",portDataList);
        return view;

    }

    @RequestMapping("lastData.action")
    public ModelAndView findLastData(){
        PortData portData = portService.findMaxId();
        ModelAndView view = new ModelAndView();
        view.setViewName("WebContent/eMonitor.jsp");
        view.addObject("data",portData);
        return view;
    }
}

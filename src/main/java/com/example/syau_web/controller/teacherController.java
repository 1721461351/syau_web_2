package com.example.syau_web.controller;

import com.example.syau_web.domain.PortData;
import com.example.syau_web.service.Impl.PortServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by wangyu
 * 2018/11/18 0018 12:13
 * 哈哈哈
 * 特效满分
 *
 */
@Controller
public class teacherController {

    @Autowired
    private PortServiceImpl portService;
    @RequestMapping("showPortData")
    public ModelAndView showportData(Map<String,Object> map){
        //串口中最新的数据
        PortData maxData = portService.findMaxId();

        //串口中所有的数据
        List<PortData> portData = portService.selectAll();
        map.put("maxData", maxData);
        map.put("portData", portData);
        return new ModelAndView("showPortData",map);
    }

}

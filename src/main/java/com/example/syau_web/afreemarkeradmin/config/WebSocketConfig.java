package com.example.syau_web.afreemarkeradmin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * create by wangyu
 * 2018/11/11 0011 16:41
 * <p>
 * websocket的配置
 */

@Configuration
public class WebSocketConfig {

    @Bean(name = "socket")
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}

package com.example.syau_web.service;

import com.example.syau_web.enums.MyExceptionEnum;
import com.example.syau_web.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * create by wangyu
 * 2018/11/11 0011 16:46
 */
@Component
@ServerEndpoint("/webSocket")
public class WebSocket {

    private final static Logger logger = LoggerFactory.getLogger(WebSocket.class);

    private Session session;

    //定义一个websocket的容器
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        //把连接加入到容器中
        webSocketSet.add(this);
        logger.info("有新的连接，连接总数为:{}",webSocketSet.size());
    }

    @OnClose
    public void onClose(Session session){
        //移除连接
        webSocketSet.remove(this);
        logger.info("连接已断开，连接总数为：{}",webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message){
        logger.info("收到浏览器发来的消息:{}",message);
    }

    public void sendMessage(String message){
        //遍历容器
        for (WebSocket webSocket:webSocketSet){
            //打印出消息
            logger.info("发送消息:{}",message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                throw new MyException(MyExceptionEnum.IOEXCEPTION);
            }
        }

    }
}

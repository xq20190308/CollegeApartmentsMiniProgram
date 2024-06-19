package com.william.collegeapartmentsbacke.service.impl;

import com.william.collegeapartmentsbacke.pojo.entity.SessionBean;
import com.william.collegeapartmentsbacke.service.WebsocketService;
import com.william.collegeapartmentsbacke.websoket.WebSessionMannager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;


@Service
@Slf4j
public class WebsocketServiceImpl implements WebsocketService {

//    /**
//     * @Author William
//     * @Date 2024/6/19 20:15
//     * @Description 发送消息
//     * @Param [sessionBean, message]
//     * @Return void
//     * @Since version 1.0
//     */
//    @Override
//    public void sendMessage(SessionBean sessionBean, String message) throws IOException {
//        sessionBean.getWebSocketSession().sendMessage(new TextMessage(message));
//    }
//
//    /**
//     * @Author William
//     * @Date 2024/6/19 20:16
//     * @Description 群发消息
//     * @Param [message]
//     * @Return void
//     * @Since version 1.0
//     */
//    @Override
//    public void broadcastMessage(String message) throws IOException {
//        for(SessionBean sessionBean: WebSessionMannager.SESSION_POOL.values()){
//            sessionBean.getWebSocketSession().sendMessage(new TextMessage(message));
//        }
//    }

    /**
     * @param session 会话
     */
    @Override
    public void handleOpen(WebSocketSession session) {

    }

    /**
     * @param session 会话
     */
    @Override
    public void handleClose(WebSocketSession session) {

    }

    /**
     * @param session 会话
     * @param message 接收的消息
     */
    @Override
    public void handleMessage(WebSocketSession session, String message) {

    }

    /**
     * @param session 当前会话
     * @param message 要发送的消息
     * @throws IOException
     */
    @Override
    public void sendMessage(WebSocketSession session, String message) throws IOException {

    }

    /**
     * @param userId  用户id
     * @param message 要发送的消息
     * @throws IOException
     */
    @Override
    public void sendMessage(Integer userId, TextMessage message) throws IOException {

    }

    /**
     * @param userId  用户id
     * @param message 要发送的消息
     * @throws IOException
     */
    @Override
    public void sendMessage(Integer userId, String message) throws IOException {

    }

    /**
     * @param session 当前会话
     * @param message 要发送的消息
     * @throws IOException
     */
    @Override
    public void sendMessage(WebSocketSession session, TextMessage message) throws IOException {

    }

    /**
     * @param message 字符串消息
     * @throws IOException
     */
    @Override
    public void broadCast(String message) throws IOException {

    }

    /**
     * @param message 文本消息
     * @throws IOException
     */
    @Override
    public void broadCast(TextMessage message) throws IOException {

    }

    /**
     * @param session 会话
     * @param error   异常
     */
    @Override
    public void handleError(WebSocketSession session, Throwable error) {

    }
}

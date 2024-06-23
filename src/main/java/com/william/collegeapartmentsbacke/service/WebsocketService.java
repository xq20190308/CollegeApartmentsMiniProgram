package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.entity.ClientMessage;
import com.william.collegeapartmentsbacke.pojo.dto.MessageDTO;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;

public interface WebsocketService {

    void handleOpen(String userId,WebSocketSession session);

    void handleClose(WebSocketSession session);

    void handleMessage(String userId,WebSocketSession session, TextMessage message) throws IOException;

    void sendMessage(ClientMessage clientMessage) throws IOException;
    /**
     * 广播
     *
     * @param message 字符串消息
     * @throws IOException 异常
     */
    void broadCast(String message) throws IOException;
    /**
     * 处理会话异常
     *
     * @param session 会话
     * @param error   异常
     */
    void handleError(WebSocketSession session, Throwable error) throws IOException;

    List<MessageDTO> getHistoryMessageListByUserId(String userId);
//
//    /**
//     * 发送消息
//     *
//     * @param session 当前会话
//     * @param message 要发送的消息
//     * @throws IOException 发送io异常
//     */
//    void sendMessage(WebSocketSession session, String message) throws IOException;
//
//    /**
//     * 发送消息
//     *
//     * @param userId  用户id
//     * @param message 要发送的消息
//     * @throws IOException 发送io异常
//     */
//    void sendMessage(Integer userId, TextMessage message) throws IOException;
//
//    /**
//     * 发送消息
//     *
//     * @param userId  用户id
//     * @param message 要发送的消息
//     * @throws IOException 发送io异常
//     */
//    void sendMessage(Integer userId, String message) throws IOException;
//
//    /**
//     * 发送消息
//     *
//     * @param session 当前会话
//     * @param message 要发送的消息
//     * @throws IOException 发送io异常

//     */

//    void sendMessage(WebSocketSession session, TextMessage message) throws IOException;
//    /**
//     * 广播
//     *
//     * @param message 文本消息
//     * @throws IOException 异常
//     */

//    void broadCast(TextMessage message) throws IOException;



//    public void sendMessage(ClientSessionBean session, String message) throws IOException;
//
//    public void broadcastMessage(String message) throws IOException;

}

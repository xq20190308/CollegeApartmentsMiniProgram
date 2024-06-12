package com.william.collegeapartmentsbacke.websoket;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/websocket")
@Component
@Slf4j
@EnableScheduling
public class WebSocketServerEndpoint {
    static Map<String, Session> sessionMap = new ConcurrentHashMap<>();
    //连接建立时执行的操作
    @OnOpen
    public void onOpen(Session session){
        sessionMap.put(session.getId(),session);
        log.info("websocket is open");
    }
    //收到了客户端消息执行的操作
    @OnMessage
    public String onMessage(String text){
        log.info("收到了一条消息："+text);
        return "已收到你的消息";
    }
    //连接关闭的时候执行的操作
    @OnClose
    public void onClose(Session session){
        sessionMap.remove(session.getId());
        log.info("websocke t is close");
    }
    //每2s发送给客户端心跳消息
    @Scheduled(fixedRate = 2000)
    public void sendMsg() throws IOException {
        log.info("后端正在尝试发送心跳");
        for(String key:sessionMap.keySet()){
            sessionMap.get(key).getBasicRemote().sendText(" biu~biu~");
        }
    }
}

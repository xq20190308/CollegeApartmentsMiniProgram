package com.william.collegeapartmentsbacke.handler;

import com.william.collegeapartmentsbacke.service.UserService;
import com.william.collegeapartmentsbacke.service.WebsocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

@Component
@Slf4j
@EnableScheduling
public class WebSocketHandler extends AbstractWebSocketHandler {
//    private static Map<String, ClientSessionBean> sessionBeanMap ;
//    private static AtomicInteger clientIdMaker;
//    private static StringBuffer stringBuffer;
//    static {
//        sessionBeanMap = new ConcurrentHashMap<>();
//        clientIdMaker = new AtomicInteger(0);
//        stringBuffer = new StringBuffer();
//    }

    @Autowired
    private UserService userService;

    @Autowired
    private WebsocketService websocketService;



    /**
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        log.info("--"+session.getHandshakeHeaders().toString());
        log.info("websocketToken:"+session.getHandshakeHeaders().get("Authorization"));
        log.info("@@token:"+session.getHandshakeHeaders().get("token"));
        String token = session.getHandshakeHeaders().get("Authorization").get(0);
        String userId = userService.getUseridFromToken(token);
        session.getAttributes().put("userId", userId);
        websocketService.handleOpen(userId,session);

//        ClientSessionBean sessionBean = new ClientSessionBean(session,clientIdMaker.getAndIncrement());
//        sessionBeanMap.put(session.getId(),sessionBean);
//        log.info(sessionBeanMap.get(session.getId()).getClientId()+"建立了连接");
//        stringBuffer.append(sessionBeanMap.get(session.getId()).getClientId()+"进入了群聊<br/>");
//        sendMessage(sessionBeanMap);
    }

    /**
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        String userId = session.getAttributes().get("userId").toString();
        log.info(userId + ":" + message.getPayload());
        websocketService.handleMessage(userId,session, message);

//        log.info(sessionBeanMap.get(session.getId()).getClientId()+":"+message.getPayload());
//
//        stringBuffer.append(sessionBeanMap.get(session.getId()).getClientId()+":"+message.getPayload()+"<br/>");
//        sendMessage(sessionBeanMap);
    }

    /**
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
        websocketService.handleError(session,exception);
//        log.info(session.getHandshakeHeaders().get("userId").get(0)+"因传输错误，关闭了连接");
//        if(session.isOpen()){
//            session.close();
//        }
//        sessionBeanMap.remove(session.getId());
//        log.info(sessionBeanMap.get(session.getId()).getClientId()+":"+"因传输错误，关闭了连接");
    }


    /**
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        websocketService.handleClose(session);


//
//        int clientId = sessionBeanMap.get(session.getId()).getClientId();
//        sessionBeanMap.remove(session.getId());
//        log.info(clientId+"关闭了连接");
//        stringBuffer.append(clientId+"退出了群聊<br/>");
//        sendMessage(sessionBeanMap);
    }

//    public void sendMessage(Map<String, ClientSessionBean> sessionBeanMap){
//        for(String key:sessionBeanMap.keySet()){
//            try {
//                log.info(key+":"+sessionBeanMap.get(key).getClientId());
//                sessionBeanMap.get(key).getWebSocketSession().sendMessage(new TextMessage(stringBuffer.toString()));
//            } catch (IOException e) {
//                e.printStackTrace();
//                log.error(e.getMessage());
//            }
//        }
//    }

//    @Scheduled(fixedRate = 2000)
//    public void sendMsg() {
//        log.info("第二个心跳跳动");
//        for(String key:sessionBeanMap.keySet()){
//            WebSocketSession session = sessionBeanMap.get(key).getWebSocketSession();
//            if(session != null && session.isOpen() ){
//                try {
//                    session.sendMessage(new TextMessage("biu~biu~"));
//                } catch (IOException e) {
//                    log.error("发送消息时遇到了错误");
//                    throw new RuntimeException(e);
//
//                }
//            }
//        }
//    }
}

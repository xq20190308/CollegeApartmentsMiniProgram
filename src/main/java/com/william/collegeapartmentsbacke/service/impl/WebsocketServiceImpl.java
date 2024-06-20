package com.william.collegeapartmentsbacke.service.impl;

import com.alibaba.fastjson.JSONObject;
import cn.hutool.json.JSONArray;
import com.william.collegeapartmentsbacke.common.utils.JsonUtil;
import com.william.collegeapartmentsbacke.pojo.entity.ClientMessage;
import com.william.collegeapartmentsbacke.pojo.entity.ClientSessionBean;
import com.william.collegeapartmentsbacke.pojo.vo.ClientMessageVO;
import com.william.collegeapartmentsbacke.service.WebsocketService;
import com.william.collegeapartmentsbacke.websoket.ClientSessionMannager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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
//    public void sendMessage(ClientSessionBean sessionBean, String message) throws IOException {
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
//        for(ClientSessionBean sessionBean: ClientSessionMannager.SESSION_POOL.values()){
//            sessionBean.getWebSocketSession().sendMessage(new TextMessage(message));
//        }
//    }

    /**
     * @param session 会话
     */
    @Override
    public void handleOpen( String userId,WebSocketSession session) {

        ClientSessionBean clientSessionBean = new ClientSessionBean(session, ClientSessionMannager.getClientIdMaker());
        String clientUserId = session.getAttributes().get("userId").toString();
        ClientSessionMannager.addClientSessionBean(clientUserId, clientSessionBean);
        log.info(clientUserId+"建立了连接，现在总连接人数是"+ ClientSessionMannager.CLIENT_POOL.size());
//        stringBuffer.append(sessionBeanMap.get(session.getId()).getClientId()+"进入了群聊<br/>");
//        sendMessage(sessionBeanMap);
    }

    /**
     * @param session 会话
     */
    @Override
    public void handleClose(WebSocketSession session) {
        String clientUserId = session.getAttributes().get("userId").toString();
        ClientSessionMannager.removeClientSessionBean(clientUserId);
        log.info(clientUserId+" 的连接断开,现在总连接人数是"+ ClientSessionMannager.CLIENT_POOL.size());
    }

    /**
     * @param session 会话
     * @param message 接收的消息
     */
    @Override
    public void handleMessage(String userId, WebSocketSession session, TextMessage message) throws IOException {

        log.info("received a message：{}", message.getPayload());
        JSONObject obj = JSONObject.parseObject(message.getPayload());
        Integer type = Integer.valueOf(obj.get("type").toString());
        String data = obj.get("data").toString();

        String receivers = obj.get("receivers").toString();

//        List<Object> receiversObj = JsonUtil.jsonArrayToList(new JSONArray(obj.get("receivers").toString()));
//
//        log.info("receiversObj:"+receiversObj);
//        List<String> receivers = new ArrayList<>();
//        for (Object receiverObj : receiversObj) {
//            receivers.add(receiverObj.toString());
//        }

        LocalDateTime sendTime = LocalDateTime.now();
        ClientMessage clientMessage = new ClientMessage(userId,type,data,sendTime,receivers);
        sendMessage(clientMessage);
//        broadCast(clientMessage.getData());


    }

    /**
     * @param clientMessage
     * @throws IOException
     */
    @Override
    public void sendMessage(ClientMessage clientMessage) throws IOException {
        log.info("进入消息发送函数"+clientMessage.toString());
        ClientMessageVO messageVO = ClientMessageVO.builder()
                .type(clientMessage.getType())
                .senderUserId(clientMessage.getSenderUserId())
                .data(clientMessage.getData())
                .sendTime(clientMessage.getSendTime())
                .build();

        List<Object> receiversObj = JsonUtil.jsonArrayToList(new JSONArray(clientMessage.getReceivers()));
        log.info("receiversObj:"+receiversObj);
        List<String> receiversUserids = new ArrayList<>();
        for (Object receiverObj : receiversObj) {
            receiversUserids.add(receiverObj.toString());
        }
        
        for(String receiverId: receiversUserids){
            ClientSessionBean clientSessionBean =  ClientSessionMannager.getClientSessionBean(receiverId);
            if(clientSessionBean == null){
                continue;
            }

            WebSocketSession session = clientSessionBean.getWebSocketSession();
            if(session == null || !session.isOpen()){
                continue;
            }
            log.info("私聊给{}发送了："+messageVO.toString(),receiverId);
            String messageJsonStr = JSONObject.toJSONString(messageVO);
            log.info("json转换后："+ messageJsonStr);
            clientSessionBean.getWebSocketSession().sendMessage(new TextMessage(messageJsonStr));

        }
    }


    /**
     * @param message 字符串消息
     * @throws IOException
     */
    @Override
    public void broadCast(String message) throws IOException {
        for (ClientSessionBean clientSessionBean : ClientSessionMannager.CLIENT_POOL.values()){
            WebSocketSession session = clientSessionBean.getWebSocketSession();
            if(session.isOpen()){
                try {
                    session.sendMessage(new TextMessage("后端群发: "+message));
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @param session 会话
     * @param error   异常
     */
    @Override
    public void handleError(WebSocketSession session, Throwable error) throws IOException {
        if(session.isOpen()){
            session.close();
        }
        String clientUserid = session.getAttributes().get("userId").toString();
        ClientSessionMannager.removeAndCloseClientSession(clientUserid);
    }
}

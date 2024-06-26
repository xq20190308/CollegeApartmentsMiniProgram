package com.william.collegeapartmentsbacke.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.william.collegeapartmentsbacke.mapper.ClientMessageMapper;
import com.william.collegeapartmentsbacke.mapper.UserMapper;
import com.william.collegeapartmentsbacke.pojo.entity.ClientMessage;
import com.william.collegeapartmentsbacke.pojo.entity.ClientSessionBean;
import com.william.collegeapartmentsbacke.pojo.dto.MessageDTO;
import com.william.collegeapartmentsbacke.pojo.vo.ClientMessageVO;
import com.william.collegeapartmentsbacke.service.WebsocketService;
import com.william.collegeapartmentsbacke.websoket.ClientSessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class WebsocketServiceImpl implements WebsocketService {

    @Autowired
    private ClientMessageMapper clientMessageMapper;

    @Autowired
    private UserMapper userMapper;



    @Override
    public void handleOpen( String userId,WebSocketSession session) {

        ClientSessionBean clientSessionBean = new ClientSessionBean(session, ClientSessionManager.getClientIdMaker());
        String clientUserId = session.getAttributes().get("userId").toString();
        ClientSessionManager.addClientSessionBean(clientUserId, clientSessionBean);
        log.info(clientUserId+"建立了连接，现在总连接人数是"+ ClientSessionManager.CLIENT_POOL.size());
    }


    @Override
    public void handleClose(WebSocketSession session) {
        String clientUserId = session.getAttributes().get("userId").toString();
        ClientSessionManager.removeClientSessionBean(clientUserId);
        log.info(clientUserId+" 的连接断开,现在总连接人数是"+ ClientSessionManager.CLIENT_POOL.size());
    }

    //接收到消息
    @Override
    public void handleMessage(String userId, WebSocketSession session, TextMessage message) throws IOException {

        log.info("received a message：{}", message.getPayload());
        JSONObject obj = JSONObject.parseObject(message.getPayload());
        Integer type = Integer.valueOf(obj.get("type").toString());
        //说明是单发消息
        String data = obj.get("data").toString();
        String receiver = obj.get("receiver").toString();
        LocalDateTime sendTime = LocalDateTime.now();
        if(type == 0){
            ClientMessage clientMessage = new ClientMessage(null,userId,type,data,sendTime, receiver,true);
            sendMessage(clientMessage);
        }
        if(type == 1){
            List<String> userIds = userMapper.findUserByClassId(receiver);
            for(String userid : userIds){
                ClientMessage ClientMessage = new ClientMessage(null,userId,type,data,sendTime, userid,true);
                sendMessage(ClientMessage);
            }
        }

//        clientMessageMapper.insertClientMessage(clientMessage);
//        sendMessage(clientMessage);

    }

    @Override
    public void sendMessage(ClientMessage clientMessage) throws IOException {

        log.info("进入消息发送函数"+clientMessage.toString());
        ClientMessageVO messageVO = ClientMessageVO.builder()
                .type(clientMessage.getType())
                .senderUserId(clientMessage.getSenderUserId())
                .data(clientMessage.getData())
                .sendTime(clientMessage.getSendTime())
                .build();

        String receiverId = clientMessage.getReceiver();
        ClientSessionBean clientSessionBean =  ClientSessionManager.getClientSessionBean(receiverId);
        if(clientSessionBean == null){
            log.info("clientSessionbean为空，消息会被存到数据库");
            clientMessageMapper.insertClientMessage(clientMessage);
            return;
        }
        WebSocketSession session = clientSessionBean.getWebSocketSession();
        if(session == null || !session.isOpen()){
            log.info("session为空，消息会被存到数据库");
            clientMessageMapper.insertClientMessage(clientMessage);
            return;
        }
        log.info("私聊给{}发送了："+messageVO.toString(),receiverId);
        String messageJsonStr = JSONObject.toJSONString(messageVO);
        log.info("json转换后：" + messageJsonStr);
        clientSessionBean.getWebSocketSession().sendMessage(new TextMessage(messageJsonStr));
//        List<String> receiversUserids = clientMessage.getReceiversStrList();
//        for(String receiverId: receiversUserids){
//            ClientSessionBean clientSessionBean =  ClientSessionManager.getClientSessionBean(receiverId);
//            if(clientSessionBean == null){
//                continue;
//            }
//            WebSocketSession session = clientSessionBean.getWebSocketSession();
//            if(session == null || !session.isOpen()){
//                continue;
//            }
//            log.info("私聊给{}发送了："+messageVO.toString(),receiverId);
//            String messageJsonStr = JSONObject.toJSONString(messageVO);
//            log.info("json转换后：" + messageJsonStr);
//            clientSessionBean.getWebSocketSession().sendMessage(new TextMessage(messageJsonStr));

    }

    @Override
    public void broadCast(String message) throws IOException {
        for (ClientSessionBean clientSessionBean : ClientSessionManager.CLIENT_POOL.values()){
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

    @Override
    public void handleError(WebSocketSession session, Throwable error) throws IOException {
        if(session.isOpen()){
            session.close();
        }
        String clientUserid = session.getAttributes().get("userId").toString();
        ClientSessionManager.removeAndCloseClientSession(clientUserid);
    }

    /**
     * @param userId
     * @return
     */
    @Override
    @Transactional
    public List<MessageDTO> getHistoryMessageListByUserId(String userId) {
        List<ClientMessage> clientMessages = clientMessageMapper.getClientMessageListByReceiver(userId);

        List<MessageDTO> messageDTOList = new ArrayList<>();
        for(ClientMessage clientMessage : clientMessages){
            MessageDTO messageDTO = new MessageDTO(clientMessage.getType(),clientMessage.getData(),clientMessage.getSenderUserId(),clientMessage.getSendTime());
            messageDTOList.add(messageDTO);
            //设置为已读，测试用，可以直接删除
            clientMessageMapper.updateMessageStatusByMessageId(clientMessage.getMessageId(),0);
        }
        return messageDTOList;
    }


}

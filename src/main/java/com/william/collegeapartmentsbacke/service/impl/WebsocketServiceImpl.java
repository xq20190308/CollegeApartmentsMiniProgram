package com.william.collegeapartmentsbacke.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.william.collegeapartmentsbacke.mapper.ClientMessageMapper;
import com.william.collegeapartmentsbacke.mapper.UserMapper;
import com.william.collegeapartmentsbacke.mapper.SchoolInfoMapper;
import com.william.collegeapartmentsbacke.pojo.dto.schoolInfo.SchoolInfo;
import com.william.collegeapartmentsbacke.pojo.entity.websocket.ClientMessage;
import com.william.collegeapartmentsbacke.pojo.entity.websocket.ClientSessionBean;
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

    @Autowired
    private SchoolInfoMapper schoolInfoMapper;



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
        LocalDateTime sendTime = LocalDateTime.now();
        //按单人发送
        if(type == 0){
            String singleUser = obj.get("receiver").toString();
            log.info("消息type为 0 给{}发的。",singleUser);
            ClientMessage clientMessage = new ClientMessage(null,userId,type,data,sendTime, singleUser,true);
            sendMessage(clientMessage);
        }
        //自由群发 校区-年级-学院-专业-班级
        else if(type == 1){
            JSONObject schoolInfoObj = obj.getJSONObject("receiver");
            Integer campusId = Integer.parseInt(schoolInfoObj.get("campusId").toString());
            Integer gradeId = Integer.parseInt(schoolInfoObj.get("gradeId").toString());
            Integer collegeId = Integer.parseInt(schoolInfoObj.get("collegeId").toString());
            Integer majorId = Integer.parseInt(schoolInfoObj.get("majorId").toString());
            Integer classId = Integer.parseInt(schoolInfoObj.get("classId").toString());
            SchoolInfo schoolInfo = new SchoolInfo(campusId,gradeId,collegeId,majorId,classId);
            log.info("schoolInfo: {}", schoolInfo);
            List<String> userIds = schoolInfoMapper.selectUserIdBySchoolInfo(schoolInfo);
            for(String userid : userIds){
                ClientMessage ClientMessage = new ClientMessage(null,userId,type,data,sendTime, userid,true);
                sendMessage(ClientMessage);

            }
        }
        else if(type == 2){
            JSONObject dormitoryInfo = JSONObject.parseObject(obj.get("receiver").toString());
            String dormitoryName=dormitoryInfo.get("dormitoryName").toString();
            String campusId=dormitoryInfo.get("campusId").toString();
            List<String> userIds = userMapper.findUsersByDormitory(dormitoryName);
            userIds.retainAll(userMapper.findUsersBycampusId(campusId));
            log.info("消息type为2，群发了给{}，消息内容：{}",userIds.toString(),data);
            for(String userid : userIds){
                ClientMessage ClientMessage = new ClientMessage(null,userId,type,data,sendTime, userid,true);
                sendMessage(ClientMessage);
            }
        }
//        else if(type == 3){
//            String classId = obj.get("receiver").toString();
//            List<String> userIds = userMapper.findUserByClassId(classId);
//            log.info("消息type为1，群发了给{}，消息内容：{}",userIds.toString(),data);
//            for(String userid : userIds){
//                ClientMessage ClientMessage = new ClientMessage(null,userId,type,data,sendTime, userid,true);
//                sendMessage(ClientMessage);
//            }
//        }
        //按宿舍发送
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
        log.info("send函数中给{}发送了："+messageVO.toString(),receiverId);
        String messageJsonStr = JSONObject.toJSONString(messageVO);
        log.info("json转换后：" + messageJsonStr);
        clientSessionBean.getWebSocketSession().sendMessage(new TextMessage(messageJsonStr));

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

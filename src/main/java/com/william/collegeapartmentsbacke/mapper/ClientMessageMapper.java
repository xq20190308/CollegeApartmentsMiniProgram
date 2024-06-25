package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.entity.ClientMessage;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/20 21:19
 * @Version: 1.0
 */
@Mapper
public interface ClientMessageMapper {
    @Insert("INSERT into coap.client_message (sender_user_id, send_time, data, type,receiver,status) " +
            "VALUE (#{senderUserId},#{sendTime},#{data},#{type},#{receiver},#{status})")
    void insertClientMessage(ClientMessage clientMessage);

    @Delete("delete from coap.client_message where message_id = #{messageId} and status = #{status}")
    void deleteMessageByMessageId(Integer messageId,Integer status);

    @Update("update coap.client_message set status = #{status}")
    void updateMessageStatusByMessageId(Integer messageId,Integer status);



    @Select("select * from coap.client_message where message_id = #{id}")
    ClientMessage getClientMessageById(Integer id);

    //根据receiver的id来查有没有消息
    @Select("select * from coap.client_message where receiver = #{receiver} and status = true")
    List<ClientMessage> getClientMessageListByReceiver(String receiver);



}

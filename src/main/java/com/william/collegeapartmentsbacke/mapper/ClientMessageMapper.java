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
    @Insert("INSERT into coap.client_message (sender_user_id, send_time, data, type,receivers) " +
            "VALUE (#{senderUserId},#{sendTime},#{data},#{type})")
    void insertClientMessage(ClientMessage clientMessage);

    @Delete("delete from coap.client_message where type = 1 and receivers like concat('%',#{receiver},'%')")
    void deleteSingleClientMessage(Integer id, String receiver);



    @Select("select * from coap.client_message where id = #{id}")
    ClientMessage getClientMessageById(Integer id);

    //根据receiver的id来查有没有消息
    @Select("select * from coap.client_message where receivers like concat('%',#{receiver},'%')")
    List<ClientMessage> getClientMessageListByReceiver(String receiver);



}

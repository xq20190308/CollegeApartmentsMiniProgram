package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.entity.ClientMessage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/20 21:19
 * @Version: 1.0
 */
@Mapper
public interface ClientMessageMapper {
    @Insert("INSERT into coap.client_message (id, sender_user_id, send_time, data, type) " +
            "VALUE (#{id},#{senderUserId},#{sendTime},#{data},#{type})")
    void insertClientMessage(ClientMessage clientMessage);

    @Delete("delete from coap.client_message where id = #{id}")
    void deleteClientMessage(Integer id);

    @Select("select * from coap.client_message where id = ")
    void getClientMessageById(Integer id);



}

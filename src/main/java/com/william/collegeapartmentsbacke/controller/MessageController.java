package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.pojo.entity.AjaxResult;
import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.dto.MessageDTO;
import com.william.collegeapartmentsbacke.pojo.vo.ClientMessageVO;
import com.william.collegeapartmentsbacke.service.UserService;
import com.william.collegeapartmentsbacke.service.WebsocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/23 16:47
 * @Version: 1.0
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private UserService userService;

    @Autowired
    private WebsocketService websocketService;

    @RequestMapping(value = "/history",method = RequestMethod.GET)
    AjaxResult getHistoryMessage(@RequestHeader("Authorization") String token){
        String userId = userService.getUseridFromToken(token);
        List<MessageDTO> messageDTOList = websocketService.getHistoryMessageListByUserId(userId);
        List<ClientMessageVO> clientMessageVOS = new ArrayList<>();
        for(MessageDTO messageDTO : messageDTOList){
            ClientMessageVO messageVO = ClientMessageVO.builder()
                    .data(messageDTO.getData())
                    .type(messageDTO.getType())
                    .sendTime(messageDTO.getSendTime())
                    .senderUserId(messageDTO.getSenderUserId())
                    .build();
            clientMessageVOS.add(messageVO);
        }

        return AjaxResult.success(clientMessageVOS);
    }

}

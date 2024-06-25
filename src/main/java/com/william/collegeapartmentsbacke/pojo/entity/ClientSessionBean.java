package com.william.collegeapartmentsbacke.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

@AllArgsConstructor
@Data
public class ClientSessionBean {
    private WebSocketSession webSocketSession;
    private Integer clientId;
}

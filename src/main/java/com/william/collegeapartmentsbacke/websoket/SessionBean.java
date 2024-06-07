package com.william.collegeapartmentsbacke.websoket;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

@AllArgsConstructor
@Data
public class SessionBean {
    private WebSocketSession webSocketSession;
    private Integer clientId;
}

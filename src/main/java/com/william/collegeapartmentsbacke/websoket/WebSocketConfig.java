package com.william.collegeapartmentsbacke.websoket;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Resource
    WebSocketHandler webSocketHandler;
    @Resource
    WebSocketInterceptor webSocketInterceptor;

    /**
     * @param registry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/websocket1").addInterceptors(webSocketInterceptor).setAllowedOrigins("*");
    }
}


package com.william.collegeapartmentsbacke.Config;

import com.william.collegeapartmentsbacke.websoket.WebSocketHandler;
import com.william.collegeapartmentsbacke.websoket.WebSocketInterceptor;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
@Slf4j
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
        log.info("Registered WebSocket Handler,WebSocket开始服务");
    }
}


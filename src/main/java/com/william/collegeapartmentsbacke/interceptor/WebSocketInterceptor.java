package com.william.collegeapartmentsbacke.interceptor;

import com.william.collegeapartmentsbacke.common.annotations.NoNeedLogin;
import com.william.collegeapartmentsbacke.common.constant.JwtClaimsConstant;
import com.william.collegeapartmentsbacke.common.properties.JwtProperties;
import com.william.collegeapartmentsbacke.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

@Component
@Slf4j
public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {

    @Autowired
    private JwtProperties jwtProperties;


    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        log.info(request.getRemoteAddress().toString()+"websocket开始握手");
        if(request instanceof ServletServerHttpRequest) {

            HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
            String token = req.getHeader("Authorization");

            try {
                log.info("websocket jwt校验:{}", token);
                Claims claims = JwtUtil.parseJWT(jwtProperties.getSecretKey(), token);
                Long userId = Long.valueOf(claims.get(JwtClaimsConstant.USERID).toString());
                log.info("websocket 当前用户userid：{}", userId);

                //3、通过，放行
                return true;
            } catch (Exception ex) {
                if (ex instanceof ExpiredJwtException) {
                    log.info("websocket 检测到令牌过期异常：{}", ex.getMessage());
                    //令牌过期，返回403状态码
                    response.setStatusCode(HttpStatusCode.valueOf(403));
                    return false;
                } else {
                    log.info("websocket 检测到令牌异常：{}", ex.getMessage());
                    //4、不通过，响应401状态码
                    response.setStatusCode(HttpStatusCode.valueOf(401));
                    return false;
                }
            }
        }
        return false;
    }



    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        log.info(request.getRemoteAddress().toString()+"websocket完成握手");
        super.afterHandshake(request, response, wsHandler, ex);
    }
}

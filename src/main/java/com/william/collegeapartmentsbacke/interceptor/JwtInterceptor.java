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
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * jwt令牌校验的拦截器
 */
@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            log.info("访问的路径不在方法上");
            return true;
        }

        //不需要登录
        boolean isNoNeedLogin= ((HandlerMethod) handler).getMethodAnnotation(NoNeedLogin.class)!=null;
        if(isNoNeedLogin){
            log.info("无需登录");
            return true;
        }

        //1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getTokenName());


        //2、校验令牌
        try {
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getSecretKey(), token);
            Long userId = Long.valueOf(claims.get(JwtClaimsConstant.USERID).toString());
            log.info("当前用户userid：{}", userId);

            //3、通过，放行
            return true;
        } catch (Exception ex) {
            if (ex instanceof ExpiredJwtException) {
                log.info("检测到令牌过期异常：{}",ex.getMessage());
                //令牌过期，返回403状态码
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write("{\"code\": 0, \"message\": \"令牌过期，请重新登录\", \"data\": null}");
                return false;
            }
            else{
                log.info("检测到令牌异常：{}",ex.getMessage());
                //4、不通过，响应401状态码
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write("{\"code\": 0, \"message\": \"令牌无效，拒绝访问\", \"data\": null}");
            }

            return false;
        }
    }
}
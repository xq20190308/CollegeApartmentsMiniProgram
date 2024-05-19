package com.william.collegeapartmentsbacke.common.utils;

import cn.hutool.jwt.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    /**
     * 生成JWT
     * @param secretKey Jwt密钥
     * @param ttlMillis Jwt过期时间
     * @param claims    设置的信息
     * @return
     */
    public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long expMillis = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(expMillis);

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .signWith(signatureAlgorithm, secretKey.getBytes(StandardCharsets.UTF_8))
                .setExpiration(exp);

        return builder.compact();
    }


    /**
     * 解析JWT
     * @param secretKey Jwt密钥
     * @param token     JWT
     * @return
     */
    public static Claims parseJWT(String secretKey, String token) {
        Claims claims = (Claims) Jwts.parser()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJwt(token)
                .getBody();
        return claims;
    }
}


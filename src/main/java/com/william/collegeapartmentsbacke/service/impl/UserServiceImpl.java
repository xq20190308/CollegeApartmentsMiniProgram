package com.william.collegeapartmentsbacke.service.impl;
import io.jsonwebtoken.Claims;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.william.collegeapartmentsbacke.common.properties.JwtProperties;
import com.william.collegeapartmentsbacke.common.utils.HttpClientUtil;
import com.william.collegeapartmentsbacke.common.utils.JwtUtil;
import com.william.collegeapartmentsbacke.mapper.UserMapper;
import com.william.collegeapartmentsbacke.pojo.dto.UserLoginDTO;
import com.william.collegeapartmentsbacke.pojo.entity.Permission;
import com.william.collegeapartmentsbacke.pojo.entity.User;
import com.william.collegeapartmentsbacke.service.UserService;
import com.william.collegeapartmentsbacke.common.properties.WeChatProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WeChatProperties weChatProperties;

    @Autowired
    private JwtProperties jwtProperties;


    @Override
    public User findByOpenid(String openid) {
        return userMapper.getByOpenid(openid);
    }

    @Override
    public User findByUsername(String username) {
        User user = new User();
        try{
            user = userMapper.getUserByUserName(username);
        }
        catch (Exception e){
            e.printStackTrace();

        }

        return user;

    }

    @Override
    public String verifyByPwd(String username, String password) {
        String truePwd = userMapper.findPwdByUsername(username);
        log.info(username);
        if(truePwd == null) {
            return "登录失败，查无此用户,请注册或联系管理员";
        }

        if (truePwd.equals(password)) {
            return "true";
        }
        return "登陆失败,账号或密码错误";
    }

    @Override
    public void rigisterUser(UserLoginDTO userLoginDTO) {
        return;
    }

    @Override
    public String getUseridFromToken(String token) {
//        try {
//            String userid = JwtUtil.parseJWT(jwtProperties.getSecretKey(), token).getClaim("userid").toString();
//            if (userid != null) {
//                return userid;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Claims claims = JwtUtil.parseJWT(jwtProperties.getSecretKey(),token);
        log.info(claims.toString());
        String userid = JwtUtil.parseJWT(jwtProperties.getSecretKey(),token).get("userid").toString();
        return userid;
    }


    @Override
    public User wxLogin(UserLoginDTO userLoginDTO) {
        //获取前端code
        //根据code向微信服务端获取openid
        log.info("serLoginDTO.getCode()：{}",userLoginDTO.getCode());
        String openid = getOpenidByCode(userLoginDTO.getCode());

        //获取openid失败
        if (openid == null) {
//            throw new RuntimeException("微信登录失败,无法获取openid");
            log.info("微信登录失败,无法获取openid");
            return null;
        }

        //根据微信唯一标识openid查询该微信是否注册过
        User user = userMapper.getByOpenid(openid);
        log.info("user:{}",JSON.toJSONString(user));
        //如果没有查询到该用户，说明该用户从未用微信登录过，直接当前微信绑定到该账号
        if (user == null) {
            log.info("登录用户名为{}",userLoginDTO.getUsername());
            log.info("插入的openid为{}",openid);
            userMapper.updateOpenid(userLoginDTO.getUsername(),openid);
//        //注册新用户
//            user = User.builder().name(userLoginDTO.getStudentid())
//                    .openid(openid)
//                    .build();
//            userMapper.insert(user);
//            log.info("已注册用户");
//        }
        }
        //如果该微信登录过,但是user

        user = userMapper.getByOpenid(openid);
        log.info("微信登录");
        return user;
    }





    private String getOpenidByCode(String code) {
        Map<String, String> map = new HashMap<String, String>() {{
            put("appid", weChatProperties.getAppid());
            put("secret", weChatProperties.getSecret());
            put("js_code", code);
            put("grant_type", "authorization_code");
        }};
        log.info(map.toString());
        String json = HttpClientUtil.doGet(WX_LOGIN, map);

        JSONObject jsonObject = JSON.parseObject(json);
        return jsonObject.getString("openid");
    }

    @Override
    public Permission getPermission(String openid) {
        try {
            Permission permissionByOpenid = userMapper.getPermissionByOpenid(openid);
            log.info("openid:{}", openid);
            log.info("permission：{}", permissionByOpenid.toString());
            return userMapper.getPermissionByOpenid(openid);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



}

package com.william.collegeapartmentsbacke.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.william.collegeapartmentsbacke.common.utils.HttpClientUtil;
import com.william.collegeapartmentsbacke.mapper.UserMapper;
import com.william.collegeapartmentsbacke.pojo.dto.UserLoginDTO;
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




    @Override
    public User findByOpenid(String openid) {
        return userMapper.getByOpenid(openid);
    }


    @Override
    public User wxLogin(UserLoginDTO userLoginDTO) {
        log.info(userLoginDTO.getCode());
        String openid = getOpenid(userLoginDTO.getCode());

        if (openid == null) {
            throw new RuntimeException("微信登录失败");
        }
        User user = userMapper.getByOpenid(openid);
        if (user == null) {
            if (!userLoginDTO.isVerify()) {
                return null;
            } else {



                user = User.builder().name(userLoginDTO.getName())
                        .openid(openid)
                        .build();
                userMapper.insert(user);

            }
        }
        log.info("微信登录");
        return user;
    }



    private String getOpenid(String code) {
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
}

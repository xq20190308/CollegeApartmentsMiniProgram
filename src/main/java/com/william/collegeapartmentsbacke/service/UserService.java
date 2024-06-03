package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.dto.UserLoginDTO;
import com.william.collegeapartmentsbacke.pojo.entity.Permission;
import com.william.collegeapartmentsbacke.pojo.entity.User;

public interface UserService {
    //登录并返回用户
    User wxLogin(UserLoginDTO userLoginDTO);

    String verifyByPwd(String username, String password);

    //查询用户
    User findByOpenid(String openid);

    User findByUserid(String username);




    //查询用户标识
    Permission getPermission(String openid);

    String getUseridFromToken(String token);

    void updateAvatar(String userid, String avatar);

    void rigisterUser(UserLoginDTO userLoginDTO);

}

package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.dto.UserLoginDTO;
import com.william.collegeapartmentsbacke.pojo.entity.Permission;
import com.william.collegeapartmentsbacke.pojo.entity.User;

public interface UserService {
    User wxLogin(UserLoginDTO userLoginDTO);

    User findByOpenid(String openid);

    String verifyByPwd(String username, String password);

    Permission getPermission(String openid);

    void rigisterUser(UserLoginDTO userLoginDTO);

}

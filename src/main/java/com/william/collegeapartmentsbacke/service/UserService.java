package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.dto.UserLoginDTO;
import com.william.collegeapartmentsbacke.pojo.entity.User;

public interface UserService {
    User wxLogin(UserLoginDTO userLoginDTO);

    User findByOpenid(String openid);
}

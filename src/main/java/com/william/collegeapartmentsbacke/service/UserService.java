package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.dto.ContactInfoDTO;
import com.william.collegeapartmentsbacke.pojo.dto.UserLoginDTO;
import com.william.collegeapartmentsbacke.pojo.entity.Permission;
import com.william.collegeapartmentsbacke.pojo.entity.User;
import com.william.collegeapartmentsbacke.pojo.vo.ContactInfoVO;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.List;

public interface UserService {
    //登录并返回用户
    User wxLogin(UserLoginDTO userLoginDTO);

    String verifyByPwd(String username, String password);

    //查询用户
    User findByOpenid(String openid);

    User findByUserid(String username);

    User findByUsername(String username);

    List<ContactInfoVO> findByUserLevel(String userLevel) throws BadHanyuPinyinOutputFormatCombination;




    //查询用户标识
    Permission getPermissionByUserid(String openid);

    String getUseridFromToken(String token);

    void updateAvatar(String userid, String avatar);

    void rigisterUser(UserLoginDTO userLoginDTO);


    //管理用户
    void updatePasswordByUserid(String userid, String password);

    void updateLevelByUserid(String userid, String level);

    void initOpenidByUserid(String userid);

}

package com.william.collegeapartmentsbacke.pojo.vo;

import com.william.collegeapartmentsbacke.pojo.entity.userInfo.Permission;
import com.william.collegeapartmentsbacke.pojo.entity.userInfo.StuClassInfoDTO;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UserLoginVO implements Serializable {
    private Integer id;
    private String openid;
    private String username;
    private String trueName;
    private String userid;
    private String phone;
    private Permission userPermission;
    private String token;
    private String avatarUrl;
    private String dormitory;
    private StuClassInfoDTO classInfo;
    private String email;
    private String userLevel;
    //加一个level 加ba

}

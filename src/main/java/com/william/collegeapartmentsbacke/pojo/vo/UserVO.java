package com.william.collegeapartmentsbacke.pojo.vo;

import com.william.collegeapartmentsbacke.pojo.entity.userInfo.Permission;
import com.william.collegeapartmentsbacke.pojo.entity.userInfo.StuClassInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO implements Serializable {
    private Integer id;
    private String openid;
    private String username;
    private String password;
    private String trueName;
    private String userid;
    private String phone;
    private String avatarUrl;
    private String userLevel;
    private String dormitory;
    private Permission userPermission;
    private StuClassInfoDTO classInfo;
    private String email;
}

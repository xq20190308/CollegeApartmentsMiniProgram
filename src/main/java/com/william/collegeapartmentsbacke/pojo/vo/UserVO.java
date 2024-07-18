package com.william.collegeapartmentsbacke.pojo.vo;

import com.william.collegeapartmentsbacke.pojo.entity.Permission;
import com.william.collegeapartmentsbacke.pojo.entity.StuClassInfoDTO;
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
    private String name;
    private String userid;
    private String phone;
    private String avatar;
    private String userLevel;
    private String dormitory;
    private Permission userPermission;
    private StuClassInfoDTO classInfo;
    private String email;
}

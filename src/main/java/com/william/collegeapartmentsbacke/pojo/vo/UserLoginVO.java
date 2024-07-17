package com.william.collegeapartmentsbacke.pojo.vo;

import com.william.collegeapartmentsbacke.pojo.entity.Permission;
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

}

package com.william.collegeapartmentsbacke.pojo.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UserLoginVO implements Serializable {
    private Integer id;
    private String openid;
    private String token;
    private Integer nid;
}

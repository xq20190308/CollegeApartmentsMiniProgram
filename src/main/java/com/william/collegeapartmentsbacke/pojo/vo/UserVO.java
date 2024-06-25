package com.william.collegeapartmentsbacke.pojo.vo;

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
    private String id;
    private String username;
    private String phone;
    private String student_id;
    private byte[] avatar;
    private String isauthentic;
    private String islogin;
}

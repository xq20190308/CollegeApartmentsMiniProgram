package com.william.collegeapartmentsbacke.pojo.dto;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;


@Data
public class UserLoginDTO implements Serializable {
    private String code;
    private String username;
    private String password;
}

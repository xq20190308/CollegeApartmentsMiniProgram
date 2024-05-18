package com.william.collegeapartmentsbacke.pojo.dto;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String code;
    private String name;
    private boolean verify;
}

package com.william.collegeapartmentsbacke.pojo.entity;

import lombok.Data;

@Data
public class PuUser {
    private String username;
    private String password;
    private Integer requestPage;
    private long activityId;
}

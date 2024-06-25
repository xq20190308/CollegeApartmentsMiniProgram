package com.william.collegeapartmentsbacke.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Uploadfile {
    private String id;
    private String userid;
    private String name;
    private String type;
    private String path;
    private byte[] data;
}

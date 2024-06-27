package com.william.collegeapartmentsbacke.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Itemdata {
    private int id;
    private String stuid;
    private String category;
    private String name;
    private String pickLocation;
    private String pickTime;
    private String describes;
    private String contactobject;
    private String filepath;
    private Integer status;
    private Integer solve;
}

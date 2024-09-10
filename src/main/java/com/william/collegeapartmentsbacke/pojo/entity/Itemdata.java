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
    private String pick_location;
    private String pick_time;
    private String describes;
    private String contact_object;
    private String file_path;
    private Integer status;
    private Integer solve;
    private String content;
}

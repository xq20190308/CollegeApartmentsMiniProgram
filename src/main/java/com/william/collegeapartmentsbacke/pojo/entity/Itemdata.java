package com.william.collegeapartmentsbacke.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Itemdata {
    private String category;
    private String pickLocation;
    private String pickTime;
    private String describes;
    private String contactobject;
    private String filepath;
}

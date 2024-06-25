package com.william.collegeapartmentsbacke.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Suggestion {
             private int id;
             private String stu_id;
             private String describes;
             private String category;
             private String contactobject;
             private LocalDateTime pushtime ;
             private String  path;
             private int status;
}

package com.william.collegeapartmentsbacke.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class Suggestion {
             private Integer id;
             private String stu_id;
             private String describes;
             private String category;
             private String contactobject;
             private LocalDateTime pushtime ;
             private String  Urlpath;
}

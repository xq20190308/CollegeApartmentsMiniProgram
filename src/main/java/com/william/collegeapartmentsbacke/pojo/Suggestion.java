package com.william.collegeapartmentsbacke.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Suggestion {
             private Integer id;
             private String describes;
             private String category;
             private String pushtime ;
             private String contactobject;
}

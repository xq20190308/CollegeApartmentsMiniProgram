package com.william.collegeapartmentsbacke.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Suggestion {
             private Integer id;
             private String describes;
             private String category;
             private String contactobject;
             private LocalDateTime pushtime ;
}

package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class Suggestion {
             private Integer id;
             private String describes;
             private String category;
             private String pushtime ;
             private String contactobject;
}

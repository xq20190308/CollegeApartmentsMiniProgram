package com.william.collegeapartmentsbacke.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
    private Integer id;
    private String title;
    private String content;
    private String type;
    private LocalDateTime creatTime;
}

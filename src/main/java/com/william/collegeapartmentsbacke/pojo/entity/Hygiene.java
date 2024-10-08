package com.william.collegeapartmentsbacke.pojo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Hygiene {
    public Hygiene(){}
    private Integer id;
    private String weeks;
    @ExcelProperty("宿舍号")
    private String dormitory_id;
    @ExcelProperty("成绩")
    private String rank;
}

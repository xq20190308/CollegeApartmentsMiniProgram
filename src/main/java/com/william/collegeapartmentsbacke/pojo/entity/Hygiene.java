package com.william.collegeapartmentsbacke.pojo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Hygiene {
    public Hygiene(){}
    @ExcelProperty("宿舍号")
    private String Dormitoryid;
    @ExcelProperty("成绩")
    private String Rank;
}

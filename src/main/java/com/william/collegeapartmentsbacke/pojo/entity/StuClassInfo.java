package com.william.collegeapartmentsbacke.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/28 16:29
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StuClassInfo {
    private Integer classId;
    private String className;
    private String campusName;
    private String grade;
    private String major;
    private String collegeName;
}

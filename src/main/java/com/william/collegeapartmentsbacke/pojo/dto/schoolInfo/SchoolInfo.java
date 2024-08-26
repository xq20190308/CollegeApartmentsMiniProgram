package com.william.collegeapartmentsbacke.pojo.dto.schoolInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/29 22:20
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
public class SchoolInfo {
    private Integer campusId;
    private Integer gradeId;
    private Integer collegeId;
    private Integer majorId;
    private Integer classId;
}

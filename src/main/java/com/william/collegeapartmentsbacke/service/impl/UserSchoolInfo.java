package com.william.collegeapartmentsbacke.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/7/17 23:18
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSchoolInfo {
    private String userId;
    private Integer campusId;
    private Integer gradeId;
    private Integer collegeId;
    private Integer majorId;
    private Integer classId;
}

package com.william.collegeapartmentsbacke.pojo.vo.basicInfo;

import lombok.Data;

import java.util.List;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/29 21:03
 * @Version: 1.0
 */
@Data
public class GradeInfoVO {
    private Integer gradeId;
    private String gradeName;
    private List<CollegeInfoVO> colleges;
}

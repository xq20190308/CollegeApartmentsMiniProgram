package com.william.collegeapartmentsbacke.pojo.vo.basicInfo;

import lombok.Data;

import java.util.List;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/29 21:09
 * @Version: 1.0
 */
@Data
public class CampusInfoVO {
    private Integer campusId;
    private String campusName;
    private List<GradeInfoVO> grades;
}

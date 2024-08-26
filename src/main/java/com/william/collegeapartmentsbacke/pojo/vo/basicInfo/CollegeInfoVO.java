package com.william.collegeapartmentsbacke.pojo.vo.basicInfo;

import lombok.Data;

import java.util.List;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/29 21:02
 * @Version: 1.0
 */
@Data
public class CollegeInfoVO {
    private Integer collegeId;
    private String collegeName;
    private List<MajorInfoVO> majors;
}

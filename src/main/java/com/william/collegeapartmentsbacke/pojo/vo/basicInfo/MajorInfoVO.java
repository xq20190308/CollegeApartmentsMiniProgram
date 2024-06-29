package com.william.collegeapartmentsbacke.pojo.vo.basicInfo;

import lombok.Data;

import java.util.List;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/29 20:36
 * @Version: 1.0
 */
@Data
public class MajorInfoVO {
    private Integer majorId;
    private String majorName;
    List<ClassInfoVO> classes;
}

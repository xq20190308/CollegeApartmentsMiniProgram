package com.william.collegeapartmentsbacke.pojo.vo.basicInfo;

import lombok.Data;

import java.util.List;
@Data
public class CampusInfoIncludeDormitoryVO {
    private Integer campusId;
    private String campusName;
    private List<BuildingInfoVO> buildings;
}

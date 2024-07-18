package com.william.collegeapartmentsbacke.pojo.entity.basicInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuildingInfo {
    private Integer buildingId;
    private String buildingName;
    private Integer floorNum;
    private Integer dormitoryNum;
    private Integer campusId;
}

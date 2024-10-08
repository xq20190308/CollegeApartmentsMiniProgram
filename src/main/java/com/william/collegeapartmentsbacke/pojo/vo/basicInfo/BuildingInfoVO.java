package com.william.collegeapartmentsbacke.pojo.vo.basicInfo;

import lombok.Data;

import java.util.List;

@Data
public class BuildingInfoVO {
    private Integer BuildingId;
    private String BuildingName;
    private List<FloorInfoVO> floors;
}

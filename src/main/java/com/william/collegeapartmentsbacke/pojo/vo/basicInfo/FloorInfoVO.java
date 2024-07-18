package com.william.collegeapartmentsbacke.pojo.vo.basicInfo;

import lombok.Data;

import java.util.List;

@Data
public class FloorInfoVO {
    private int floorId;
    private String floorName;
    private List<DormitoryInfoVO> dormitorys;
}

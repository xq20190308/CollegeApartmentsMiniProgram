package com.william.collegeapartmentsbacke.pojo.vo.basicInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalDormitoryInfoVO {
    private List<CampusInfoIncludeDormitoryVO> campusInfoVOList;
}

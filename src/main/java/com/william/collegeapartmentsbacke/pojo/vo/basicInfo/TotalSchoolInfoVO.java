package com.william.collegeapartmentsbacke.pojo.vo.basicInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/29 21:11
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalSchoolInfoVO {
    private List<CampusInfoVO> campusInfoVOList;
}

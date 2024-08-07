package com.william.collegeapartmentsbacke.pojo.entity.basicInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/29 19:57
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
public class GradeInfo implements Comparable<GradeInfo> {
    private Integer gradeId;
    private String gradeName;
    private Integer campusId;


    /**
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(GradeInfo o) {
        return this.campusId.compareTo(o.campusId);
    }
}

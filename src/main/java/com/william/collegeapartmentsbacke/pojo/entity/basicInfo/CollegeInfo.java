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
public class CollegeInfo implements Comparable<CollegeInfo>{
    private Integer collegeId;
    private String collegeName;
    private Integer gradeId;

    /**
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(CollegeInfo o) {
        return this.gradeId.compareTo(o.getGradeId());
    }
}

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
public class MajorInfo implements Comparable<MajorInfo>{
    private Integer majorId;

    private String majorName;
    private Integer collegeId;

    /**
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(MajorInfo o) {
        return this.collegeId.compareTo(o.collegeId);
    }
}

package com.william.collegeapartmentsbacke.pojo.entity.userInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/28 16:29
 * @Version: 1.0
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StuClassInfoDTO implements Serializable {
    private String className;
    private String campusName;
    private String gradeName;
    private String majorName;
    private String collegeName;
}

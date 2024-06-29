package com.william.collegeapartmentsbacke.pojo.entity.basicInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/29 19:57
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
public class CampusInfo {
    private Integer campusId;
    private String campusName;
}

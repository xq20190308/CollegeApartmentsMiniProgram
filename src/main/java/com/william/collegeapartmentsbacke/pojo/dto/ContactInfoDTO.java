package com.william.collegeapartmentsbacke.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/20 20:14
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfoDTO {
    private String tureName;
    private String userId;
    private String phoneNumber;
    private String nameInitialLetter;
}

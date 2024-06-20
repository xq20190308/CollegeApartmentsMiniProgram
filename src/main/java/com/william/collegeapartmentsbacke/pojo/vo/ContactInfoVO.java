package com.william.collegeapartmentsbacke.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/20 19:54
 * @Version: 1.0
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfoVO implements Serializable {
    private String tureName;
    private String userId;
    private String phoneNumber;
    private Character nameInitialLetter;
}

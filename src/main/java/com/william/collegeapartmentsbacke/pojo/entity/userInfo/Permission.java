package com.william.collegeapartmentsbacke.pojo.entity.userInfo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("coap.user_permission")
public class Permission {
    private String userLevel;
    private Boolean accountManage;
    private Boolean noticeManage;
    private Boolean questionnaireManage;
    private Boolean feedbackManage;
}

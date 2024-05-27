package com.william.collegeapartmentsbacke.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@TableName("coap.user_permission")
public class Permission {
    private String userLevel;
    private Boolean accountManage;
    private Boolean noticeManage;
    private Boolean questionnaireManage;;
}

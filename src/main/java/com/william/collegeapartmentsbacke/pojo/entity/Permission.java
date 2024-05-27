package com.william.collegeapartmentsbacke.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@TableName("coap.user_permission")
public class Permission {
    private String userLevel;
    private String account_manage;
    private String notice_manage;
    private String questionnair_manage;;
}

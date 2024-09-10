package com.william.collegeapartmentsbacke.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("coap.menu")
public class Menu implements Serializable {
    private Long id;
    private String name;
    private String imgPath;
    private String pagePath;
    private String typeId;
}

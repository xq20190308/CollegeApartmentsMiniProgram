package com.william.collegeapartmentsbacke.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ruoyi.sys_dict_data")
public class DictItem {
    @TableId
    @TableField("dict_code")
    private Long dictCode;
    @TableField("dict_label")
    private String dictLabel;
    @TableField("dict_value")
    private String dictValue;
    @TableField("list_class")
    private String listClass;
}

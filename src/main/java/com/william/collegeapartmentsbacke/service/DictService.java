package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.entity.DictItem;

import java.util.List;


public interface DictService {
    public List<DictItem> searchDicts(String type);

    //获取某一字典值
    String getDictItem(String type, String dictValue);
}

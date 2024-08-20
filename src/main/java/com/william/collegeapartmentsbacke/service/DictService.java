package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.entity.DictItem;

import java.util.List;


public interface DictService {
    public List<DictItem> searchDicts(String type);
}

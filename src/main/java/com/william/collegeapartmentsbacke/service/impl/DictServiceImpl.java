package com.william.collegeapartmentsbacke.service.impl;

import com.william.collegeapartmentsbacke.mapper.DictMapper;
import com.william.collegeapartmentsbacke.pojo.entity.DictItem;
import com.william.collegeapartmentsbacke.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;

@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictMapper dictMapper;

    @Override
    public List<DictItem> searchDicts(String type) {
        QueryWrapper<DictItem> queryWrapper = new QueryWrapper<DictItem>();
        queryWrapper.eq("dict_type",type);
        queryWrapper.orderByAsc("dict_sort");
        queryWrapper.eq("status",0);
        return dictMapper.selectList(queryWrapper);
    }

    @Override
    //获取某一字典值
    public String getDictItem(String type, String dictValue) {
        QueryWrapper<DictItem> queryWrapper = new QueryWrapper<DictItem>();
        queryWrapper.eq("dict_type",type);
        queryWrapper.eq("dict_value",dictValue);
        queryWrapper.eq("status",0);
        return dictMapper.selectOne(queryWrapper).getDictLabel();
    }
}

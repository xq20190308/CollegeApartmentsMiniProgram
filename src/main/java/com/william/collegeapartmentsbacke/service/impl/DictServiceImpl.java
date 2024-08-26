package com.william.collegeapartmentsbacke.service.impl;

import com.william.collegeapartmentsbacke.mapper.DictMapper;
import com.william.collegeapartmentsbacke.pojo.entity.DictItem;
import com.william.collegeapartmentsbacke.service.DictService;
import lombok.AllArgsConstructor;
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
        queryWrapper.orderByAsc("dict_value");
        return dictMapper.selectList(queryWrapper);
    }
}

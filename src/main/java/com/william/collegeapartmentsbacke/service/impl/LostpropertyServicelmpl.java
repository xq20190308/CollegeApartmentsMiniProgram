package com.william.collegeapartmentsbacke.service.impl;

import com.william.collegeapartmentsbacke.mapper.LostpropertyMapper;
import com.william.collegeapartmentsbacke.pojo.entity.Itemdata;
import com.william.collegeapartmentsbacke.service.LostpropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LostpropertyServicelmpl implements LostpropertyService {
    @Autowired
    private LostpropertyMapper lostpropertyMapper;

    @Override
    public void saveSubmit(Itemdata itemdata) {
        lostpropertyMapper.saveSubmit(itemdata);
    }

    @Override
    public List<Itemdata> getItemdata() {
        return lostpropertyMapper.selectAll();
    }

}

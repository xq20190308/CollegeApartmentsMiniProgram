package com.william.collegeapartmentsbacke.service.impl;

import com.william.collegeapartmentsbacke.mapper.LostpropertyMapper;
import com.william.collegeapartmentsbacke.pojo.entity.Itemdata;
import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.service.LostpropertyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class LostpropertyServicelmpl implements LostpropertyService {
    @Autowired
    private LostpropertyMapper lostpropertyMapper;

    @Override
    public void saveSubmit(Itemdata itemdata) {
        itemdata.setStatus(0);
        itemdata.setSolve(0);
        lostpropertyMapper.saveSubmit(itemdata);
    }

    @Override
    public List<Itemdata> getItemdata(String category) {
        if(category.length()>=5)
        {
            return lostpropertyMapper.selectUserAll(category);
        }
        else
            return lostpropertyMapper.selectAll(category);
    }

    @Override
    public List<Itemdata> getUserItemdata(String stuid) {
        return lostpropertyMapper.selectUserAll(stuid);
    }

    @Override
    public void updateSolve(Integer id, Integer solve) {
        lostpropertyMapper.updateSolve(id,solve);
    }

    @Override
    public void updateItemdata(Itemdata itemdata) {

        lostpropertyMapper.updateAll(itemdata);
    }
    @Override
    public void deleteItemdata(Integer id)
    {
         lostpropertyMapper.deleteData(id);
    }

}

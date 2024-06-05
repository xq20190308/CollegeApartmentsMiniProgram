package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.entity.Itemdata;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LostpropertyService {
    void saveSubmit(Itemdata itemdata);
    List<Itemdata> getItemdata();

}

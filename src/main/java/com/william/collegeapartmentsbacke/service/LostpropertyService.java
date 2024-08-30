package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.entity.Itemdata;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LostpropertyService {
    void saveSubmit(Itemdata itemdata);

    List<Itemdata> getItemdata(String category);

    void updateItemdata(Itemdata itemdata);

    void deleteItemdata(Integer id);

    List<Itemdata> getUserItemdata(String id);

    void updateSolve(Integer id, Integer solve);
}

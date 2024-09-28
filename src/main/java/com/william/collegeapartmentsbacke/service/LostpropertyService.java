package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.dto.PageDTO;
import com.william.collegeapartmentsbacke.pojo.entity.Itemdata;
import com.william.collegeapartmentsbacke.pojo.entity.PageResults;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LostpropertyService {
    void saveSubmit(Itemdata itemdata);

    PageResults<Itemdata> getItemData(String category, PageDTO pagePara);

    void updateItemdata(Itemdata itemdata);

    void deleteItemdata(Integer id);

    List<Itemdata> getUserItemdata(String id);

    void updateSolve(Integer id, Integer solve);
}

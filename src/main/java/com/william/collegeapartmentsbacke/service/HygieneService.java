package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.dto.HygieneDTO;
import com.william.collegeapartmentsbacke.pojo.entity.Hygiene;

import java.io.InputStream;
import java.util.List;

public interface HygieneService {
    List<Hygiene> SaveRank(InputStream file);
    void upData(List<Hygiene> hygieneList,String weeks);
    String getDynamicUpdateSql(Hygiene hygiene, String dromaticSql);
    String selectHygieneByDormitoryid(String id);
    HygieneDTO SelectRank(String id);
}

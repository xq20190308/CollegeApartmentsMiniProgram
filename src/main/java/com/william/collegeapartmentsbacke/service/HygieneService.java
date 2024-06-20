package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.dto.HygieneDTO;
import com.william.collegeapartmentsbacke.pojo.entity.Hygiene;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

public interface HygieneService {
    Set<Hygiene> SaveRank(InputStream file);
    void upData(Set<Hygiene> hygieneList,String weeks);
    String getDynamicUpdateSql(Hygiene hygiene, String dromaticSql);
    String selectHygieneByDormitoryid(String id);
    HygieneDTO SelectRank(String id);
}

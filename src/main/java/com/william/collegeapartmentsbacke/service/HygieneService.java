package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.entity.Hygiene;

import java.io.InputStream;
import java.util.List;

public interface HygieneService {
    List<Hygiene> SaveRank(InputStream file);
    void upData(List<Hygiene> hygieneList);
    String SelectRank(String rank);
}

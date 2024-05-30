package com.william.collegeapartmentsbacke.service.impl;

import com.alibaba.excel.EasyExcel;
import com.william.collegeapartmentsbacke.mapper.HygieneMapper;
import com.william.collegeapartmentsbacke.pojo.entity.Hygiene;
import com.william.collegeapartmentsbacke.service.HygieneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class HygieneServicelmpl implements HygieneService {
    @Autowired
    HygieneMapper hygieneMapper;
    @Override
    public List<Hygiene> SaveRank(InputStream file)
    {
        List <Hygiene> hygiene= EasyExcel.read(file).sheet().head(Hygiene.class).doReadSync();
        return hygiene;
    }

    @Override
    public void upData(List<Hygiene> hygieneList) {
        for (Hygiene hygiene : hygieneList) {
            hygieneMapper.insertHygiene(hygiene); // 插入单个Hygiene对象到数据库
        }
    }

    @Override
    public String SelectRank(String rank) {
        return hygieneMapper.selectHygieneByDormitoryid(rank);
    }
}

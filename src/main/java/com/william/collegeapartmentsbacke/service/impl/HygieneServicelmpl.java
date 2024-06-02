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
    public String getDynamicUpdateSql(Hygiene hygiene, String dromaticSql) {
        return "UPDATE coap.Hygiene SET " + dromaticSql+"Rank = #{hygiene.Rank}" + " WHERE Dormitoryid = #{hygiene.Dormitoryid}";
    }

    @Override
    public String selectHygieneByDormitoryid(String week,String Dormitoryid) {
       return "select "+week+" from coap.Hygiene where "+"Dormitoryid = #{Dormitoryid}";
    }
    @Override
    public void upData(List<Hygiene> hygieneList,String weeks) {
        for (Hygiene hygiene : hygieneList) {
            hygieneMapper.insertHygiene(hygiene,weeks);
        }
    }

    @Override
    public String SelectRank(String week,String rank) {
        return hygieneMapper.selectHygieneByDormitoryid(week,rank);
    }
}

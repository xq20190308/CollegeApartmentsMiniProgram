package com.william.collegeapartmentsbacke.service.impl;

import com.alibaba.excel.EasyExcel;
import com.william.collegeapartmentsbacke.mapper.HygieneMapper;
import com.william.collegeapartmentsbacke.pojo.dto.HygieneDTO;
import com.william.collegeapartmentsbacke.pojo.entity.Hygiene;
import com.william.collegeapartmentsbacke.service.HygieneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class HygieneServicelmpl implements HygieneService {
    @Autowired
    HygieneMapper hygieneMapper;

    @Override
    public Set<Hygiene> SaveRank(InputStream file)
    {
        List <Hygiene> hygiene= EasyExcel.read(file).sheet().head(Hygiene.class).doReadSync();
        return new HashSet<>(hygiene);
    }

    @Override
    public String getDynamicUpdateSql(Hygiene hygiene, String dromaticSql) {
        return "UPDATE coap.hygiene_info SET " + dromaticSql+"_rank = #{hygiene.Rank}  WHERE dormitoryId = #{hygiene.dormitory_id}" ;
    }

    @Override
    public String selectHygieneByDormitoryid(String id) {
        return "select * from coap.hygiene_info where " + " dormitoryId = #{dormitoryId}  LIMIT 1";
    }

    @Override
    public void upData(Set<Hygiene> hygieneList,String weeks) {
        String []weekList=new String []{" ","first","second","third","fourth","fifth","sixth","seventh","eighth","ninth","tenth","eleventh","twelfth","thirteenth","fourteenth","fifteenth","sixteenth","seventh","eighteenth","nineteenth"};
        String week=weekList[Integer.parseInt(weeks)];
        for (Hygiene hygiene : hygieneList) {
            hygieneMapper.insertHygiene(hygiene,week);
        }
    }

    @Override
    public HygieneDTO SelectRank(String id) {
        return hygieneMapper.selectHygieneByDormitoryid(id);
    }

}

package com.william.collegeapartmentsbacke.service.impl;

import com.william.collegeapartmentsbacke.mapper.QuestionnaireMapper;
import com.william.collegeapartmentsbacke.pojo.entity.Questionnaire;
import com.william.collegeapartmentsbacke.pojo.vo.QuestionnaireVO;
import com.william.collegeapartmentsbacke.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Autowired
    QuestionnaireMapper questionnaireMapper;

    @Override
    public List<Questionnaire> selectAll() {
        return questionnaireMapper.selectAll();
    }

    @Override
    public List<QuestionnaireVO> countById(List<String> idList) {
        return questionnaireMapper.countById(idList);
    }

    @Override
    public void add(Questionnaire questionnaire) {
        questionnaireMapper.add(questionnaire);
    }

    @Override
    public void deleteById(String id) {
        questionnaireMapper.deleteById(id);
    }

    @Override
    public Questionnaire selectById(String id) {
        return questionnaireMapper.selectById(id);
    }

}

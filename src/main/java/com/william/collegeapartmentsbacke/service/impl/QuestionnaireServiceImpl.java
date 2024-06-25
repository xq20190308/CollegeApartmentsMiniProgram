package com.william.collegeapartmentsbacke.service.impl;

import com.william.collegeapartmentsbacke.mapper.QuestionnaireMapper;
import com.william.collegeapartmentsbacke.pojo.entity.Questionnaire;
import com.william.collegeapartmentsbacke.pojo.vo.QuestionnaireVO;
import com.william.collegeapartmentsbacke.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    public void deleteById(Integer id) {
        questionnaireMapper.deleteById(id);
    }

    @Transactional
    @Override
    public Integer simpleAdd() {
        questionnaireMapper.simpleadd();
        Integer naireId = questionnaireMapper.getNewestId();
        return naireId;
    }

    @Override
    public void totallyadd(Questionnaire questionnaire) {
        questionnaireMapper.updateQuestionnaire(questionnaire);
    }

}

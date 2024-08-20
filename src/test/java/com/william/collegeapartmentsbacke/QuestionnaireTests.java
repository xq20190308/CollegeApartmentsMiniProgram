package com.william.collegeapartmentsbacke;

import com.william.collegeapartmentsbacke.mapper.QuestionnaireMapper;
import com.william.collegeapartmentsbacke.pojo.entity.Questionnaire;
import com.william.collegeapartmentsbacke.service.impl.QuestionnaireServiceImpl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
class QuestionnaireTests {

    private static final Logger log = LoggerFactory.getLogger(QuestionnaireTests.class);
    @Autowired
    private QuestionnaireServiceImpl questionnaireService;

    //注入mapper接口的Bean
    @MockBean
    private QuestionnaireMapper questionnaireMapper;

    @Test
    public void contextLoads() {
        questionnaireService.selectAll();
    }

    @Test
    public void contextLoads1() {
        List<Questionnaire> questionnaires = questionnaireService.selectAll();
        log.info(questionnaires.toString());
    }

}

package com.william.collegeapartmentsbacke;

import com.william.collegeapartmentsbacke.mapper.QuestionnaireMapper;
import com.william.collegeapartmentsbacke.service.impl.QuestionnaireServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class QuestionnaireTests {

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
        questionnaireService.selectAll();
    }

}

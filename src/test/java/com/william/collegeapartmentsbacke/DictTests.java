package com.william.collegeapartmentsbacke;

import com.william.collegeapartmentsbacke.controller.DictController;
import com.william.collegeapartmentsbacke.mapper.DictMapper;
import com.william.collegeapartmentsbacke.mapper.QuestionnaireMapper;
import com.william.collegeapartmentsbacke.pojo.entity.AjaxResult;
import com.william.collegeapartmentsbacke.pojo.entity.DictItem;
import com.william.collegeapartmentsbacke.service.DictService;
import com.william.collegeapartmentsbacke.service.impl.QuestionnaireServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@SpringBootTest
public class DictTests {
    private static final Logger log = LoggerFactory.getLogger(QuestionnaireTests.class);

    private DictController dictController;

    @Autowired
    private DictService dictService;

    //注入mapper接口的Bean
    @MockBean
    private DictMapper dictMapper;

    @Test
    public void testselect() {
        List<DictItem> result = dictService.searchDicts(null);
        log.info("result:{}", result);
    }
}

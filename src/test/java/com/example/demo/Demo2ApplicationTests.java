package com.example.demo;


import com.william.collegeapartmentsbacke.mapper.QuestionnaireMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class Demo2ApplicationTests {
	@Test
	void contextLoads() {
	}

	@Autowired
	private QuestionnaireMapper questionnaireMapper;
	@Test
	void mappertest() {

	}

}

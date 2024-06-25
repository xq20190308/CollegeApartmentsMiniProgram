package com.william.collegeapartmentsbacke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CollegeApartmentsBackEApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollegeApartmentsBackEApplication.class, args);
    }

}

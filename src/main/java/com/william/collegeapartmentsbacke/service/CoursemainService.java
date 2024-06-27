package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.service.impl.CoursemainServicelmpl;

public interface CoursemainService {
     boolean initialization();
     void setCurtime(String zc,String curtime);
     void setAccount(String account,String password);
     CoursemainServicelmpl getCurrentTime();
     CoursemainServicelmpl getTable();
     CoursemainServicelmpl setWeek(String week);
     CoursemainServicelmpl getGrade();
     CoursemainServicelmpl setTerm(String term);
     CoursemainServicelmpl getClassroom(String idleTime);
     CoursemainServicelmpl getExamInfo();
     String exec();
    }

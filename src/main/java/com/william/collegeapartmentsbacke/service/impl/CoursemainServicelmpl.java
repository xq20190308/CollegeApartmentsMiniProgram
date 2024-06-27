package com.william.collegeapartmentsbacke.service.impl;
import com.william.collegeapartmentsbacke.service.CoursehttpService;
import com.william.collegeapartmentsbacke.service.CoursemainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class CoursemainServicelmpl implements CoursemainService {
        @Autowired
        private CoursehttpService coursehttpService;
        /**
         * 强智教务系统
         */
        ////////////////////////////////////////////////////////
        public String account = "202211070621";
        public String password = "wyc.1024";
        public String url = "https://jwgl.sdust.edu.cn/app.do";
        public String curWeek = "15";
        public String curTerm = "2023-2024-2";
        ////////////////////////////////////////////////////////

        public Map<String, String> params = new HashMap<>();
        public Map<String, String> headers = new HashMap<>();

        @Override
        public boolean initialization() {
            this.params.put("method", "authUser");
            this.params.put("xh", this.account);
            this.params.put("pwd", this.password);
            String reqResult = coursehttpService.httpRequest(this.url, this.params, "GET", this.headers);
            System.out.println(reqResult);
            String[] reqResultArr  = reqResult.split(",");
            if(reqResultArr[0].charAt(9) == '0') {
                System.out.println("登录失败");
                return false;
//                System.exit(0);
            }else {
                this.headers.put("token", reqResultArr[2].substring(9, reqResultArr[2].length()-1));
                return true;
            }
        }
        @Override
        public void setCurtime(String zc,String curtime){
            this.curWeek=zc;
            this.curTerm=curtime;
        }
        @Override
        public void setAccount(String account,String password){
            this.account=account;
            this.password=password;
        }

    //        @Override
//        public CoursemainServicelmpl getStudentInfo() {
//            this.params.put("method", "getUserInfo");
//            this.params.put("xh", this.account);
//            return this;
//        }
        @Override
        public CoursemainServicelmpl getCurrentTime() {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            this.params.put("method", "getCurrentTime");
            this.params.put("currDate", df.format(new Date()));
            return this;
        }
        @Override
        public CoursemainServicelmpl getTable() {
            this.params.put("method", "getKbcxAzc");
            this.params.put("xh", this.account);
            this.params.put("xnxqid", this.curTerm);
            this.params.put("zc", this.curWeek);
            return this;
        }
        @Override
        public CoursemainServicelmpl setWeek(String week) {
            this.params.put("zc", week);
            return this;
        }
        @Override
        public CoursemainServicelmpl getGrade() {
            this.params.put("method", "getCjcx");
            this.params.put("xh", this.account);
            this.params.put("xnxqid", "");
            return this;
        }
        @Override
        public CoursemainServicelmpl setTerm(String term) {
            this.params.put("xnxqid", term);
            return this;
        }
        @Override
        public CoursemainServicelmpl getClassroom(String idleTime) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            this.params.put("method", "getKxJscx");
            this.params.put("time", df.format(new Date()));
            this.params.put("idleTime", idleTime);
            return this;
        }
        @Override
        public CoursemainServicelmpl getExamInfo() {
            this.params.put("method", "getKscx");
            this.params.put("xh", this.account);
            return this;
        }
        @Override
        public String exec(){
            String result = coursehttpService.httpRequest(this.url, this.params, "GET", this.headers);
            this.params.clear();
            System.out.println(result);
            return result;
        }

    }


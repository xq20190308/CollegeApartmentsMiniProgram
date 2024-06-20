package com.william.collegeapartmentsbacke.service;


import java.io.BufferedReader;

import java.io.InputStream;

import java.net.HttpURLConnection;

import java.util.Map;


public interface CoursehttpService {
      String httpRequest(String url, Map<String, String> param, String method, Map<String, String> headers);
      String pramHandle(Map<String, String> params);
      String doGet(String httpurl, Map<String, String> headers);
      String doPost(String httpUrl, String param, Map<String, String> headers);
      String getResult(HttpURLConnection connection);
      void closeConn(BufferedReader br, InputStream is,HttpURLConnection connection);

    }

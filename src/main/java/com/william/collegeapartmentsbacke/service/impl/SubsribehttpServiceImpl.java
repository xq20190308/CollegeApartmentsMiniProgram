package com.william.collegeapartmentsbacke.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.william.collegeapartmentsbacke.service.SubsribehttpService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Service
public class SubsribehttpServiceImpl implements SubsribehttpService {
    private static final String API_URL = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send";
    public String sendSubscribeMessage(String accessToken, String openid, String templateId, String page, String data) {
        try {
            String url = API_URL + "?access_token=" + accessToken;

            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setDoOutput(true);

            // 构建请求体
            String body = buildRequestBody(openid, templateId, page, data);
            byte[] requestBodyBytes = body.getBytes(StandardCharsets.UTF_8);

            // 发送请求
            connection.getOutputStream().write(requestBodyBytes);

            // 读取响应
            int responseCode = connection.getResponseCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            if (responseCode == HttpURLConnection.HTTP_OK&&JSONObject.parseObject(response.toString()).getString("errcode").equals("0")) {
                // 请求成功处理逻辑
                System.out.println("发送订阅消息成功");
                //System.out.println(response.toString());
                connection.disconnect();
                return response.toString();
            } else {
                // 请求失败处理逻辑
                System.out.println("发送订阅消息失败");
                System.out.println("响应码：" + responseCode);
                System.out.println(response.toString());
                connection.disconnect();
                return response.toString();
            }


        } catch (IOException e) {
            // 异常处理逻辑
            e.printStackTrace();
            return "catch error";
        }
    }

    private static String buildRequestBody(String openid, String templateId, String page, String data) {
        // 构建请求体的JSON字符串
        return String.format(
                "{\"touser\":\"%s\",\"template_id\":\"%s\",\"page\":\"%s\",\"data\":%s}",
                openid, templateId, page, data);
    }

    //推送消息的时候需要accessToken，这里是获取token的方法，在实际环境中，一般是定时刷新，两个小时内有效，获取accesstoken时需要appid和secret信息，一般是后台参数或常量进行配置。
    //获取accessToken的请求接口
    private final static String GetAccessToken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}";

    public String refreshAccessToken(String appid, String secret){
        if(StringUtils.isEmpty(appid) || StringUtils.isEmpty(secret)){
            log.error("刷新微信AccessToken时，缺少appid或secret参数！");
            //throw new WxBzException(SysErrorCode.SYS_ERROR_700000,"刷新微信AccessToken时，缺少appid或secret参数！");
        }
        Map<String, Object> param = new HashMap<>();
        param.put("appid",appid);
        param.put("secret",secret);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JSONObject> resp = restTemplate.getForEntity(GetAccessToken, JSONObject.class,param);
        JSONObject jsonObj = resp.getBody();
        String accessToken = null;
        if(jsonObj != null){
            accessToken = jsonObj.getString("access_token");
            return jsonObj.getString("access_token");
        }else{
            return accessToken;
        }

    }

    private final static String getPubTemplate="https://api.weixin.qq.com/wxaapi/newtmpl/getpubtemplatekeywords?access_token=";
    @Override
    public String getPubTemplateKeyWordsById(String accessToken, String templateId) {
        Map<String, Object> param = new HashMap<>();
        param.put("tid",templateId);
        RestTemplate restTemplate = new RestTemplate();
        log.info("templateId:"+templateId);
        ResponseEntity<JSONObject> resp = restTemplate.getForEntity(getPubTemplate+accessToken+"&tid="+templateId, JSONObject.class,param);
        JSONObject jsonObj = resp.getBody();
        log.info("jsonObj:"+jsonObj);
        String data = null;
        if(jsonObj != null){
            data = jsonObj.getString("data");
        }
        log.info("data:"+data);
        return data;
    }

}

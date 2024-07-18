package com.william.collegeapartmentsbacke.controller;

import com.alibaba.fastjson.JSONObject;
import com.william.collegeapartmentsbacke.common.properties.WeChatProperties;
import com.william.collegeapartmentsbacke.pojo.dto.SubscribeDTO;
import com.william.collegeapartmentsbacke.service.SubsribehttpService;
import com.william.collegeapartmentsbacke.service.impl.SubsribehttpServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.william.collegeapartmentsbacke.pojo.entity.Result;

import java.util.HashMap;
import java.util.Map;

//消息推送的模拟方法
@Slf4j
@RestController
@RequestMapping(value = "/subscribe")
public class SubscribeController {
        @Autowired
        private SubsribehttpService wxCommonService;
        @Autowired
        private WeChatProperties weChatProperties;
        @PostMapping
        public Result sendMsg(@RequestBody SubscribeDTO subscribeDTO){
            log.info("前端请求发订阅消息");

            //请求 微信接口 获取 accessToken
            String accessToken = wxCommonService.refreshAccessToken(weChatProperties.getAppid(),weChatProperties.getSecret());
            if(accessToken==null) return Result.error("获得access_token失败");
            //System.out.println("--accessToken: "+accessToken);
            //String accessToken = "82_d9pScTUP802Ngo-9agQ7EZpHMuH2mvovlN5nt9E3OERMUnwZi4N6sUJWID2owSy7t4LXxzq7ox2tBlbrxqc9OA_QgHOqUfZBP2QOnYRONHc5BnJGz4iwgPLeGFUEIKbAJAMQS";
            String openId = "ormTS5A6OPUrfzEUmUL2gSozu7y4";
            String templateId = "yTxSWrDTgHG44_PtbLQPNKHG2TrUlH2lPSQNyAGhwH4";
            String page = "pages/home/home";
            String data = wxCommonService.getPubTemplateKeyWordsById(accessToken,templateId);
            log.info("data:::"+data);
            // 构建订阅消息内容的JSON对象
            // 构建订阅消息内容的JSON对象
            JSONObject messageData = new JSONObject();
            messageData.put("thing5", createDataItem("导师姓名", "导师姓名"));
            messageData.put("thing4", createDataItem("备注", "备注"));
            messageData.put("name1", createDataItem("学生姓名", "学生姓名"));
            messageData.put("date3", createDataItem("记录时间", "2019-10-10 14:00:00"));
            log.info("messageData:{}",messageData);
            // 将订阅消息内容转换为JSON字符串
            String jsonData = messageData.toJSONString();
            log.info("jsonData:{}",jsonData);
            //log.info(accessToken);
            //String result = wxCommonService.sendSubscribeMessage(accessToken,subscribeDTO.getOpenid(), subscribeDTO.getTemplateId(), subscribeDTO.getPage(),jsonData);
            String result = wxCommonService.sendSubscribeMessage(accessToken,openId, templateId, page,jsonData);
            //System.out.println(JSONObject.parseObject(result).getString("errcode").equals("0"));
            if (JSONObject.parseObject(result).getString("errcode").equals("0")) return Result.error(result);
            return Result.success(JSONObject.parseObject(result));
        }
        private static Map<String, Object> createDataItem(String name, String value) {
            Map<String, Object> item = new HashMap<>();
            item.put("value", value);
            return item;
        }
}

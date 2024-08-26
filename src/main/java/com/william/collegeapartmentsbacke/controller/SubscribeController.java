package com.william.collegeapartmentsbacke.controller;

import com.alibaba.fastjson.JSONObject;
import com.william.collegeapartmentsbacke.common.properties.WeChatProperties;
import com.william.collegeapartmentsbacke.mapper.UserMapper;
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
import java.util.Objects;

//消息推送的模拟方法
@Slf4j
@RestController
@RequestMapping(value = "/subscribe")
public class SubscribeController {
        @Autowired
        private SubsribehttpService wxCommonService;
        @Autowired
        private WeChatProperties weChatProperties;
        @Autowired
        private UserMapper userMapper;

        @GetMapping(value = "/getTemplates")
        public Result getTemplates() {
            String accessToken = wxCommonService.refreshAccessToken(weChatProperties.getAppid(),weChatProperties.getSecret());
            if(accessToken==null) return Result.error("获得access_token失败");
            Object data = wxCommonService.getPubTemplates(accessToken);
            log.info(data.toString());
            return Result.success(data);
        }



        @PostMapping
        public Result sendMsg(@RequestBody SubscribeDTO subscribeDTO){
            log.info("前端请求发订阅消息");
            //请求 微信接口 获取 accessToken
            String accessToken = wxCommonService.refreshAccessToken(weChatProperties.getAppid(),weChatProperties.getSecret());
            if(accessToken==null) return Result.error("获得access_token失败");
            log.info("向{}发订阅消息{}：{}",subscribeDTO.getUserid(),subscribeDTO.getTemplateId(),subscribeDTO.getData());
            String openid=userMapper.findOpenidByUsername(subscribeDTO.getUserid());
            String result = wxCommonService.sendSubscribeMessage(accessToken,openid, subscribeDTO.getTemplateId(), subscribeDTO.getPage(),subscribeDTO.getData());
            if (!Objects.equals(JSONObject.parseObject(result).getString("errcode"), "0")) return Result.error(result);
            return Result.success(JSONObject.parseObject(result));
        }
        private static Map<String, Object> createDataItem(String name, String value) {
            Map<String, Object> item = new HashMap<>();
            item.put("value", value);
            return item;
        }
}

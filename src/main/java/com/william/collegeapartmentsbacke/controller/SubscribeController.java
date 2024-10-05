package com.william.collegeapartmentsbacke.controller;

import com.alibaba.fastjson.JSONObject;
import com.william.collegeapartmentsbacke.common.properties.WeChatProperties;
import com.william.collegeapartmentsbacke.mapper.UserMapper;
import com.william.collegeapartmentsbacke.pojo.dto.SubscribeDTO;
import com.william.collegeapartmentsbacke.pojo.entity.AjaxResult;
import com.william.collegeapartmentsbacke.service.SubsribehttpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        public AjaxResult getTemplates() {
            String accessToken = wxCommonService.refreshAccessToken(weChatProperties.getAppid(),weChatProperties.getSecret());
            if(accessToken==null) return AjaxResult.error("获得access_token失败");
            Object data = wxCommonService.getPubTemplates(accessToken);
            log.info(data.toString());
            return AjaxResult.success(data);
        }



        @PostMapping
        public AjaxResult sendMsg(@RequestBody SubscribeDTO subscribeDTO){
            log.info("前端请求发订阅消息");
            //请求 微信接口 获取 accessToken
            String accessToken = wxCommonService.refreshAccessToken(weChatProperties.getAppid(),weChatProperties.getSecret());
            if(accessToken==null) return AjaxResult.error("获得access_token失败");
            log.info("向{}发订阅{}消息{}：{}",subscribeDTO.getUserid(),subscribeDTO.getTemplateId(), subscribeDTO.getPage(),subscribeDTO.getData());
            String openid=userMapper.findOpenidByUsername(subscribeDTO.getUserid());
            String result = wxCommonService.sendSubscribeMessage(accessToken,openid, subscribeDTO.getTemplateId(), subscribeDTO.getPage(),subscribeDTO.getData());
            if (!Objects.equals(JSONObject.parseObject(result).getString("errcode"), "0")) return AjaxResult.error(result);
            return AjaxResult.success(JSONObject.parseObject(result));
        }
        private static Map<String, Object> createDataItem(String name, String value) {
            Map<String, Object> item = new HashMap<>();
            item.put("value", value);
            return item;
        }

}

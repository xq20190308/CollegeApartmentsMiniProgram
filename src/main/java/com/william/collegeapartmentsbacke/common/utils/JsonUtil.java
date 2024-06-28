package com.william.collegeapartmentsbacke.common.utils;

import ch.qos.logback.core.net.server.Client;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.william.collegeapartmentsbacke.pojo.entity.ClientMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class JsonUtil {

    /**
     * @Author William
     * @Date 2024/6/20 20:22
     * @Description 将JssonArray转换成List
     * @Param [jsonArray]
     * @Return java.util.List<java.lang.Object>
     * @Since version 1.0
     */
    public static List<Object> jsonArrayToList(JSONArray jsonArray) throws JSONException {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            Object value = jsonArray.get(i);
            if (value instanceof JSONArray) {
                // 递归处理嵌套的JSONArray

                list.add(jsonArrayToList((JSONArray) value));
            } else {
                // 添加基本类型的值
                list.add(value);
            }
        }
        return list;
    }

//    public static ClientMessage parseMessage(TextMessage message) throws ParseException {
//        ClientMessage clientMessage = new ClientMessage();
//        JSONObject obj = JSONObject.parseObject(message.getPayload());
//        Integer type = Integer.valueOf(obj.get("type").toString());
//        r
//    }

}

package com.william.collegeapartmentsbacke.common.utils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


@Slf4j
public class JsonUtil {
    public static void main123(String jsonString) {
        // 这是给定的JSON字符串
//        String jsonString = "[\"0\",\"0\",\"0\",\"1\",[\"0\",\"1\",\"2\",\"3\",\"4\"],[\"0\",\"4\"],[\"1\",\"0\"],[\"4\"],[\"3\",\"4\",\"1\"],[\"0\",\"3\",\"1\"]]";

        // 解析字符串为JSONArray
        JSONArray jsonArray;

        try {
            jsonArray = new JSONArray(jsonString);
        } catch (JSONException e) {

            System.err.println("Error parsing JSON: " + e.getMessage());
            return;
        }

        // 将JSONArray转换为List<Object>
        List<Object> list = jsonArrayToList(jsonArray);

        // 打印结果
        System.out.println(list);
    }

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
}

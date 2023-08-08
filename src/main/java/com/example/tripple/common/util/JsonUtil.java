package com.example.tripple.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class JsonUtil {

    private Gson gson;

    public JsonUtil(){
        this.gson = new GsonBuilder().disableHtmlEscaping().create();
    }

    /**
     * JSON String을 map으로 변환
     * @param str
     * @return
     */
    public Map<String, Object> jsonToMap(String str){
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        return gson.fromJson(str, type);
    }

    /**
     * map을 JSON String으로 변환
     * @param map
     * @return
     */
    public String mapToJson(Map<String, Object> map){
        return this.gson.toJson(map);
    }
}


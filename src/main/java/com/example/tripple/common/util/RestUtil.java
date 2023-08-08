package com.example.tripple.common.util;

import lombok.extern.log4j.Log4j2;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class RestUtil {


    public static Map<String, Object> postForm(String url, Map<String, Object> body){
        Map<String, Object> resultMap = new HashMap<>();
        JsonUtil jsonUtil = new JsonUtil();
        OkHttpClient client = new OkHttpClient();


        FormBody.Builder builder = new FormBody.Builder();
        body.forEach((key, value)->{
            builder.add(key,value.toString());
        });

        RequestBody reqBody = builder.build();
        Request request = new Request.Builder().url(url).post(reqBody).build();

        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()){
                ResponseBody resBody = response.body();
                if(resBody != null){
                    log.info("content Type :: "+resBody.contentType());
                    // content Type : text일 경우 gson jsonToMap 에러 발생
                    if(resBody.contentType().toString().contains("text/html")){
                        resultMap.put("data",resBody.string());
                    }else{
                        resultMap = jsonUtil.jsonToMap(resBody.string());
                        resultMap.forEach((k,v)->{
                            log.info(k+" : "+v.toString());
                        });
                    }

                }
            }else{
                log.error("Error Occurred");
                //log.error(response.body().string());
            }

            return resultMap;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }


    public static Map<String, Object> postJson(String url, Map<String, Object> body) throws Exception {
        return postJson(url, null, body);
    }

    public static Map<String, Object> postJson(String url, Map<String,String> header, Map<String, Object> body) throws Exception {

        System.out.println("postJson 실행");

        Map<String, Object> resultMap = new HashMap<>();
        JsonUtil jsonUtil = new JsonUtil();
        String paramJson = jsonUtil.mapToJson(body);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);

        Request.Builder requestBuilder = new Request.Builder();
        if (header != null) {
            for (Map.Entry<String, String> param : header.entrySet()) {
                requestBuilder.addHeader(param.getKey(), param.getValue().toString());
            }
        }
        Request request = requestBuilder
                .url(url)
                .post(requestBody)
                .build();


        log.info("REST CALL(POST) - URL:{}, PARAM:{}", url, paramJson);


        try (Response response = client.newCall(request).execute()){
            if (response.body() != null) {
                String responseStr = response.body().string();
                log.debug("response:{}", responseStr);
                resultMap = jsonUtil.jsonToMap(responseStr);
            }
        } catch (Exception e) {
            log.error("REST 통신 오류", e);
            throw new Exception("REST 통신 오류");
        }


        return resultMap;
    }

    public static Map<String, Object> getJson(String url, Map<String,String> header) throws Exception {

        System.out.println("getJson 실행");

        Map<String, Object> resultMap = new HashMap<>();
        JsonUtil jsonUtil = new JsonUtil();

        OkHttpClient client = new OkHttpClient();

        Request.Builder requestBuilder = new Request.Builder();
        if (header != null) {
            for (Map.Entry<String, String> param : header.entrySet()) {
                requestBuilder.addHeader(param.getKey(), param.getValue().toString());
            }
        }
        Request request = requestBuilder
                .url(url)
                .get()
                .build();


        log.info("REST CALL(GET) - URL:{}", url);


        try (Response response = client.newCall(request).execute()){
            if (response.body() != null) {
                String responseStr = response.body().string();
                log.debug("response:{}", responseStr);
                resultMap = jsonUtil.jsonToMap(responseStr);
                System.out.println("결과 정상");
                resultMap.forEach((s, o) -> {
                    System.out.println("s : "+s+"  , o : "+o);
                });
            }
        } catch (Exception e) {
            log.error("REST 통신 오류", e);
            throw new Exception("REST 통신 오류");
        }


        return resultMap;
    }
}

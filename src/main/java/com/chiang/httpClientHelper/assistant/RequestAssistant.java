package com.chiang.httpClientHelper.assistant;

import com.alibaba.fastjson.JSONObject;
import com.chiang.httpClientHelper.annotation.RequestInfo;
import com.chiang.httpClientHelper.spi.RequestMethod;
import okhttp3.Request;

import java.util.*;

public class RequestAssistant {
    public static Request buildRequest(RequestInfo requestInfo, String url, JSONObject jsonObject) throws InstantiationException, IllegalAccessException {
        ServiceLoader<RequestMethod> requestMethods = ServiceLoader.load(RequestMethod.class);
        Iterator<RequestMethod> iterator = requestMethods.iterator();
        RequestMethod requestMethod;
        List<RequestMethod> requestMethodList = new ArrayList<>();
        while (iterator.hasNext()) {
            RequestMethod next = iterator.next();
            if (next.match(requestInfo)) {
                next.setRequestInfo(requestInfo);
                requestMethodList.add(next);
            }
        }
        if (requestMethodList.isEmpty()){
            throw new IllegalArgumentException("请求方式不可为空");
        }
        Collections.sort(requestMethodList);
        requestMethod = requestMethodList.get(0);
        for (int i = 1; i < requestMethodList.size(); i++) {
            requestMethod = requestMethodList.get(i).wrap(requestMethod);
        }
        if (requestMethod == null){
            throw new IllegalArgumentException("请求方式不可为空");
        }
        return requestMethod.request(url, jsonObject);
    }
}

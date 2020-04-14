package com.chiang.httpClientHelper.assistant;

import com.chiang.httpClientHelper.annotation.Client;
import com.chiang.httpClientHelper.annotation.RequestInfo;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.chiang.httpClientHelper.assistant.ClassAssistant.checkStandard;
import static com.chiang.httpClientHelper.assistant.ClassAssistant.checkType;
import static com.chiang.httpClientHelper.assistant.MethodAssistant.checkMethod;
import static com.chiang.httpClientHelper.constant.Constants.CLIENT_INFO;
import static com.chiang.httpClientHelper.constant.Constants.REQUEST_INFOS;

public class MappingAssistant {

    public static <T> Map<String, Object> establishMapping(Class<T> interfaceType) throws Throwable {
        checkStandard(interfaceType);
        Client clientInfo = checkType(interfaceType);
        Map<Method, RequestInfo> requestInfos = checkMethod(interfaceType);
        Map<String, Object> result = new HashMap<>();
        result.put(CLIENT_INFO, clientInfo);
        result.put(REQUEST_INFOS, requestInfos);
        return result;
    }
}

package com.chiang.httpClientHelper.assistant;

import com.chiang.httpClientHelper.annotation.RequestInfo;
import com.chiang.httpClientHelper.data.DataConverter;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

public class ResultAssistant {

    public static Object buildResult(Method method, Map<Method, RequestInfo> requestInfos, Response response) throws IllegalAccessException, InstantiationException, IOException {
        RequestInfo requestInfo = requestInfos.get(method);
        Class<? extends DataConverter> converter = requestInfo.converter();
        DataConverter dataConverter = converter.newInstance();
        Object result = dataConverter.process(response);
        return result;
    }
}

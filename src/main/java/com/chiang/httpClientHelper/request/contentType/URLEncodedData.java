package com.chiang.httpClientHelper.request.contentType;

import com.alibaba.fastjson.JSONObject;
import com.chiang.httpClientHelper.annotation.RequestInfo;
import com.chiang.httpClientHelper.context.ThreadContext;
import com.chiang.httpClientHelper.request.AbstractRequestMethod;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

import static com.chiang.httpClientHelper.constant.Constants.APPLICATION_X_WWW_FORM_URLENCODED;
import static com.chiang.httpClientHelper.constant.Constants.PRE;

public class URLEncodedData extends AbstractRequestMethod {
    @Override
    public String method() {
        return null;
    }

    @Override
    public boolean match(RequestInfo requestInfo) {
        boolean equals = APPLICATION_X_WWW_FORM_URLENCODED.equals(requestInfo.contentType());
        return equals;
    }

    @Override
    public Request request(String url, JSONObject jsonObject) throws InstantiationException, IllegalAccessException {
        return super.request(url, jsonObject);
    }


    @Override
    public int compareTo(@NotNull Object o) {
        return PRE;
    }

    @Override
    public RequestBody buildRequestBody(MediaType mediaType, JSONObject jsonObject) {
        Set<String> keys = jsonObject.keySet();
        StringBuffer stringBuffer = new StringBuffer();
        boolean flag = true;
        for (String key : keys){
            Object value = jsonObject.get(key);
            if (flag){
                stringBuffer.append(key).append("=").append(value);
                flag = false;
                continue;
            }
            stringBuffer.append("&").append(key).append("=").append(value);
        }
        RequestBody body = RequestBody.create(String.valueOf(stringBuffer),mediaType);
        ThreadContext.setRequestBody(body);
        return body;
    }
}

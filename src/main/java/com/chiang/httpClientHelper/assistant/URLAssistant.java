package com.chiang.httpClientHelper.assistant;

import com.chiang.httpClientHelper.annotation.Client;
import com.chiang.httpClientHelper.annotation.RequestInfo;

public class URLAssistant {
    public static String buildURL(RequestInfo requestInfo, Client clientInfo) {
        String path = clientInfo.path();
        String uri = requestInfo.uri();
        StringBuffer stringBuffer = new StringBuffer(path);
        if (!path.endsWith("/") && !uri.startsWith("/")) {
            stringBuffer.append("/");
        }
        stringBuffer.append(uri);
        String url = stringBuffer.toString();
        return url;
    }
}

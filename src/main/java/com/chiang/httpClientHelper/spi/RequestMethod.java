package com.chiang.httpClientHelper.spi;

import com.alibaba.fastjson.JSONObject;
import com.chiang.httpClientHelper.annotation.RequestInfo;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public interface RequestMethod extends Comparable{

    void setRequestInfo(RequestInfo requestInfo);

    String method();

    boolean match(RequestInfo requestInfo);

    RequestMethod wrap(RequestMethod requestMethod);

    Request request(String url,JSONObject jsonObject) throws IllegalAccessException, InstantiationException;

    RequestBody buildRequestBody(MediaType mediaType, JSONObject jsonObject);
}

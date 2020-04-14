package com.chiang.httpClientHelper.request;

import com.alibaba.fastjson.JSONObject;
import com.chiang.httpClientHelper.annotation.RequestInfo;
import com.chiang.httpClientHelper.context.ThreadContext;
import com.chiang.httpClientHelper.header.HeaderProcessor;
import com.chiang.httpClientHelper.spi.RequestMethod;
import lombok.Data;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import static com.chiang.httpClientHelper.constant.Constants.CONTENT_TYPE;

@Data
public abstract class AbstractRequestMethod implements RequestMethod {

    protected RequestMethod requestMethod;

    protected RequestInfo requestInfo;


    @Override
    public RequestMethod wrap(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }

    @Override
    public Request request(String url, JSONObject jsonObject) throws IllegalAccessException, InstantiationException {
        Request request = ThreadContext.getRequest();
        MediaType mediaType = MediaType.parse(requestInfo.contentType());
        RequestBody body = buildRequestBody(mediaType, jsonObject);
        Class<? extends HeaderProcessor> headerProcessor = requestInfo.headerProcessor();
        HeaderProcessor instance = headerProcessor.newInstance();
        if (request == null) {
            Request.Builder builder = new Request.Builder();
            instance.processHeader(builder);
            request = builder.url(url)
                    .method(requestMethod.method(), body)
                    .addHeader(CONTENT_TYPE, requestInfo.contentType())
                    .build();
        } else {
            Request.Builder builder = request.newBuilder();
            instance.processHeader(builder);
            request = builder
                    .url(url)
                    .method(requestMethod.method(), body)
                    .addHeader(CONTENT_TYPE, requestInfo.contentType())
                    .build();
        }
        ThreadContext.setRequest(request);
        ThreadContext.setRequestBody(body);
        return request;
    }


    @Override
    public RequestBody buildRequestBody(MediaType mediaType, JSONObject jsonObject) {
        RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);
        ThreadContext.setRequestBody(body);
        return body;
    }
}

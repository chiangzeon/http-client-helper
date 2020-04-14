package com.chiang.httpClientHelper.request;

import com.alibaba.fastjson.JSONObject;
import com.chiang.httpClientHelper.annotation.RequestInfo;
import com.chiang.httpClientHelper.context.ThreadContext;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;

import static com.chiang.httpClientHelper.constant.Constants.LAST;
import static com.chiang.httpClientHelper.constant.Constants.POST;

public class PostMethod extends AbstractRequestMethod {

    @Override
    public String method() {
        return POST;
    }

    @Override
    public boolean match(RequestInfo requestInfo) {
        return method().equals(requestInfo.method());
    }

    @Override
    public Request request(String url, JSONObject jsonObject) {
        Request request = ThreadContext.getRequest();
        return request;
    }


    @Override
    public int compareTo(@NotNull Object o) {
        return LAST;
    }
}

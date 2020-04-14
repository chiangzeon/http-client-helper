package com.chiang.httpClientHelper.request;

import com.alibaba.fastjson.JSONObject;
import com.chiang.httpClientHelper.annotation.RequestInfo;
import com.chiang.httpClientHelper.context.ThreadContext;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

import static com.chiang.httpClientHelper.constant.Constants.GET;
import static com.chiang.httpClientHelper.constant.Constants.LAST;

@Data
@ToString
@EqualsAndHashCode
public class GetMethod extends AbstractRequestMethod {

    @Override
    public String method() {
        return GET;
    }

    @Override
    public boolean match(RequestInfo requestInfo) {
        return requestInfo.method().equals(GET);
    }

    @Override
    public Request request(String url, JSONObject body) {
        StringBuffer stringBuffer = buildURL(url, body);
        Request request = buildRequest(stringBuffer);
        return request;
    }

    @NotNull
    private Request buildRequest(StringBuffer stringBuffer) {
        Request request = ThreadContext.getRequest();
        RequestBody requestBody = ThreadContext.getRequestBody();
        if (request == null) {
            request = new Request.Builder()
                    .url(stringBuffer.toString())
                    .method(method(), requestBody)
                    .build();
        } else {
            request = request.newBuilder().url(String.valueOf(stringBuffer)).method(method(), null).build();
        }
        return request;
    }

    @NotNull
    private StringBuffer buildURL(String url, JSONObject body) {
        StringBuffer stringBuffer = new StringBuffer(url);
        Set<String> keys = body.keySet();
        boolean flag = true;
        for (String key : keys) {
            if (flag) {
                stringBuffer.append("?").append(key).append("=").
                        append(body.get(key));
                flag = false;
            } else {
                stringBuffer.append("&").append(key).append("=").
                        append(body.get(key));
            }
        }
        return stringBuffer;
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return LAST;
    }
}

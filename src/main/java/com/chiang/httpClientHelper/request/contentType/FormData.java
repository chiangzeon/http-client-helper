package com.chiang.httpClientHelper.request.contentType;

import com.alibaba.fastjson.JSONObject;
import com.chiang.httpClientHelper.annotation.RequestInfo;
import com.chiang.httpClientHelper.context.ThreadContext;
import com.chiang.httpClientHelper.request.AbstractRequestMethod;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Set;

import static com.chiang.httpClientHelper.constant.Constants.*;

public class FormData extends AbstractRequestMethod {
    @Override
    public String method() {
        return null;
    }

    @Override
    public boolean match(RequestInfo requestInfo) {
        boolean equals = MULTIPART_FORM_DATA.equals(requestInfo.contentType());
        return equals;
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return PRE;
    }

    @Override
    public RequestBody buildRequestBody(MediaType mediaType, JSONObject jsonObject) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        Set<String> keys = jsonObject.keySet();
        for (String key : keys) {
            Object value = jsonObject.get(key);
            if (value instanceof File) {
                File file = (File) value;
                RequestBody body = RequestBody.create(file, MediaType.parse(APPLICATION_OCTET_STREAM));
                builder.addFormDataPart(key, ((File) value).getAbsolutePath(), body);
            } else {
                builder.addFormDataPart(key, String.valueOf(value));
            }
        }
        MultipartBody multipartBody = builder.build();
        ThreadContext.setRequestBody(multipartBody);
        return multipartBody;
    }
}

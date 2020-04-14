package com.chiang.httpClientHelper.header;

import com.chiang.httpClientHelper.context.ThreadContext;
import okhttp3.Request;

import java.util.List;
import java.util.Map;

public class DefaultHeaderProcessor implements HeaderProcessor {
    @Override
    public void processHeader(Request.Builder builder) {
        List<Map<String, String>> header = ThreadContext.getHeader();
        if (header == null){
            return;
        }
        header.forEach(record -> {
            record.forEach((key, value) -> {
                builder.addHeader(key, value);
            });
        });
    }
}

package com.chiang.httpClientHelper.data.customize;

import com.chiang.httpClientHelper.data.DataConverter;
import okhttp3.Response;

import java.io.IOException;

public class StringConverter implements DataConverter<String> {
    @Override
    public String process(Response response) throws IOException {
        return response.body().string();
    }
}

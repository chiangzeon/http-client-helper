package com.chiang.httpClientHelper.data.customize;

import com.chiang.httpClientHelper.data.DataConverter;
import okhttp3.Response;
import okio.BufferedSource;

import java.io.IOException;

public class SourceConverter implements DataConverter<BufferedSource> {
    @Override
    public BufferedSource process(Response response) throws IOException {
        return response.body().source();
    }
}

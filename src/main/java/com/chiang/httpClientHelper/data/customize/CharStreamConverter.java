package com.chiang.httpClientHelper.data.customize;

import com.chiang.httpClientHelper.data.DataConverter;
import okhttp3.Response;

import java.io.IOException;
import java.io.Reader;

public class CharStreamConverter implements DataConverter<Reader> {
    @Override
    public Reader process(Response response) throws IOException {
        return response.body().charStream();
    }
}

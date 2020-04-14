package com.chiang.httpClientHelper.data.customize;

import com.chiang.httpClientHelper.data.DataConverter;
import okhttp3.Response;

import java.io.IOException;

public class ByteArrayConverter implements DataConverter<byte[]> {
    @Override
    public byte[] process(Response response) throws IOException {
        return response.body().bytes();
    }
}

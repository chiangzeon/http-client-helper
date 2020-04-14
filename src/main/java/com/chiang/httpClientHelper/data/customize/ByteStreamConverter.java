package com.chiang.httpClientHelper.data.customize;

import com.chiang.httpClientHelper.data.DataConverter;
import okhttp3.Response;

import java.io.IOException;
import java.io.InputStream;

public class ByteStreamConverter implements DataConverter<InputStream> {
    @Override
    public InputStream process(Response response) throws IOException {
        return response.body().byteStream();
    }
}

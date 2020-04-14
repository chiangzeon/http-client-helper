package com.chiang.httpClientHelper.data;

import okhttp3.Response;

import java.io.IOException;

public interface DataConverter<T> {

    T process(Response response) throws IOException;
}

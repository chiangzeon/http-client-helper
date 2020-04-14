package com.chiang.httpClientHelper.header;

import okhttp3.Request;

public interface HeaderProcessor {

    void processHeader(Request.Builder builder);
}

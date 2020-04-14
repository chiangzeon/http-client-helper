package com.chiang.httpClientHelper.context;

import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.List;
import java.util.Map;

public class ThreadContext {

    private static final ThreadLocal<Request> REQUEST_THREAD_LOCAL = new ThreadLocal<>();

    private static final ThreadLocal<RequestBody> REQUEST_BODY_THREAD_LOCAL = new ThreadLocal<>();

    private static final ThreadLocal<List<Map<String, String>>> HEADER_THREAD_LOCAL = new ThreadLocal<>();

    public static void setRequest(Request request) {
        REQUEST_THREAD_LOCAL.set(request);
    }

    public static Request getRequest() {
        return REQUEST_THREAD_LOCAL.get();
    }

    public static void removeRequest() {
        REQUEST_THREAD_LOCAL.remove();
    }

    public static void setRequestBody(RequestBody requestBody) {
        REQUEST_BODY_THREAD_LOCAL.set(requestBody);
    }

    public static RequestBody getRequestBody() {
        return REQUEST_BODY_THREAD_LOCAL.get();
    }

    public static void removeRequestBody() {
        REQUEST_BODY_THREAD_LOCAL.remove();
    }


    public static void setHeader(List<Map<String, String>> header) {
        HEADER_THREAD_LOCAL.set(header);
    }

    public static List<Map<String, String>> getHeader() {
        return HEADER_THREAD_LOCAL.get();
    }

    public static void removeHeader() {
        HEADER_THREAD_LOCAL.remove();
    }

}

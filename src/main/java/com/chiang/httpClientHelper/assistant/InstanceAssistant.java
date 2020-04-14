package com.chiang.httpClientHelper.assistant;

import com.chiang.httpClientHelper.proxy.HttpClientHelperProxy;
import okhttp3.OkHttpClient;

import java.lang.reflect.Proxy;
import java.util.Map;

import static com.chiang.httpClientHelper.assistant.MappingAssistant.establishMapping;

public class InstanceAssistant {

    public static <T> T newInstance(Class<T> interfaceType) throws Throwable {
        Map<String, Object> mappings = establishMapping(interfaceType);
        HttpClientHelperProxy httpClientHelperProxy = new HttpClientHelperProxy();
        httpClientHelperProxy.setInterfaceType(interfaceType);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        httpClientHelperProxy.setTarget(client);
        httpClientHelperProxy.setMappings(mappings);
        Object proxyInstance = Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class[]{interfaceType}, httpClientHelperProxy);
        return (T) proxyInstance;
    }
}

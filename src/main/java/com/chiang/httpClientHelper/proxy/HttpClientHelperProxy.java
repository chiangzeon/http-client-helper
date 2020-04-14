package com.chiang.httpClientHelper.proxy;

import com.alibaba.fastjson.JSONObject;
import com.chiang.httpClientHelper.annotation.Client;
import com.chiang.httpClientHelper.annotation.RequestInfo;
import com.chiang.httpClientHelper.context.ThreadContext;
import lombok.Data;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationHandler;
import java.util.Map;

import static com.chiang.httpClientHelper.assistant.ParameterAssistant.buildParameters;
import static com.chiang.httpClientHelper.assistant.ResultAssistant.buildResult;
import static com.chiang.httpClientHelper.assistant.URLAssistant.buildURL;
import static com.chiang.httpClientHelper.assistant.RequestAssistant.buildRequest;
import static com.chiang.httpClientHelper.constant.Constants.CLIENT_INFO;
import static com.chiang.httpClientHelper.constant.Constants.REQUEST_INFOS;


@Data
public class HttpClientHelperProxy implements InvocationHandler {

    private Class interfaceType;

    private OkHttpClient target;

    private Map<String, Object> mappings;

    public String toString(){
        return super.toString();
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            return execute(method, args);
        } catch (IllegalAccessException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (InvocationTargetException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } catch (InstantiationException e) {
            throw e;
        } finally {
            ThreadContext.removeRequest();
            ThreadContext.removeRequestBody();
            ThreadContext.removeHeader();
        }
    }

    private Object execute(Method method, Object[] args) throws IllegalAccessException, InvocationTargetException, IOException, InstantiationException {
        Map<Method, RequestInfo> requestInfos = (Map<Method, RequestInfo>) mappings.get(REQUEST_INFOS);
        if (!requestInfos.containsKey(method)) {
            String name = method.getName();
            if (name.equals("hashCode")){
                return hashCode();
            }else if (name.equals("toString")){
                return toString();
            }else if (name.equals("equals")){
                return equals(args[0]);
            }else{
                return method.invoke(interfaceType, args);
            }
        }
        RequestInfo requestInfo = requestInfos.get(method);
        Client clientInfo = (Client) mappings.get(CLIENT_INFO);
        String url = buildURL(requestInfo, clientInfo);
        JSONObject jsonObject = buildParameters(method, args);
        Request request = buildRequest(requestInfo, url, jsonObject);
        Response response = target.newCall(request).execute();
        Object result = buildResult(method, requestInfos, response);
        return result;
    }


}

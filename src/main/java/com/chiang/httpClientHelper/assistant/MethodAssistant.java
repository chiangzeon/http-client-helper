package com.chiang.httpClientHelper.assistant;

import com.chiang.httpClientHelper.annotation.Param;
import com.chiang.httpClientHelper.annotation.ParamBody;
import com.chiang.httpClientHelper.annotation.RequestInfo;
import com.chiang.httpClientHelper.data.customize.StringConverter;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodAssistant {

    public static <T> Map<Method, RequestInfo> checkMethod(Class<T> interfaceType) throws Exception {
        Method[] methods = interfaceType.getMethods();
        Map<Method, RequestInfo> map = new HashMap<>();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            boolean existsRequestInfo = method.isAnnotationPresent(RequestInfo.class);
            if (!existsRequestInfo) {
                continue;
            }
            RequestInfo requestInfo = method.getAnnotation(RequestInfo.class);
            validateMethod(method, requestInfo);
            map.put(method, requestInfo);
        }
        return map;
    }

    private static void validateMethod(Method method, RequestInfo requestInfo) throws Exception {
        validateUri(method, requestInfo);
        validateReturnType(method, requestInfo);
        validateParameterAnnotation(method);
    }

    private static void validateParameterAnnotation(Method method) {
        Parameter[] parameters = method.getParameters();
        List<ParamBody> paramBodies = findParamBody(parameters);
        List<Param> params = findParams(parameters);
        if (!params.isEmpty() && !paramBodies.isEmpty()) {
            String exceptionMsg = String.format("请为方法%s修改参数，%s不可与%s同时存在",
                    method, Param.class.getName(), ParamBody.class.getName());
            throw new IllegalArgumentException(exceptionMsg);
        }
        if (paramBodies.size() > 1) {
            String exceptionMsg = String.format("请为方法%s修改参数，%s修饰的参数只能有一个",
                    method, ParamBody.class.getName());
            throw new IllegalArgumentException(exceptionMsg);
        }
        if(paramBodies.isEmpty() && params.isEmpty() && parameters.length != 0){
            String exceptionMsg = String.format("请为方法%s添加注解否则无法正常传输入参", method);
            throw new IllegalArgumentException(exceptionMsg);
        }
    }

    @NotNull
    public static List<Param> findParams(Parameter[] parameters) {
        List<Param> params = new ArrayList<>();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            boolean hasParam = parameter.isAnnotationPresent(Param.class);
            if (hasParam) {
                params.add(parameter.getAnnotation(Param.class));
            }
        }
        return params;
    }

    @NotNull
    public static List<ParamBody> findParamBody(Parameter[] parameters) {
        List<ParamBody> paramBodies = new ArrayList<>();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            boolean hasParamBody = parameter.isAnnotationPresent(ParamBody.class);
            if (hasParamBody) {
                paramBodies.add(parameter.getAnnotation(ParamBody.class));
            }
        }
        return paramBodies;
    }

    private static void validateReturnType(Method method, RequestInfo requestInfo) {
        Class<?> returnType = method.getReturnType();
        boolean isString = String.class.isAssignableFrom(returnType);
        boolean isCustomize = requestInfo.customizeResultType();
        boolean isDefault = StringConverter.class.isAssignableFrom(requestInfo.converter());
        if (!isString) {//返回值不是String
            if (isCustomize) {//自定义开启
                if (isDefault) {
                    String exceptionMsg = String.format("返回值为%s的方法，请将converter属性值设置为对应类型", String.class.getName());
                    throw new IllegalArgumentException(exceptionMsg);
                }
            } else {
                String exceptionMsg = String.format("返回值为%s的方法，请将customizeResultType属性值设置为false", String.class.getName());
                throw new IllegalArgumentException(exceptionMsg);
            }
        }
    }

    private static void validateUri(Method method, RequestInfo requestInfo) throws Exception {
        String uri = requestInfo.uri();
        if (StringUtils.isEmpty(uri)) {
            String exceptionMsg = String.format("请为方法[%s]添加[uri]属性值", method.getName());
            throw new Exception(exceptionMsg);
        }
    }
}

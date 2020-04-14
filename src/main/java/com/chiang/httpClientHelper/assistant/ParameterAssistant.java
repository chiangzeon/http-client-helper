package com.chiang.httpClientHelper.assistant;

import com.alibaba.fastjson.JSONObject;
import com.chiang.httpClientHelper.annotation.Ignore;
import com.chiang.httpClientHelper.annotation.Param;
import com.chiang.httpClientHelper.annotation.ParamBody;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.chiang.httpClientHelper.assistant.MethodAssistant.findParamBody;
import static com.chiang.httpClientHelper.assistant.MethodAssistant.findParams;

public class ParameterAssistant {

    public static JSONObject buildParameters(Method method, Object[] args) throws IllegalAccessException {
        Parameter[] parameters = method.getParameters();
        List<Param> params = findParams(parameters);
        List<ParamBody> paramBody = findParamBody(parameters);
        if (!params.isEmpty()) {
            return buildParams(args, parameters);
        } else if (!paramBody.isEmpty()) {
            return buildParamBody(args);
        } else {
            return new JSONObject();
        }

    }

    private static JSONObject buildParamBody(Object[] args) throws IllegalAccessException {
        Object arg = args[0];
        JSONObject jsonObject = new JSONObject();
        List<Field> fields = findFields(arg);
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            field.setAccessible(true);
            boolean ignore = field.isAnnotationPresent(Ignore.class);
            boolean isFinal = Modifier.isFinal(field.getModifiers());
            boolean isStatic = Modifier.isStatic(field.getModifiers());
            if (ignore || isFinal || isStatic) {
                continue;
            }
            Object value = field.get(arg);
            jsonObject.put(field.getName(), value);
        }
        return jsonObject;
    }

    private static List<Field> findFields(Object arg) {
        List<Field> fields = new ArrayList<>();
        Class clazz = arg.getClass();
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        while (!clazz.equals(Object.class)) {
            clazz = clazz.getSuperclass();
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        }
        return fields;
    }

    @NotNull
    private static JSONObject buildParams(Object[] args, Parameter[] parameters) {
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Object arg = args[i];
            String name = parameter.getName();
            if (parameter.isAnnotationPresent(Param.class)) {
                Param param = parameter.getAnnotation(Param.class);
                if (param.required() && arg == null) {
                    throw new IllegalArgumentException("参数不可为空");
                }
                name = param.value();
            }
            jsonObject.put(name, arg);
        }
        return jsonObject;
    }
}

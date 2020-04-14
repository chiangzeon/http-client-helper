package com.chiang.httpClientHelper.assistant;

import com.chiang.httpClientHelper.annotation.Client;
import com.chiang.httpClientHelper.standard.HttpClientHelper;
import org.apache.commons.lang3.StringUtils;

public class ClassAssistant {

    public static <T> Client checkType(Class<T> interfaceType) throws Exception {
        boolean existsClientInfo = interfaceType.isAnnotationPresent(Client.class);
        if (!existsClientInfo) {
            String exceptionMsg = String.format("请为接口[%s]添加注解[%s]", interfaceType.getName(), Client.class.getName());
            throw new Exception(exceptionMsg);
        }
        Client clientInfo = interfaceType.getAnnotation(Client.class);
        String path = clientInfo.path();
        if (StringUtils.isEmpty(path)) {
            String exceptionMsg = String.format("请为注解[%s]添加[path]属性值", Client.class.getName());
            throw new Exception(exceptionMsg);
        }
        return clientInfo;
    }

    public static <T> void checkStandard(Class<T> interfaceType) {
        if (!HttpClientHelper.class.isAssignableFrom(interfaceType) || !interfaceType.isInterface()) {
            String exceptionMsg = String.format("接口[%s]不是接口[%s]的子类，请检查", interfaceType.getName(), HttpClientHelper.class.getName());
            throw new RuntimeException(exceptionMsg);
        }
    }
}

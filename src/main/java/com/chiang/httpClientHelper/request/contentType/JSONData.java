package com.chiang.httpClientHelper.request.contentType;

import com.chiang.httpClientHelper.annotation.RequestInfo;
import com.chiang.httpClientHelper.request.AbstractRequestMethod;
import org.jetbrains.annotations.NotNull;

import static com.chiang.httpClientHelper.constant.Constants.*;

public class JSONData extends AbstractRequestMethod {
    @Override
    public String method() {
        return null;
    }

    @Override
    public boolean match(RequestInfo requestInfo) {
        boolean equals = APPLICATION_JSON.equals(requestInfo.contentType());
        return equals;
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return PRE;
    }

}

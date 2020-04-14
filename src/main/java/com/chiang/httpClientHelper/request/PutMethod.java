package com.chiang.httpClientHelper.request;

import com.chiang.httpClientHelper.annotation.RequestInfo;
import org.jetbrains.annotations.NotNull;


import static com.chiang.httpClientHelper.constant.Constants.PRE;
import static com.chiang.httpClientHelper.constant.Constants.PUT;

public class PutMethod extends AbstractRequestMethod {
    @Override
    public String method() {
        return PUT;
    }

    @Override
    public boolean match(RequestInfo requestInfo) {
        boolean equals = PUT.equals(requestInfo.method());
        return equals;
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return PRE;
    }
}

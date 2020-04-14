package com.chiang.httpClientHelper.annotation;

import com.chiang.httpClientHelper.constant.Constants;
import com.chiang.httpClientHelper.data.DataConverter;
import com.chiang.httpClientHelper.data.customize.StringConverter;
import com.chiang.httpClientHelper.header.DefaultHeaderProcessor;
import com.chiang.httpClientHelper.header.HeaderProcessor;

import java.lang.annotation.*;

import static com.chiang.httpClientHelper.constant.Constants.GET;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RequestInfo {

    String method() default GET;

    String uri();

    String contentType() default "";

    boolean customizeResultType() default false;

    Class<? extends HeaderProcessor> headerProcessor() default DefaultHeaderProcessor.class;

    Class<? extends DataConverter> converter() default StringConverter.class;
}

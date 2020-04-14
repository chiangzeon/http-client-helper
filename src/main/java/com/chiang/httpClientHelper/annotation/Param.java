package com.chiang.httpClientHelper.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Param {

    String value() default "";

    boolean required() default true;
}

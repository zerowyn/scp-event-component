/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.utils.redis.annotation;

import java.lang.annotation.*;

/**
 * @author wangjun
 * @create 2017-12-26 15:54
 * @description：
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RedisCachePut {

    String value() default "";

    String key() default "";

    long expire() default -1L;
}

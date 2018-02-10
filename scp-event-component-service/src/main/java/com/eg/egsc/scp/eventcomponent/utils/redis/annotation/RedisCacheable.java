/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.utils.redis.annotation;

import java.lang.annotation.*;

/**
 * @author wangjun
 * @create 2017-12-26 15:53
 * @description：
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RedisCacheable {

    String key() default  "";

    String value() default "";

    long expire() default -1L;


}

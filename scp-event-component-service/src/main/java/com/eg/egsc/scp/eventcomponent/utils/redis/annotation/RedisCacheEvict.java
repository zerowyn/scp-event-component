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
public @interface RedisCacheEvict {

    String key() default "";

    String value() default "";

    boolean allEntries() default false;

    boolean beforeInvoation() default false;

}

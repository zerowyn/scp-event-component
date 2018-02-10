/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.common;

import com.eg.egsc.scp.eventcomponent.common.exception.EventComponentException;

/**
 * @author shiweisen
 * @since 2018-01-18
 */
public class ExpireTimeConstant {

    private ExpireTimeConstant() {
        throw new EventComponentException("UnSupport") ;
    }

    /**
     * trigger表数据的缓存失效时间,6秒
     */
    public static final long EXPIRE_CACHE_TRIGGER = 6L;

    /**
     * trigger_detail表数据的缓存失效时间,6秒
     */
    public static final long EXPIRE_CACHE_TRIGGER_DETAIL = 6L;

}

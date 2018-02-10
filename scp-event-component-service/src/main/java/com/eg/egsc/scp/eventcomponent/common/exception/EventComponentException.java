/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.common.exception;

import com.eg.egsc.common.exception.CommonException;

/**
 * 事件组件异常类
 *
 * @author chenhao
 * @since 2017-12-20
 */
public class EventComponentException extends CommonException {

    /**
     * 构造器
     *
     *@param code 异常信息
     */
    public EventComponentException(String code) {

        super(code);
    }

    /**
     * 构造器
     *
     *@param  code 编号
     *@param message 异常信息
     *@param values
     *@param cause
     */
    public EventComponentException(String code, String message, Object[] values, Throwable cause) {

        super(code, message, values, cause);
    }
}

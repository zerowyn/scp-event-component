/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.utils;

import com.eg.egsc.scp.eventcomponent.common.EventTypeDeviceEnum;

import java.util.UUID;

/**
 * 事件组件的工具类
 *
 * @author shiweisen
 * @since 2018-01-12
 */
public class EcStringUtils {

    private EcStringUtils(){
        throw new IllegalStateException("Utility class");
    }

    /**
     * 随机生成uuid
     *
     * @return 返回生成的uuid
     */
    public static String generateUuid() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    /**
     *根据事件类型返回设备名称
     *
     * @param eventType 事件类型编码
     * @return 返回对应的设备名称
     */
    public static String qryDeviceTypeName(int eventType) {
        int value = eventType / 10000;
        return EventTypeDeviceEnum.getName(value);
    }
}

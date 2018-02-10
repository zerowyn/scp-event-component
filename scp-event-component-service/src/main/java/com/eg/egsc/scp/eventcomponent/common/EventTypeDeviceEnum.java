/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.common;

/**
 * 事件类型的枚举类
 *
 * @author shiweisen
 * @since 2018-01-08
 */
public enum EventTypeDeviceEnum {
    PARKING("2004", "停车场", 1),
    CAMERA("2001", "智能摄像机", 2),
    DOORACS("2009", "门禁", 3),
    VIDEOINTERCOM("2013", "可视对讲", 4),
    LIFTCONTROL("2016", "梯控", 5),
    INFOSCREEN("2018", "广告屏", 6),
    PATROLDEVICE("2017", "巡更单兵设备", 7),
    BROADCAST("2019", "广播", 8);

    // 成员变量
    private String code;
    private String name;
    private int value;
    // 构造方法
    private EventTypeDeviceEnum(String code, String name, int value) {
        this.code = code;
        this.name = name;
        this.value = value;
    }
    // 普通方法
    public static String getName(int value) {
        for (EventTypeDeviceEnum c : EventTypeDeviceEnum.values()) {
            if (c.getValue() == value) {
                return c.name;
            }
        }
        return null;
    }

    public static EventTypeDeviceEnum getEnumByCode(String code) {
        for (EventTypeDeviceEnum c : EventTypeDeviceEnum.values()) {
            if (c.getName().equals(code)) {
                return c;
            }
        }
        return null;
    }

    // get方法
    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

}

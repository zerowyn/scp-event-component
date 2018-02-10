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
public enum EventLogStatusEnum {
    START(0, "开始"),
    PULSE(1, "脉冲"),
    END(2, "结束"),
    UNKNOW(3, "不适用");

    // 成员变量
    private Integer value;
    private String name;

    // 构造方法
    EventLogStatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;

    }
    // 普通方法
    public static EventLogStatusEnum getEnumByValue(Integer value) {
        if (value == null){
            return EventLogStatusEnum.UNKNOW;
        }
        for (EventLogStatusEnum c : EventLogStatusEnum.values()) {
            if (c.getValue() == value) {
                return c;
            }
        }
        return EventLogStatusEnum.UNKNOW;
    }

    // get方法
    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

}

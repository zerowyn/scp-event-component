/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.common;

/**
 * 联动类型字典表是否启用枚举类
 *
 * @author shiweisen
 * @since 2018-01-08
 */
public enum DictEnableFlagEnum {
    NO("禁用", 0), YES("启用", 1);
    // 成员变量
    private String name;
    private int value;
    // 构造方法
    private DictEnableFlagEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }
    // 普通方法
    public static String getName(int value) {
        for (DictEnableFlagEnum c : DictEnableFlagEnum.values()) {
            if (c.getValue() == value) {
                return c.name;
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

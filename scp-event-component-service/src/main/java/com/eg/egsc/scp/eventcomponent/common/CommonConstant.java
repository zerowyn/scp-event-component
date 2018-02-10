/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.common;

/**
 * 常用常量类
 *
 * @author chenhao
 * @since  2017-12-20
 */
public class CommonConstant {

    private CommonConstant() {
    }

    public static final String RESPONSE_CODE_SUCCESS = "00000" ;
    public static final String RESPONSE_CODE_INVALID_PARAMETER = "33316" ;
    public static final String RESPONSE_CODE_NOT_EXIST = "33111";

    /**
     * 暂定，保存或者更新失败
     */
    public static final String RESPONSE_CODE_SAVE_FAILURE = "33001";

    /**
     * 联动方式，中文，对应组件发送消息话题字典主表type_name
     */
    public static final String TRIGGER_TYPE_TOPIC = "triggerType_topic";

    /**
     * 联动方式，中文，对应组件设备类型编码字典主表type_name
     */
    public static final String TRIGGER_TYPE_DEVICE = "triggerType_device";

    /**
     * Operator LIKE 分隔符
     */
    public static final String OPERATOR_LIKE = "%" ;

    /**
     * 组件列表
     */
    public static final String COMPONENT_LIST = "component";

    /**
     * 组件与设备类型编码对应列表
     */
    public static final String COMPONENT_DEVICE_TYPE = "component_deviceType";

    /**
     * 组件和事件类型编码对应列表
     */
    public static final String COMPONENT_EVENT_TYPE = "component_eventType";


}

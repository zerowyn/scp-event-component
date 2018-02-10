/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.mq.constants;


import java.util.HashMap;
import java.util.Map;

/**
 * 事件消息常量类
 *
 * @author wangjun
 * @since  2017-12-10
 */
public class EventMsgConstant {

    private EventMsgConstant(){}

    /**
     * 发送消息到停车场组件的topic
     */
    public static final String SEND_TOPIC_PARKING = "MSG/EVENT/PARKING";

    /**
     * 发送消息到智能摄像机组件的topic
     */
    public static final String SEND_TOPIC_CAMERA = "MSG/EVENT/CAMERA";

    /**
     * 发送消息到门禁组件的topic
     */
    public static final String SEND_TOPIC_DOORACS = "MSG/EVENT/DOORACS";

    /**
     * 发送消息到可视对讲组件的topic
     */
    public static final String SEND_TOPIC_VIDEOINTERCOM = "MSG/EVENT/VIDEOINTERCOM";

    /**
     * 发送消息到梯控组件的topic
     */
    public static final String SEND_TOPIC_LIFTCONTROL = "MSG/EVENT/LIFTCONTROL";

    /**
     * 发送消息到信息发布屏组件的topic
     */
    public static final String SEND_TOPIC_INFOSCREEN = "MSG/EVENT/INFOSCREEN";

    /**
     * 发送消息到巡更组件的topic
     */
    public static final String SEND_TOPIC_PATROLDEVICE = "MSG/EVENT/PATROLDEVICE";

    /**
     * 发送消息到广播组件的topic
     */
    public static final String SEND_TOPIC_BROADCAST = "MSG/EVENT/BROADCAST";

    /**
     * 其他组件发送消息到事件组件的topic
     */
    public static final String SEND_TRIGGER_RESULT = "MSG/EVENT/TRIGGERRESULT";

    /**
     * 封裝組件topic和eventTypeId的map
     */
    private static final Map<String,Integer > TOPIC_EVENT_TYPE_MAP = new HashMap<>();

    public static Map<String,Integer> getTopicEventTypeMap(){
        return TOPIC_EVENT_TYPE_MAP ;
    }


    /**
     * 是否需要回执,需要回执
     */
    public static final String REPLY_FLAG_YES = "Yes";

    /**
     * 是否需要回执,不需要回执
     */
    public static final String REPLY_FLAG_NO = "No";

    /**
     * 是否需要回执,失败时需要回执
     */
    public static final String REPLY_FLAG_FAILURE = "Failure";

    /**
     * 类型名称，根据类型名称查类型表数据
     */
    public static final String DICT_TYPE_NAME = "component_topic";

}

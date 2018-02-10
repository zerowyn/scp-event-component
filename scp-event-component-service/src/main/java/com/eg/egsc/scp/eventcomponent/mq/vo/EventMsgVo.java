/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.mq.vo;



/**
 * @author wangjun
 * @create 2017-12-10 18:08
 * @description：物联网总线统一消息格式
 */
public class EventMsgVo {

    /**
     * 接入系统标识
     */
    private String appID;

    /**
     * 接入系统认证
     */
    private String token;

    /**
     * 设备编号
     */
    private String deviceID;

    /**
     * 事件组消息简称
     */
    private String topic;

    /**
     * 事件类型编号
     */
    private Integer eventTypeID;

    /**
     * 事件编号:要求网关生成一个uuid
     */
    private String messageID;

    /**
     * （0-起始，1-脉冲，2-结束， 3-不适用）目前只适用于视频设备，
     * 不是所有的设备都支持，所以这个字段应该是可选的。
     */
    private Integer eventStatus;

    /**
     * No：不需要回执, 为缺省设置；
     Yes：需要回执；
     Failure：失败时需要回执。
     */
    private String replyFlag;

    /**
     * 指定回复消息的目标队列
     */
    private String replyToQueue;

    /**
     * 同步调用模式下需要的关联ID
     */
    private String correlationID;

    /**
     * 网关标识，可选
     */
    private String gatewayID;

    /**
     * 消息报告时间：这个需要在网关上报总线的时候要添加这些信息
     */
    private String timestamp;

    /**
     * 标识该消息是否是回复消息：
     *（0-默认上下行消息，1-上行回复消息）
     */
    private Integer isReplyMsg;

    /**
     * 业务数据格式遵循网关与应用组件约定协议格式-Json Object String
     */
    private Object payload;


    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getEventTypeID() {
        return eventTypeID;
    }

    public void setEventTypeID(Integer eventTypeID) {
        this.eventTypeID = eventTypeID;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public Integer getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(Integer eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getReplyFlag() {
        return replyFlag;
    }

    public void setReplyFlag(String replyFlag) {
        this.replyFlag = replyFlag;
    }

    public String getReplyToQueue() {
        return replyToQueue;
    }

    public void setReplyToQueue(String replyToQueue) {
        this.replyToQueue = replyToQueue;
    }

    public String getCorrelationID() {
        return correlationID;
    }

    public void setCorrelationID(String correlationID) {
        this.correlationID = correlationID;
    }

    public String getGatewayID() {
        return gatewayID;
    }

    public void setGatewayID(String gatewayID) {
        this.gatewayID = gatewayID;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getIsReplyMsg() {
        return isReplyMsg;
    }

    public void setIsReplyMsg(Integer isReplyMsg) {
        this.isReplyMsg = isReplyMsg;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}

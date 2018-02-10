/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.mq.vo;

/**
 * @PackageName com.eg.egsc.scp.eventcomponent.mq.vo
 * @Description
 * @Author chenhao
 * @Date 2017/12/19 15:03
 */
public class TriggerDistributeVO {

    private String triggerType ;

    private Object triggerParams;

    private boolean hasNext ;

    private Integer triggerOrder ;

    private Integer eventType ;

    private String eventSourceCode ;

    private Long sendTime ;

    private Object eventInfo ;

    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }

    public Object getTriggerParams() {
        return triggerParams;
    }

    public void setTriggerParams(Object triggerParams) {
        this.triggerParams = triggerParams;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public Integer getTriggerOrder() {
        return triggerOrder;
    }

    public void setTriggerOrder(Integer triggerOrder) {
        this.triggerOrder = triggerOrder;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public String getEventSourceCode() {
        return eventSourceCode;
    }

    public void setEventSourceCode(String eventSourceCode) {
        this.eventSourceCode = eventSourceCode;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public Object getEventInfo() {
        return eventInfo;
    }

    public void setEventInfo(Object eventInfo) {
        this.eventInfo = eventInfo;
    }

    public TriggerDistributeVO(TriggerDistributeVO triggerDistribute) {
        this.eventType = triggerDistribute.getEventType();
        this.eventSourceCode =triggerDistribute.getEventSourceCode();
        this.sendTime = triggerDistribute.getSendTime();
    }

    public TriggerDistributeVO() { }
}

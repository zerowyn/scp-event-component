/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.mq.vo;

/**
 * @PackageName com.eg.egsc.scp.eventcomponent.mq.vo
 * @Description
 * @Author chenhao
 * @Date 2017/12/19 15:33
 */
public class TriggerResultVO {

    private String errMsg ;

    private boolean success ;

    private boolean hasNext ;

    private Integer triggerOrder ;

    private Long sendTime ;

    private Integer eventType ;

    private String eventSourceCode ;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
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
}

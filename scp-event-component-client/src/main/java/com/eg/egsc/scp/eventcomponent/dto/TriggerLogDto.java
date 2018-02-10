/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.dto;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

public class TriggerLogDto extends EventBusinessDto {

    @NotBlank(message = "event.triggerLog.triggerLogId.valid")
    private String triggerLogId;

    @NotBlank(message = "event.triggerLog.triggerType.valid")
    private String triggerType;

    private Object triggerResult;

    private Short order;

    private Date triggerTime;

    private String creator;

    private Integer retryCount;

    @NotBlank(message = "event.triggerLog.eventLogId.valid")
    private String eventLogId;

    public String getTriggerLogId() {
        return triggerLogId;
    }

    public void setTriggerLogId(String triggerLogId) {
        this.triggerLogId = triggerLogId;
    }

    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }

    public Object getTriggerResult() {
        return triggerResult;
    }

    public void setTriggerResult(Object triggerResult) {
        this.triggerResult = triggerResult;
    }

    public Short getOrder() {
        return order;
    }

    public void setOrder(Short order) {
        this.order = order;
    }

    public Date getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Date triggerTime) {
        this.triggerTime = triggerTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public String getEventLogId() {
        return eventLogId;
    }

    public void setEventLogId(String eventLogId) {
        this.eventLogId = eventLogId;
    }

}

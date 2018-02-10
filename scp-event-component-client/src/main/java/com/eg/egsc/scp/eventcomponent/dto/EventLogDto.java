/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.dto;

import org.hibernate.validator.constraints.NotBlank;
import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 * Created by xulei on 2017/12/11.
 * 事件日志
 */
public class EventLogDto extends EventBusinessDto {
    /**
     * 事件日志的唯一编号
     */
    @NotBlank(message = "event.eventLog.eventLogId.valid")
    private String eventLogId;

    /**
     * 事件类型
     */
    @NotNull(message = "event.eventLog.eventType.valid")
    private Integer eventType;
    /**
     * 事件开始时间
     */
    private Date startTime;
    /**
     * 事件结束时间
     */
    private Date endTime;
    /**
     * 内容
     */
    private String content;
    /**
     * 创建者
     */
    private String creator;

    /**
     * 设备编号
     */
    @NotBlank(message ="event.eventLog.deviceCode.valid")
    private String deviceCode;
    /**
     * 资源点编号
     */
    private String resourceCode;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 资源点名称
     */
    private String resourceName;
    /**
     * 事件状态  1开始  2脉动  3结束
     */
    private Integer status;
    private Object extend;

    @Override
    public String toString() {
        return "EventLogDto{" +
                ", eventLogId='" + eventLogId + '\'' +
                ", eventType=" + eventType +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", content='" + content + '\'' +
                ", creator='" + creator + '\'' +
                ", deviceCode='" + deviceCode + '\'' +
                ", resourceCode='" + resourceCode + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", resourceName='" + resourceName + '\'' +
                ", status=" + status +
                ", extend=" + extend +
                '}';
    }

    public String getEventLogId() {
        return eventLogId;
    }

    public void setEventLogId(String eventLogId) {
        this.eventLogId = eventLogId;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getExtend() {
        return extend;
    }

    public void setExtend(Object extend) {
        this.extend = extend;
    }

}

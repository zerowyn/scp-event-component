/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.dto.trigger;

/**
 * @author wangjun
 * @create 2018-01-04 20:57
 * @description：
 */
public class TriggerDeviceDto {

    /**
     * 联动方式
     */
    private String triggerType;

    /**
     * 联动方式对应中文
     */
    private String triggerName;

    /**
     * 联动方式对应组件的topic
     */
    private String to;

    /**
     * 联动方式对应组件下设备类型编码
     */
    private String deviceTypeCode;

    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDeviceTypeCode() {
        return deviceTypeCode;
    }

    public void setDeviceTypeCode(String deviceTypeCode) {
        this.deviceTypeCode = deviceTypeCode;
    }
}

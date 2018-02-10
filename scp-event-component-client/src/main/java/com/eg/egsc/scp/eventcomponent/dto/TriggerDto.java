/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.dto;

import org.hibernate.validator.constraints.NotBlank;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class TriggerDto extends EventBusinessDto {

    /**
     *  标记某一类型事件
     */
    @NotNull(message = "event.trigger.save.dto.eventType.null")
    private Integer eventType;

    /**
     *  事件源设备编号
     */
    @NotBlank(message = "event.trigger.save.dto.eventSourceCode.null")
    private String eventSourceCode;


    @NotNull(message = "event.trigger.save.dto.triggers.null")
    @Valid
    private List<TriggerDetailDto> triggers;

    /**
     *  是否有序
     */
    @NotNull(message = "event.trigger.save.dto.isSequential.null")
    private Boolean isSequential;

    @NotBlank(message = "event.trigger.save.dto.creator.null")
    private String creator;

    private String deviceTypeName;

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

    public List<TriggerDetailDto> getTriggers() {
        return triggers;
    }

    public void setTriggers(List<TriggerDetailDto> triggers) {
        this.triggers = triggers;
    }

    public Boolean getIsSequential() {
        return isSequential;
    }

    public void setIsSequential(Boolean sequential) {
        isSequential = sequential;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }
}

/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.dto;

import com.eg.egsc.framework.client.dto.BaseBusinessDto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class TriggerDelDto extends BaseBusinessDto {

    @NotNull(
            message = "event.trigger.save.dto.serviceIndexCode.null"
    )
    private String serviceIndexCode;//		下发联动规则配置的业务组件标示

    @NotNull(
            message = "event.trigger.save.dto.opUserId.null"
    )
    private String opUserId;//		操作用户I

    @NotNull(
            message = "event.trigger.save.dto.triggerIdList.null"
    )
    private List<String> triggerIdList;

    public String getServiceIndexCode() {
        return serviceIndexCode;
    }

    public void setServiceIndexCode(String serviceIndexCode) {
        this.serviceIndexCode = serviceIndexCode;
    }

    public String getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }

    public List<String> getTriggerIdList() {
        return triggerIdList;
    }

    public void setTriggerIdList(List<String> triggerIdList) {
        this.triggerIdList = triggerIdList;
    }
}

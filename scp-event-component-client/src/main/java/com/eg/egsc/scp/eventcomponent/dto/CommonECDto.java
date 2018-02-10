/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.dto;

import com.eg.egsc.framework.client.dto.BaseBusinessDto;

import javax.validation.constraints.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CommonECDto extends BaseBusinessDto {

    @NotNull(
            message = "event.trigger.save.dto.params.null"
    )
    private Map<String, Object> map;

    public CommonECDto() {
        this.map = new HashMap<>();
    }

    public CommonECDto(Map<String, Object> man) {
        this.map = man;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}

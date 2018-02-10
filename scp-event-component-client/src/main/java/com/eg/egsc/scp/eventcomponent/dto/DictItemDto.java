/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.dto;

import com.eg.egsc.framework.client.dto.BaseBusinessDto;

/**
 * @author shiweisen
 * @since 2018-01-22
 */
public class DictItemDto extends BaseBusinessDto {

    private String code;

    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

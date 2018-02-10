/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.dto;

public class TriggerResultDto {
    private String message ;

    private String flag ;

    public TriggerResultDto() {
    }

    public TriggerResultDto(String message, String flag) {

        this.message = message;
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}

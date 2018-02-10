/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.utils.adaptor;

import com.eg.egsc.scp.eventcomponent.common.EventLogStatusEnum;
import com.eg.egsc.scp.eventcomponent.dto.EventLogDto;
import com.eg.egsc.scp.eventcomponent.mapper.entity.EventLog;

public class EpsEventLogAdaptor {

    private EpsEventLogAdaptor() {
    }

    public static void toDto(EventLogDto epsEventLogDto, EventLog epsEventLog){
        // Is null ,return
        if (null == epsEventLogDto || null == epsEventLog){
            return;
        }
        epsEventLogDto.setUuid(epsEventLog.getUuid());
        epsEventLogDto.setCreateTime(epsEventLog.getCreateTime());
        epsEventLogDto.setStartTime(epsEventLog.getStartTime());
        epsEventLogDto.setEndTime(epsEventLog.getEndTime());
        epsEventLogDto.setUpdateTime(epsEventLog.getUpdateTime());
        epsEventLogDto.setEventLogId(epsEventLog.getEventLogId());

        epsEventLogDto.setContent(epsEventLog.getContent());
        epsEventLogDto.setCourtUuid(epsEventLog.getCourtUuid());
        epsEventLogDto.setCreator(epsEventLog.getCreator());
        epsEventLogDto.setExtend(epsEventLog.getExtend());
        epsEventLogDto.setUpdateUser(epsEventLog.getUpdateUser());
        epsEventLogDto.setStatus(epsEventLog.getStatus());
        epsEventLogDto.setResourceName(epsEventLog.getResourceName());
        epsEventLogDto.setResourceCode(epsEventLog.getResourceCode());
        epsEventLogDto.setEventType(epsEventLog.getEventType());
        epsEventLogDto.setDeviceName(epsEventLog.getDeviceName());
        epsEventLogDto.setDeviceCode(epsEventLog.getDeviceCode());
        epsEventLogDto.setCreateUser(epsEventLog.getCreateUser());

    }


    public static void toEntity(EventLog epsEventLog, EventLogDto epsEventLogDto){
        // Is null ,return
        if (null == epsEventLogDto || null == epsEventLog){
            return;
        }
        epsEventLog.setStartTime(epsEventLogDto.getStartTime());
        epsEventLog.setEndTime(epsEventLogDto.getEndTime());
        epsEventLog.setCreateTime(epsEventLogDto.getCreateTime());
        epsEventLog.setUpdateTime(epsEventLogDto.getUpdateTime());
        epsEventLog.setUuid(epsEventLogDto.getUuid());
        epsEventLog.setEventLogId(epsEventLogDto.getEventLogId());
        epsEventLog.setContent(epsEventLogDto.getContent());
        epsEventLog.setCourtUuid(epsEventLogDto.getCourtUuid());
        epsEventLog.setCreator(epsEventLogDto.getCreator());
        epsEventLog.setExtend(epsEventLogDto.getExtend());
        epsEventLog.setUpdateUser(epsEventLogDto.getUpdateUser());

        epsEventLog.setStatus(EventLogStatusEnum.getEnumByValue(epsEventLogDto.getStatus()).getValue());
        epsEventLog.setResourceName(epsEventLogDto.getResourceName());
        epsEventLog.setResourceCode(epsEventLogDto.getResourceCode());
        epsEventLog.setEventType(epsEventLogDto.getEventType());
        epsEventLog.setDeviceName(epsEventLogDto.getDeviceName());
        epsEventLog.setDeviceCode(epsEventLogDto.getDeviceCode());
        epsEventLog.setCreateUser(epsEventLogDto.getCreateUser());
    }

}

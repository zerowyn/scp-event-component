/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.utils.adaptor;


import com.eg.egsc.common.component.utils.BeanUtils;
import com.eg.egsc.scp.eventcomponent.dto.TriggerLogDto;
import com.eg.egsc.scp.eventcomponent.mapper.entity.TriggerLog;

/**
 * TriggerLog适配，Dto与Entity转换
 */
public class TriggerLogAdaptor {

    private TriggerLogAdaptor() {
    }

    /**
     * TriggerLog 转换对象为Dto
     * @param triggerLogDto
     * @param triggerLog
     */
    public static void toDto(TriggerLogDto triggerLogDto, TriggerLog triggerLog){
        // Is null ,return
        if (null == triggerLogDto || null == triggerLog){
            return;
        }
        BeanUtils.copyProperties(triggerLogDto,triggerLog);

    }

    /**
     * TriggerLogDto 转换对象为Entity
     * @param triggerLogDto
     * @param triggerLog
     */
    public static void toEntity(TriggerLogDto triggerLogDto, TriggerLog triggerLog){
        // Is null ,return
        if (null == triggerLogDto || null == triggerLog){
            return;
        }
        BeanUtils.copyProperties(triggerLog,triggerLogDto);
    }

}

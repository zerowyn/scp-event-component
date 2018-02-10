/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.client;

import com.eg.egsc.framework.client.dto.BaseBusinessDto;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.scp.eventcomponent.dto.DictItemDto;

/**
 * 提供的联动类型的接口
 *
 * @author wangjun
 * @since 2018-01-05
 */
public interface TriggerTypeClient {

    /**
     * 获取联动方式列表
     *
     * @param baseBusinessDto 请求头信息
     * @return 返回联动方式列表
     */
    ResponseDto getTriggerTypeList(BaseBusinessDto baseBusinessDto);

    /**
     * 获取组件列表
     *
     * @return 返回组件列表信息
     */
    ResponseDto listComponent(BaseBusinessDto baseBusinessDto);

    /**
     * 根据编码，获取设备分类（组件）和事件类型的对应列表
     *
     * @return 返回列表信息
     */
    ResponseDto listComponentWithEventType(DictItemDto dictItemDto);

    /**
     * 根据编码，获取设备分类（组件）和设备类型的对应列表
     *
     * @return 返回列表信息
     */
    ResponseDto listComponentWithDeviceType(DictItemDto dictItemDto);
}

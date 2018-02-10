/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.client.impl;

import com.eg.egsc.framework.client.core.BaseApiClient;
import com.eg.egsc.framework.client.dto.BaseBusinessDto;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.scp.eventcomponent.client.TriggerTypeClient;
import com.eg.egsc.scp.eventcomponent.dto.DictItemDto;

import org.springframework.stereotype.Component;


/**
 * 提供的联动类型的接口
 *
 * @author wangjun
 * @since 2018-01-05
 */
@Component
public class TriggerTypeClientImpl extends BaseApiClient implements TriggerTypeClient {
    @Override
    protected String getContextPath() {
        return "/scp-eventcomponent";
    }

    public TriggerTypeClientImpl(){}

    public TriggerTypeClientImpl(String gatewayUrl) {
        super(gatewayUrl);
    }

    /**
     * 获取联动方式列表
     *
     * @param baseBusinessDto 请求头信息
     * @return 返回联动方式列表
     */
    @Override
    public ResponseDto getTriggerTypeList(BaseBusinessDto baseBusinessDto) {
        return  post("/api/triggerType/listTriggerTypes",baseBusinessDto);
    }

    /**
     * 获取组件列表
     *
     * @return 返回组件列表信息
     */
    @Override
    public ResponseDto listComponent(BaseBusinessDto baseBusinessDto) {
        return  post("/api/triggerType/listComponent",baseBusinessDto);
    }

    /**
     * 根据编码，获取设备分类（组件）和事件类型的对应列表
     *
     * @return 返回列表信息
     */
    @Override
    public ResponseDto listComponentWithEventType(DictItemDto dictItemDto) {
        return  post("/api/triggerType/listComponentWithEventType",dictItemDto);
    }

    /**
     * 根据编码，获取设备分类（组件）和设备类型的对应列表
     *
     * @return 返回列表信息
     */
    @Override
    public ResponseDto listComponentWithDeviceType(DictItemDto dictItemDto) {
        return  post("/api/triggerType/listComponentWithDeviceType",dictItemDto);
    }

}

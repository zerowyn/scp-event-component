/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.api;

import com.eg.egsc.framework.client.dto.RequestDto;
import com.eg.egsc.scp.eventcomponent.dto.DictItemDto;

import org.junit.Test;

/**
 * @PackageName com.eg.egsc.scp.eventcomponent.api
 * @Description
 * @Author chenhao
 * @Date 2017/12/14 20:47
 */
public class TriggerTypeApiTest extends BaseApiTest {

    /**
     * 测试获取列表方法
     */
    @Test
    public void testGetTriggerTypeList() {
        String dto = "test";
        mockMvcPost(dto,"/api/triggerType/listTriggerTypes");
    }

    /**
     * deviceTypeCode无值时 获取设备分类（组件）和与设备类型编码的对应列表
     *
     */
    @Test
    public void listComponentWithType() {
        RequestDto<DictItemDto> dto = InstanceApiTest.getInstance().newMockDictItemDto();
        mockMvcPost(dto,"/api/triggerType/listComponentWithType");
    }

    /**
     * deviceTypeCode有值时 获取设备分类（组件）和事件类型的对应列表
     *
     */
    @Test
    public void listComponentWithEventType() {
        RequestDto<DictItemDto> dto = InstanceApiTest.getInstance().newMockDictItemDto();
        DictItemDto itemDto = dto.getData();
        itemDto.setCode("2016");
        mockMvcPost(dto,"/api/triggerType/listComponentWithType");
    }





}

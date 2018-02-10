/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.api;

import com.eg.egsc.framework.client.dto.RequestDto;
import com.eg.egsc.scp.eventcomponent.dto.PageQueryDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerDelDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerDetailDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerDto;
import com.eg.egsc.scp.eventcomponent.service.InstanceClassTest;
import com.eg.egsc.scp.eventcomponent.utils.redis.RedisCacheUtils;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 联动规则Api的测试方法
 * @author shiweisen
 * @since 2018-1-8
 */
public class EventTriggerApiTest extends BaseApiTest {

    private static final String SAVE_TRIGGER_URL = "/api/trigger/insertTrigger";

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    /**
     * 测试数据库不存在该数据情况下的保存方法
     */
    @Test
    public void testSaveTriggerNew() {
        redisCacheUtils.deleteKey("CACHE.TRIGGER.30000301058FCDB0000EB0111");
        RequestDto<TriggerDto> requestDto = InstanceApiTest.getInstance().newMockTriggerDto();
        mockMvcPost(requestDto,SAVE_TRIGGER_URL);
    }

    /**
     * 测试数据库已存在该数据情况下的保存方法
     */
    @Test
    public void testSaveTriggerRepeat() {
        testSaveTriggerNew();
        RequestDto<TriggerDto> requestDto = InstanceApiTest.getInstance().newMockTriggerDto();
        mockMvcPost(requestDto,SAVE_TRIGGER_URL);
    }

    /**
     * 测试UUId == null的情况下的更新方法
     */
    @Test
    public void testUpdateUuidNull() {
        //先保存，再更新
        testSaveTriggerNew();
        RequestDto<TriggerDto> requestDto = InstanceApiTest.getInstance().newMockTriggerDto();
        requestDto.getData().setUuid(null);
        requestDto.getData().setUpdateUser("UpdaterApi");
        mockMvcPost(requestDto,"/api/trigger/updateTrigger");
    }

    /**
     * 测试正常情况下的更新方法
     */
    @Test
    public void testUpdateTrigger() {
        //先保存，再更新
        testSaveTriggerNew();
        RequestDto<TriggerDto> requestDto = InstanceApiTest.getInstance().newMockTriggerDto();
        requestDto.getData().setUpdateUser("UpdaterApi");
        mockMvcPost(requestDto,"/api/trigger/updateTrigger");
    }

    /**
     * 测试根据triggerId查询数据的方法
     */
    @Test
    public void testQueryTriggerById() {
        //保存了一条新数据
        testSaveTriggerNew();
        RequestDto<TriggerDto> requestDto = InstanceApiTest.getInstance().newMockTriggerDto();
        TriggerDto triggerDto = requestDto.getData();
        List<TriggerDetailDto> list = triggerDto.getTriggers();
        TriggerDetailDto detailDto = InstanceClassTest.getInstance().newTriggerDetailDto();
        detailDto.setUuid(null);
        list.add(detailDto);
        mockMvcPost(requestDto,SAVE_TRIGGER_URL);
        //重新查询出来
        mockMvcPost(requestDto,"/api/trigger/getTrigger");
    }

    /**
     * 测试删除Trigger
     * 存入要删除的triggerIds
     */
    @Test
    public void testDeleteEventTrigger() {
        testSaveTriggerNew();
        List<String> triggerIdList = new ArrayList<>();
        triggerIdList.add("21e81dc5363a4895839282cc0e2a81c3");
        RequestDto<TriggerDelDto> requestDto = InstanceApiTest.getInstance().newMockTriggerDelDto(triggerIdList);
        mockMvcPost(requestDto,"/api/trigger/deleteTrigger");
    }

    /**
     * 测试分页查询trigger方法
     */
    @Test
    public void testQueryTriggerPage() {
        RequestDto<PageQueryDto> requestDto = InstanceApiTest.getInstance().newMockTriggerPageQueryDto();
        mockMvcPost(requestDto,"/api/trigger/listTriggers");
    }

}

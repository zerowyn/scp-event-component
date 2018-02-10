/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.client;

import com.eg.egsc.scp.eventcomponent.EventComponentServiceApplication;
import com.eg.egsc.scp.eventcomponent.dto.PageQueryDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerDelDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerDto;
import com.eg.egsc.scp.eventcomponent.mapper.entity.Trigger;
import com.eg.egsc.scp.eventcomponent.service.InstanceClassTest;
import com.eg.egsc.scp.eventcomponent.service.TriggerService;
import com.eg.egsc.scp.eventcomponent.utils.redis.RedisCacheUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 联动规则client层测试类
 *
 * @author shiweisen
 * @since 2018-01-17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EventComponentServiceApplication.class })
public class TriggerClientTest {


    @Autowired
    private TriggerClient triggerClientImpl;

    @Autowired
    private TriggerService triggerServiceImpl;

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    private int testEventType = 30000;
    private String testEventSourceCode = "301058FCDB0000EB0111";
    private String testKey = "CACHE.TRIGGER."+testEventType+testEventSourceCode;

    /**
     * 测试数据库不存在该数据情况下的保存方法
     */
    @Test
    public void testSaveTriggerNew() {
        redisCacheUtils.deleteKey(testKey);
        TriggerDto triggerDto =  InstanceClassTest.getInstance().newTriggerDto();
        triggerClientImpl.saveTrigger(triggerDto);
        testDeleteEventTrigger();
    }


    /**
     * 测试正常情况下的更新方法
     * 根据eventType和eventSourceCode判断
     */
    @Test
    public void testUpdateTrigger() {
        redisCacheUtils.deleteKey(testKey);
        //先保存，再更新
        TriggerDto triggerDto =  InstanceClassTest.getInstance().newTriggerDto();
        triggerClientImpl.saveTrigger(triggerDto);
        Trigger trigger = triggerServiceImpl.queryTriggerByEventKey(testEventType,testEventSourceCode);
        triggerDto.setUuid(trigger.getUuid());
        triggerDto.setCreator("UpdateClient");
        triggerClientImpl.updateTrigger(triggerDto);
        testDeleteEventTrigger();
    }

    /**
     * 测试根据triggerId查询数据的方法
     */
    @Test
    public void testQueryTriggerById() {
        redisCacheUtils.deleteKey(testKey);
        //保存了一条新数据
        TriggerDto triggerDto =  InstanceClassTest.getInstance().newTriggerDto();
        triggerClientImpl.saveTrigger(triggerDto);
        //重新查询出来
        triggerClientImpl.queryTriggerById(triggerDto);
        testDeleteEventTrigger();
     }

    /**
     * 测试删除Trigger
     * 存入要删除的triggerIds
     */
    @Test
    public void testDeleteEventTrigger() {
        Trigger trigger = triggerServiceImpl.queryTriggerByEventKey(testEventType,testEventSourceCode);
        List<String> triggerIdList = new ArrayList<>();
        if(trigger != null){
            triggerIdList.add(trigger.getUuid());
        }
        TriggerDelDto epsTriggerDelDto = new TriggerDelDto();
        epsTriggerDelDto.setTriggerIdList(triggerIdList);
        triggerClientImpl.deleteTrigger(epsTriggerDelDto);
        redisCacheUtils.deleteKey(testKey);
    }

    /**
     * 测试分页查询trigger方法
     */
    @Test
    public void testQueryTriggerPage() {
        PageQueryDto pageQueryDto = InstanceClassTest.getInstance().newPageQueryDto();
        triggerClientImpl.queryTriggerPage(pageQueryDto);
    }
}

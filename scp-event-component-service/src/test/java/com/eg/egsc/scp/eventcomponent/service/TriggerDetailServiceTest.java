/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.service;

import com.eg.egsc.scp.eventcomponent.EventComponentServiceApplication;
import com.eg.egsc.scp.eventcomponent.mapper.entity.Trigger;
import com.eg.egsc.scp.eventcomponent.mapper.entity.TriggerDetail;
import com.eg.egsc.scp.eventcomponent.utils.EcStringUtils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EventComponentServiceApplication.class })
@Transactional
@Rollback
public class TriggerDetailServiceTest {

    @Autowired
    private TriggerService triggerServiceImpl;

    @Autowired
    private TriggerDetailService triggerDetailServiceImpl;

    private String uuidTemp = "21e81dc5363a4895839282cc0e2a81c3";


    @Test
    public void testSaveTrigger() {
        Trigger trigger = InstanceClassTest.getInstance().triggerInstance();
        List<TriggerDetail> triggerDetails = new ArrayList<>();
        TriggerDetail detail = InstanceClassTest.getInstance().triggerDetailInstance();
        triggerDetails.add(detail);
        Trigger res = triggerServiceImpl.saveTrigger(trigger, triggerDetails);
        Assert.assertNotNull(res);
    }

    /**
     * 保存联动明细
     *
     */
    @Test
    public void testSaveTriggerDetail(){
        testSaveTrigger();
        String triggerId = uuidTemp;
        List<TriggerDetail> triggerDetails = new ArrayList<>();
        TriggerDetail detail = InstanceClassTest.getInstance().triggerDetailInstance();
        detail.setUuid(EcStringUtils.generateUuid());
        detail.setTriggerId(triggerId);
        detail.setCreateUser("DtailTest");
        triggerDetails.add(detail);
        triggerDetailServiceImpl.saveTriggerDetail(triggerDetails);
    }

    /**
     * 联动规则
     *
     */
    @Test
    public void testDeleteTriggerDetailByTriggerId(){
        testSaveTrigger();
        String triggerId = uuidTemp;
        List<String> triggerIds = new ArrayList<>();
        triggerIds.add(triggerId);
        triggerDetailServiceImpl.deleteTriggerDetailByTriggerId(triggerIds);

    }

    /**
     * 根据联动规则ID，删除联动明细
     *
     */
    @Test
    public void deleteTriggerDetailByTriggerId(){
        testSaveTrigger();
        triggerDetailServiceImpl.deleteTriggerDetailByTriggerId(uuidTemp);
    }

    /**
     * 查询联动规则明细
     *
     */
    @Test
    public void testQueryTriggerDetailByTriggerId(){
        testSaveTrigger();
        String triggerId = uuidTemp;
        List<TriggerDetail> res = triggerDetailServiceImpl.queryTriggerDetailByTriggerId(triggerId);
        Assert.assertNotNull(res);
    }


}

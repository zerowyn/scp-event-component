/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.redis;

import com.eg.egsc.scp.eventcomponent.EventComponentServiceApplication;
import com.eg.egsc.scp.eventcomponent.mapper.entity.Trigger;
import com.eg.egsc.scp.eventcomponent.mapper.entity.TriggerDetail;
import com.eg.egsc.scp.eventcomponent.service.InstanceClassTest;
import com.eg.egsc.scp.eventcomponent.service.TriggerService;
import com.eg.egsc.scp.eventcomponent.utils.redis.RedisCacheUtils;

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

/**
 * Redis缓存测试
 *
 * @author zhouxing
 * @since  2018-1-5
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EventComponentServiceApplication.class })
@Transactional
@Rollback
public class TriggerServiceRedisTest {
    @Autowired
    RedisCacheUtils redisCacheUtils;

    @Autowired
    TriggerService triggerServiceImpl;

    public static final String EVENT_SOURCE_CODE_TEST = "301058FCDB0000EB0111";
    public static final String EC_TRIGGER_KEY_TEST = "CACHE.TRIGGER.30000301058FCDB0000EB0111";

    /**
     *   测试根据eventType,eventSourceCode查询trigger时是缓存是否生效
     */
    @Test
    public void testCacheableTriggerService(){
        Trigger trigger = InstanceClassTest.getInstance().triggerInstance();
        List<TriggerDetail> triggerDetails = new ArrayList<>();
        triggerServiceImpl.saveTrigger(trigger, triggerDetails);
        triggerServiceImpl.queryTriggerByEventKey(30000,EVENT_SOURCE_CODE_TEST);
        Object result = redisCacheUtils.get(EC_TRIGGER_KEY_TEST);
        Assert.assertNotNull(result);
    }

    /**
     *   测试更新trigger时是缓存是否更新
     */
    @Test
    public void testCachePutTriggerService(){
        Trigger triggerEntity = InstanceClassTest.getInstance().triggerInstance();
        ArrayList<TriggerDetail> triggerDetails = new ArrayList<>();
        triggerServiceImpl.saveTrigger(triggerEntity, triggerDetails);
        Trigger trigger = triggerServiceImpl.queryTriggerByEventKey(30000, EVENT_SOURCE_CODE_TEST);
        trigger.setCreator("UpdateCache");
        triggerServiceImpl.updateTrigger(trigger,triggerDetails);
        Trigger result = (Trigger)redisCacheUtils.get(EC_TRIGGER_KEY_TEST);
        Assert.assertEquals("UpdateCache",result.getCreator());
        triggerServiceImpl.deleteByTrigger(trigger);
        Object obj2 = redisCacheUtils.get(EC_TRIGGER_KEY_TEST);
        Assert.assertNull(obj2);
    }

    /**
     *   测试删除trigger时是缓存是否删除
     */
    @Test
    public void testCacheEvictTriggerService(){
        Trigger triggerEntity = InstanceClassTest.getInstance().triggerInstance();
        ArrayList<TriggerDetail> triggerDetails = new ArrayList<>();
        triggerServiceImpl.saveTrigger(triggerEntity, triggerDetails);
        Trigger trigger = triggerServiceImpl.queryTriggerByEventKey(30000,EVENT_SOURCE_CODE_TEST);

        triggerServiceImpl.deleteByTrigger(trigger);
        Trigger result = (Trigger)redisCacheUtils.get(EC_TRIGGER_KEY_TEST);
        Assert.assertNull(result);
    }

}

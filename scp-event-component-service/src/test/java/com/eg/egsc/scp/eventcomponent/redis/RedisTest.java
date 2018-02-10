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
import com.eg.egsc.common.component.utils.JsonUtil;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjun
 * @create 2017-12-28 16:37
 * @description：
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EventComponentServiceApplication.class })
@Transactional
@Rollback
public class RedisTest {

    private static final Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @Autowired
    private TriggerService triggerServiceImpl;

    public static final String EVENT_SOURCE_CODE_TEST = "301058FCDB0000EB0111";
    public static final String EC_TRIGGER_KEY_TEST = "CACHE.TRIGGER.30000301058FCDB0000EB0111";

    @Test
    public void testRedisUtils(){
        redisCacheUtils.set("key1","343434");
        logger.info(JsonUtil.toJsonString(redisCacheUtils.get("key1")));
        Assert.assertEquals("343434",redisCacheUtils.get("key1"));
    }

    @Test
    public void testDelRedisUtils(){
        redisCacheUtils.deletePrefixKey("EC:");
    }



    @Test
    public void testSaveCache(){
        Trigger trigger = InstanceClassTest.getInstance().triggerInstance();
        List<TriggerDetail> triggerDetails = new ArrayList<>();
        Trigger res = triggerServiceImpl.saveTrigger(trigger, triggerDetails);
        Assert.assertNotNull(res);
    }


    @Test
    public void testCacheable(){
        testSaveCache();
        triggerServiceImpl.queryTriggerByEventKey(30000,EVENT_SOURCE_CODE_TEST);
        Assert.assertNotNull(redisCacheUtils.get(EC_TRIGGER_KEY_TEST));
        redisCacheUtils.deleteKey(EC_TRIGGER_KEY_TEST);
    }

    @Test
    public void testCachePut(){
        Trigger trigger = InstanceClassTest.getInstance().triggerInstance();
        ArrayList<TriggerDetail> triggerDetails = new ArrayList<>();
        triggerServiceImpl.saveTrigger(trigger, triggerDetails);
        trigger.setCreator("UpdateRedis");
        redisCacheUtils.deleteKey(EC_TRIGGER_KEY_TEST);
        triggerServiceImpl.updateTrigger(trigger,triggerDetails);
        Trigger result = (Trigger)redisCacheUtils.get(EC_TRIGGER_KEY_TEST);
        Assert.assertNotNull(result);
        redisCacheUtils.deleteKey(EC_TRIGGER_KEY_TEST);

    }

    @Test
    public void testCacheEvict(){
        testSaveCache();
        Trigger trigger = triggerServiceImpl.queryTriggerByEventKey(30000,EVENT_SOURCE_CODE_TEST);
        triggerServiceImpl.deleteByTrigger(trigger);
        Assert.assertNull(redisCacheUtils.get(EC_TRIGGER_KEY_TEST));

    }

    @Test
    public void testDeleteCache(){
        triggerServiceImpl.queryTriggerByEventKey(30000,EVENT_SOURCE_CODE_TEST);
        redisCacheUtils.deleteKey(EC_TRIGGER_KEY_TEST);
        Object obj2 = redisCacheUtils.get(EC_TRIGGER_KEY_TEST);
        Assert.assertNull(obj2);
    }
}

/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.redis;

import com.eg.egsc.scp.eventcomponent.EventComponentServiceApplication;
import com.eg.egsc.scp.eventcomponent.mapper.entity.Trigger;
import com.eg.egsc.scp.eventcomponent.mapper.entity.TriggerDetail;
import com.eg.egsc.scp.eventcomponent.service.InstanceClassTest;
import com.eg.egsc.scp.eventcomponent.service.TriggerDetailService;
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
public class TriggerDetailServiceRedisTest {
    @Autowired
    RedisCacheUtils redisCacheUtils;

    @Autowired
    TriggerDetailService triggerDetailServiceImpl;

    @Autowired
    TriggerService triggerServiceImpl;


    /**
     *   测试根据triggerId查询triggerDetail时是缓存是否生效
     */
    @Test
    public void testCacheableTriggerDetailService(){
        Trigger trigger = InstanceClassTest.getInstance().triggerInstance();
        List<TriggerDetail> triggerDetails = new ArrayList<>();
        TriggerDetail detail = InstanceClassTest.getInstance().triggerDetailInstance();
        triggerDetails.add(detail);
        triggerServiceImpl.saveTrigger(trigger, triggerDetails);
        String triggerId = "21e81dc5363a4895839282cc0e2a81c3";
        Object result = triggerDetailServiceImpl.queryTriggerDetailByTriggerId(triggerId);
        Assert.assertNotNull(result);
    }


    /**
     *   测试根据triggerId删除triggerDetail时是否删除缓存
     */
    @Test
    public void testCacheEvictTriggerDetailService(){
        Trigger trigger = InstanceClassTest.getInstance().triggerInstance();
        List<TriggerDetail> triggerDetails = new ArrayList<>();
        TriggerDetail detail = InstanceClassTest.getInstance().triggerDetailInstance();
        triggerDetails.add(detail);
        triggerServiceImpl.saveTrigger(trigger, triggerDetails);
        String triggerId = "21e81dc5363a4895839282cc0e2a81c3";
        triggerDetailServiceImpl.queryTriggerDetailByTriggerId(triggerId);
        triggerDetailServiceImpl.deleteTriggerDetailByTriggerId(triggerId);
        Assert.assertNull(redisCacheUtils.get("CACHE.TRIGGERDETAIL.21e81dc5363a4895839282cc0e2a81c3"));
    }
}

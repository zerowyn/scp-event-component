/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.client;

import com.eg.egsc.framework.client.dto.BaseBusinessDto;
import com.eg.egsc.scp.eventcomponent.EventComponentServiceApplication;
import com.eg.egsc.scp.eventcomponent.dto.DictItemDto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 联动类型client层测试类
 *
 * @author shiweisen
 * @since 2018-01-17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EventComponentServiceApplication.class })
public class TriggerTypeClientTest {


    @Autowired
    private TriggerTypeClient triggerTypeClientImpl;

    /**
     * 测试获取列表方法
     */
    @Test
    public void testGetTriggerTypeList() {
        BaseBusinessDto baseBusinessDto = new BaseBusinessDto();
        triggerTypeClientImpl.getTriggerTypeList(baseBusinessDto);
    }

}

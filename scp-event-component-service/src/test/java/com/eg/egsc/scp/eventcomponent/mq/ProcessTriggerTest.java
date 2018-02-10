/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.mq;

import com.eg.egsc.scp.eventcomponent.EventComponentServiceApplication;
import com.eg.egsc.scp.eventcomponent.mq.service.ProcessTrigger;
import com.eg.egsc.scp.eventcomponent.mq.vo.EventMsgVo;
import com.eg.egsc.scp.eventcomponent.service.InstanceClassTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author shiweisen
 * @since 2018-01-19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EventComponentServiceApplication.class })
public class ProcessTriggerTest {

    private static final Logger logger = LoggerFactory.getLogger(ProcessTriggerTest.class);

    @Autowired
    private ProcessTrigger processTrigger;

    @Test
    public void processEventMsg() {
        try {
            EventMsgVo eventMsgVo = InstanceClassTest.getInstance().newEventMsgVo();
            processTrigger.processEventMsg(eventMsgVo);
        } catch (AmqpConnectException e) {
            logger.error(e.getMessage());
        }
    }

    @Test
    public void processTriggerResult() {
        try {
            EventMsgVo eventMsgVo = InstanceClassTest.getInstance().newEventMsgVo();
            processTrigger.processTriggerResult(eventMsgVo);
        } catch (AmqpConnectException e) {
            logger.error(e.getMessage());
        }
    }

}
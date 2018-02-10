/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.mq;

import com.eg.egsc.common.component.utils.JsonUtil;
import com.eg.egsc.scp.eventcomponent.EventComponentServiceApplication;
import com.eg.egsc.scp.eventcomponent.mq.constants.EventMsgConstant;
import com.eg.egsc.scp.eventcomponent.mq.sender.Sender;
import com.eg.egsc.scp.eventcomponent.mq.vo.EventMsgVo;
import com.eg.egsc.scp.eventcomponent.service.InstanceClassTest;
import com.eg.egsc.scp.eventcomponent.utils.EcStringUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wangjun
 * @create 2017-12-19 9:04
 * @description：发送mq消息的测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EventComponentServiceApplication.class })
public class SenderMqTest {

    private static final Logger logger = LoggerFactory.getLogger(SenderMqTest.class);

    @Autowired
    private Sender sender;

    @Test
    public void testSendMsg(){
        try {
            sender.sendmsg(EcStringUtils.generateUuid(),EventMsgConstant.SEND_TOPIC_CAMERA,"000001","222CAMERA");
        } catch (AmqpConnectException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 模拟其他网关发布消息
     */
    @Test
    public void testDoorSendMsg(){
        EventMsgVo eventMsgVo = InstanceClassTest.getInstance().newEventMsgVo();
        //此处是json格式字符串
        String jsonStr = JsonUtil.toJson(eventMsgVo);
        MessageProperties properties = new MessageProperties();
        properties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        Message newMsg = new Message(jsonStr.getBytes(), properties);
        try {
            sender.sendMessage("MSG_INBOUND_QUEUE",newMsg);
        } catch (AmqpConnectException e) {
            logger.error(e.getMessage());
        }

    }

}

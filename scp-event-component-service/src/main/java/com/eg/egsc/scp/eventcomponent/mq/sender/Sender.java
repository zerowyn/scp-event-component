/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.mq.sender;

import com.eg.egsc.common.component.utils.JsonUtil;
import com.eg.egsc.framework.client.mq.BaseIotbusMqSender;
import com.eg.egsc.scp.eventcomponent.mq.constants.EventMsgConstant;
import com.eg.egsc.scp.eventcomponent.mq.vo.EventMsgVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author wangjun
 * @create 2017-12-27 9:17
 * @description：
 */
@Component
public class Sender extends BaseIotbusMqSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Value("${delivery.message.appID}")
    private String appId;

    @Value("${delivery.message.token}")
    private String token;

    @Value("${send.iot.queue}")
    private String sendIOTQueue;

    /**
     * 推送消息到组件的方法
     * @param topic 在EventMsgConstant已定义好发到各组件的topic常量，如停车场为SEND_TOPIC_PARKING
     * @param payLoad 发往组件的消息内容
     */
    public void sendmsg(String messageID,String topic,String deviceID,Object payLoad){
        EventMsgVo eventMsgVo = getDefaultMsgVo();
        eventMsgVo.setMessageID(messageID);
        eventMsgVo.setTopic(topic);
        eventMsgVo.setDeviceID(deviceID);
        eventMsgVo.setPayload(payLoad);
        eventMsgVo.setEventTypeID(EventMsgConstant.getTopicEventTypeMap().get(topic));

        //此处是json格式字符串
        String jsonStr = JsonUtil.toJson(eventMsgVo);
        MessageProperties properties = new MessageProperties();
        properties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        Message newMsg = new Message(jsonStr.getBytes(), properties);

        sendMessage(sendIOTQueue,newMsg);
        LOGGER.info("Send message to {} :  {}",eventMsgVo.getTopic(), JsonUtil.toJson(eventMsgVo));
    }

    /**
     * 发送消息到其他组件的消息格式默认参数
     * @return
     */
    private EventMsgVo getDefaultMsgVo(){
        EventMsgVo msgVo = new EventMsgVo();
        msgVo.setAppID(appId);
        msgVo.setToken(token);
        msgVo.setReplyFlag(EventMsgConstant.REPLY_FLAG_NO);
        msgVo.setTimestamp(DateFormat.getDateTimeInstance().format(new Date()));
        msgVo.setReplyToQueue(EventMsgConstant.SEND_TRIGGER_RESULT);

        return msgVo;
    }
}

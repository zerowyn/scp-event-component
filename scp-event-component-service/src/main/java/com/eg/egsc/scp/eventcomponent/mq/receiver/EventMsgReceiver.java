/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.mq.receiver;


import com.eg.egsc.common.component.utils.JsonUtil;
import com.eg.egsc.scp.eventcomponent.mq.constants.EventMsgConstant;
import com.eg.egsc.scp.eventcomponent.mq.service.ProcessTrigger;
import com.eg.egsc.scp.eventcomponent.mq.vo.EventMsgVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * 监听物联网总线所有上报事件消息和其他组件返回联动执行结果消息
 *
 * @author wangjun
 * @since  2017-12-08
 */
@Component
@RabbitListener(queues = "${listener.gateway.queue}", containerFactory="iotbusFactory")
public class EventMsgReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventMsgReceiver.class);

    @Autowired
    private ProcessTrigger processTrigger;

    @Value("${delivery.message.appID}")
    private String appId;

    @RabbitHandler
    public void receiveMessage(Map mapMsg) {
        String msg = JsonUtil.toJsonString(mapMsg);
        LOGGER.info("Receive Mq message : {}",msg);

        try {
            EventMsgVo msgVo = JsonUtil.parser(msg, EventMsgVo.class);
            LOGGER.info("Triggger info for Mq message: eventTypeID : {} , deviceID : {}",msgVo.getEventTypeID(),
                    msgVo.getDeviceID());
            if(null == msgVo){
                LOGGER.warn("Mq message transform failed");
                return ;
            }
            //如果是自己发的消息，过滤掉
            if(appId.equalsIgnoreCase(msgVo.getAppID())){
                return ;
            }
            //判断收到消息是联动执行结果还是上报事件
            if (EventMsgConstant.getTopicEventTypeMap().get(EventMsgConstant.SEND_TRIGGER_RESULT).equals(msgVo.getEventTypeID())) {
                processTrigger.processTriggerResult(msgVo);
            }else{
                processTrigger.processEventMsg(msgVo);
            }
        }catch (Exception e){
            LOGGER.error("process Mq message failed!! cause: {}", e);
        }
    }


}

/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.mq.service;

import com.eg.egsc.common.component.utils.JsonUtil;
import com.eg.egsc.scp.eventcomponent.mapper.entity.Trigger;
import com.eg.egsc.scp.eventcomponent.mapper.entity.TriggerDetail;
import com.eg.egsc.scp.eventcomponent.mq.sender.Sender;
import com.eg.egsc.scp.eventcomponent.mq.vo.EventMsgVo;
import com.eg.egsc.scp.eventcomponent.mq.vo.TriggerDistributeVO;
import com.eg.egsc.scp.eventcomponent.mq.vo.TriggerResultVO;
import com.eg.egsc.scp.eventcomponent.service.TriggerDetailService;
import com.eg.egsc.scp.eventcomponent.service.TriggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * @author wangjun
 * @create 2017-12-20 9:33
 * @description： 处理消息 分发联动规则
 */
@Component
public class ProcessTrigger {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessTrigger.class);

    @Autowired
    private TriggerService triggerServiceImpl;

    @Autowired
    private TriggerDetailService triggerDetailServiceImpl;

    @Autowired
    private Sender sender;

    /**
     * 处理上报事件的消息
     */
    public void processEventMsg(EventMsgVo eventMsgVo) {
        Integer eventTypeID = eventMsgVo.getEventTypeID();
        String eventSourceCode = eventMsgVo.getDeviceID();
        if(ObjectUtils.isEmpty(eventTypeID)){
            LOGGER.warn("eventTypeID can not be null !!");
            return ;
        }
        if(ObjectUtils.isEmpty(eventSourceCode)){
            LOGGER.warn("eventSourceCode can not be null !!");
            return ;
        }
        Map<TriggerDistributeVO, String> voStringMap = getDistributeInfo(eventTypeID, eventSourceCode, null);
        if(null!= voStringMap && voStringMap.size()>0){
            //循环发送消息到各组件
            for(Map.Entry<TriggerDistributeVO, String> entry:voStringMap.entrySet()){
                TriggerDistributeVO triggerDistributeVO = entry.getKey();
                triggerDistributeVO.setEventInfo(eventMsgVo.getPayload());

                sender.sendmsg(eventMsgVo.getMessageID(),entry.getValue(),triggerDistributeVO.getEventSourceCode(),triggerDistributeVO);
            }
        }

    }

    /**
     * 处理返回联动执行结果
     */
    public void processTriggerResult(EventMsgVo eventMsgVo){
        String payload = JsonUtil.toJsonString(eventMsgVo.getPayload());
        TriggerResultVO triggerResultVO = JsonUtil.parser(payload, TriggerResultVO.class);
        if(null == triggerResultVO||!triggerResultVO.isSuccess()){
            //组件处理消息失败
            LOGGER.warn("component {} : process trigger failed,messageID: {}",eventMsgVo.getAppID(),
                    eventMsgVo.getMessageID());
            return ;
        }
        if(!triggerResultVO.isHasNext()) {
            //为空 或者执行完毕，没有下一条
            LOGGER.info("trigger process successfully completed!! messageID: {} ",eventMsgVo.getMessageID());
            return ;
        }

        Map<TriggerDistributeVO, String> voStringMap = getDistributeInfo(triggerResultVO.getEventType(),
                triggerResultVO.getEventSourceCode(), triggerResultVO.getTriggerOrder());
        if(null != voStringMap && voStringMap.size()>0){
            //循环发送联动规则到各组件
            for(Map.Entry<TriggerDistributeVO, String> entry:voStringMap.entrySet()){
                TriggerDistributeVO triggerDistributeVO = entry.getKey();
                triggerDistributeVO.setEventInfo(eventMsgVo.getPayload());

                sender.sendmsg(eventMsgVo.getMessageID(),entry.getValue(), triggerDistributeVO.getEventSourceCode(),triggerDistributeVO);
            }
        }
    }

    private Map<TriggerDistributeVO,String> getDistributeInfo(Integer eventType, String eventSourceCode, Integer order) {
        Map<TriggerDistributeVO,String> result = new HashMap<>();
        TriggerDistributeVO triggerDistributeVO = new TriggerDistributeVO();

        Trigger trigger = triggerServiceImpl.queryTriggerByEventKey(eventType, eventSourceCode);
        if(null == trigger){
            return null ;
        }
        triggerDistributeVO.setEventType(trigger.getEventType());
        triggerDistributeVO.setEventSourceCode(trigger.getEventSourceCode());
        triggerDistributeVO.setSendTime(System.currentTimeMillis());

        //是否时序性联动，是时序性联动只返回一条联动信息
        if(trigger.getIsSequential()){
            //orde为null，说明是上报事件，拿第一条规则明细。否则是返回的联动执行结果，拿下一条
            order = order == null?1:order+1;
            TriggerDetail triggerDetail = getTriggerDetailByOrder(trigger.getUuid(), order);
            if(!ObjectUtils.isEmpty(triggerDetail)){
                triggerDistributeVO.setTriggerType(triggerDetail.getTriggerType());
                triggerDistributeVO.setTriggerOrder(triggerDetail.getOrder());
                triggerDistributeVO.setTriggerParams(triggerDetail.getTriggerParams());
                boolean hasNext = !ObjectUtils.isEmpty(getTriggerDetailByOrder(trigger.getUuid(),order+1));
                triggerDistributeVO.setHasNext(hasNext);
            }
            result.put(triggerDistributeVO,triggerDetail.getTo());
        }else{
            //非时序性联动，返回所有
            List<TriggerDetail> triggerDetailList = triggerDetailServiceImpl.queryTriggerDetailByTriggerId(trigger.getUuid());
            if(!ObjectUtils.isEmpty(triggerDetailList)){
                TriggerDistributeVO tempTrigger = new TriggerDistributeVO(triggerDistributeVO);
                triggerDetailList.forEach(triggerDetail -> {
                    tempTrigger.setTriggerType(triggerDetail.getTriggerType());
                    tempTrigger.setTriggerParams(triggerDetail.getTriggerParams());
                    tempTrigger.setHasNext(false);
                    result.put(tempTrigger,triggerDetail.getTo());
                });
            }
        }
        return result;
    }

    private TriggerDetail getTriggerDetailByOrder(String triggerId,Integer order){
        List<TriggerDetail> triggerDetailList = triggerDetailServiceImpl.queryTriggerDetailByTriggerId(triggerId);
        if(ObjectUtils.isEmpty(triggerDetailList)) {
            return new TriggerDetail();
        }
        Optional<TriggerDetail> triggerDetail1 = triggerDetailList.stream().filter(triggerDetail ->
                order.equals(triggerDetail.getOrder())).findFirst();
        return triggerDetail1.orElse(new TriggerDetail()) ;
    }
}

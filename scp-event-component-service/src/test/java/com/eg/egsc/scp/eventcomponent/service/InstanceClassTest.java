/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.service;

import com.eg.egsc.scp.eventcomponent.dto.DeleteEventDto;
import com.eg.egsc.scp.eventcomponent.dto.EventLogDto;
import com.eg.egsc.scp.eventcomponent.dto.FilterDto;
import com.eg.egsc.scp.eventcomponent.dto.OrderDto;
import com.eg.egsc.scp.eventcomponent.dto.PageQueryDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerDetailDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerLogDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerResultDto;
import com.eg.egsc.scp.eventcomponent.mapper.entity.Trigger;
import com.eg.egsc.scp.eventcomponent.mapper.entity.TriggerDetail;
import com.eg.egsc.scp.eventcomponent.mq.constants.EventMsgConstant;
import com.eg.egsc.scp.eventcomponent.mq.vo.EventMsgVo;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @Description
 * @Author shiweisen
 * @Date 2018/1/8 20:36
 */
public class InstanceClassTest {

    private static  InstanceClassTest instance = null ;

    private static final String EVENT_SOURCE_CODE = "301058FCDB0000EB0111" ;

    private String appID = "APP8075";
    private String token = "6c7d962861cf8c92";

    private InstanceClassTest(){}

    public static InstanceClassTest getInstance() {
        if(instance == null)
            instance = new InstanceClassTest();
        return instance;
    }

    public Trigger triggerInstance() {
        Trigger trigger = new Trigger();
        trigger.setUuid("21e81dc5363a4895839282cc0e2a81c3");
        trigger.setEventType(30000);
        trigger.setEventSourceCode(EVENT_SOURCE_CODE);
        trigger.setIsSequential(false);
        trigger.setCreator("Tom");
        trigger.setCourtUuid("Test123123");
        trigger.setCreateTime(new Date());
        trigger.setUpdateTime(new Date());
        trigger.setCreateUser("Tester1");
        trigger.setUpdateUser("Moon");
        return trigger;
    }


    public TriggerDetail triggerDetailInstance() {
        TriggerDetail triggerDetail = new TriggerDetail();
        triggerDetail.setUuid("12d5eff79d19474bb41d59e5717a8a6e");
        triggerDetail.setTriggerType("TRIGGER_TEST1");
        triggerDetail.setTriggerParams("{}");
        triggerDetail.setTo("MSG/EVENT/TEST");
        triggerDetail.setOrder(1);
        triggerDetail.setCourtUuid("");
        triggerDetail.setCreateTime(new Date());
        triggerDetail.setUpdateTime(new Date());
        triggerDetail.setCreateUser("Tester3");
        triggerDetail.setUpdateUser("Moon");
        return triggerDetail;
    }

    public EventLogDto newEventLogDto() {
        EventLogDto eventLogDto = new EventLogDto();
        eventLogDto.setUuid("991ac52d96704056b585a88a85f0b666");
        eventLogDto.setResourceName("广州厅");
        eventLogDto.setResourceCode("11231");
        eventLogDto.setEventType(30000);
        eventLogDto.setDeviceName("camera");
        eventLogDto.setDeviceCode(EVENT_SOURCE_CODE);
        eventLogDto.setCreator("TEST");
        eventLogDto.setContent("测试");
        eventLogDto.setEventLogId("7629d0f286e24dd6bf59b35a07449d66");
        eventLogDto.setStatus(2);
        eventLogDto.setExtend(new Object());
        Date now = new Date();
        eventLogDto.setEndTime(now);
        eventLogDto.setStartTime(now);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-5);
        Date myDate = calendar.getTime();
        eventLogDto.setCreateTime(myDate);
        eventLogDto.setUpdateTime(myDate);
        eventLogDto.setCreateUser("Tester2");
        eventLogDto.setUpdateUser("Tom");
        OrderDto orderDto = new OrderDto();
        orderDto.setSort("asc");
        orderDto.setOrderBy("eventLogId");
        eventLogDto.setExtend(orderDto);
        return eventLogDto;
    }

    public DeleteEventDto newDeleteEventDto(){
        DeleteEventDto deleteEventDto = new DeleteEventDto();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,-15);
        Date startTime = calendar.getTime();
        deleteEventDto.setStartTime(startTime);
        calendar.add(Calendar.MINUTE,20);
        Date endTime = calendar.getTime();
        deleteEventDto.setEndTime(endTime);
        return deleteEventDto;
    }

    public PageQueryDto newPageQueryDto(){
        PageQueryDto pageQueryDto = new PageQueryDto();
        pageQueryDto.setPageSize(10);
        pageQueryDto.setPageNo(1);
        OrderDto orderDto = new OrderDto();
        orderDto.setSort("asc");
        orderDto.setOrderBy("createTime");
        pageQueryDto.setOrder(orderDto);
        FilterDto filterDto = new FilterDto();
        filterDto.setItem("creator");
        filterDto.setOperator("eq");
        filterDto.setVal("TEST");
        filterDto.setSeparator("and");
        ArrayList<FilterDto> filters = new ArrayList<>();
        filters.add(filterDto);
        filterDto = new FilterDto();
        filterDto.setItem("eventType");
        filterDto.setOperator("eq");
        filterDto.setVal("30000");
        filterDto.setSeparator("or");
        filters.add(filterDto);
        pageQueryDto.setFilters(filters);
        return pageQueryDto;
    }

    public TriggerDto newTriggerDto() {
        TriggerDto triggerDto = new TriggerDto();
        triggerDto.setEventType(30000);
        triggerDto.setEventSourceCode(EVENT_SOURCE_CODE);
        triggerDto.setIsSequential(false);
        triggerDto.setCreator("TEST");
        triggerDto.setUuid("21e81dc5363a4895839282cc0e2a81c3");
        triggerDto.setCourtUuid("Test123123");
        triggerDto.setCreateUser("TesterApi");
        triggerDto.setCreateTime(new Date());
        triggerDto.setUpdateTime(new Date());
        triggerDto.setUpdateUser("MoonApi");
        List<TriggerDetailDto> triggerDetailDtos = new ArrayList<>();
        TriggerDetailDto triggerDetailDto = this.newTriggerDetailDto();
        triggerDetailDtos.add(triggerDetailDto);
        triggerDto.setTriggers(triggerDetailDtos);
        return triggerDto;
    }

    public TriggerDetailDto newTriggerDetailDto() {
        TriggerDetailDto triggerDetailDto = new TriggerDetailDto();
        triggerDetailDto.setBusinessId("businessId");
        triggerDetailDto.setExtAttributes(new HashMap<>());
        triggerDetailDto.setMethod("TRIGGER_TEST2");
        triggerDetailDto.setParams("{}");
        triggerDetailDto.setSourceSysId("sourceSysId");
        triggerDetailDto.setTargetSysId("targetSysId");
        triggerDetailDto.setTo("MSG/EVENT/TEST");
        triggerDetailDto.setOrder(1);
        triggerDetailDto.setUuid("12d5eff79d19474bb41d59e5717a8a6e");
        triggerDetailDto.setCourtUuid("");
        triggerDetailDto.setCreateUser("TesterApi1");
        triggerDetailDto.setCreateTime(new Date());
        triggerDetailDto.setUpdateTime(new Date());
        triggerDetailDto.setUpdateUser("GaryApi");
        return triggerDetailDto;
    }

    public TriggerLogDto newTriggerLogDto() {
        TriggerLogDto triggerLogDto = new TriggerLogDto();
        triggerLogDto.setCreator("env/test");
        triggerLogDto.setOrder((short)1);
        triggerLogDto.setTriggerLogId("testTriggerLogId100");
        triggerLogDto.setTriggerType("TRIGGER_TEST3");
        triggerLogDto.setEventLogId("7629d0f286e24dd6bf59b35a07449d66");
        triggerLogDto.setTriggerTime(new Date());
        triggerLogDto.setRetryCount(0);
        TriggerResultDto triggerResultDto = new TriggerResultDto() ;
        triggerResultDto.setFlag("success");
        triggerResultDto.setMessage("add a result");
        triggerLogDto.setTriggerResult(triggerResultDto);
        triggerLogDto.setUuid("a57d6e5d05034eeda68ace2ffadee766");
        triggerLogDto.setCourtUuid("a57d6e5d05034eeda68ace2ffadee799");
        triggerLogDto.setCreateUser("TesterApi2");
        triggerLogDto.setCreateTime(new Date());
        triggerLogDto.setUpdateTime(new Date());
        triggerLogDto.setUpdateUser("Cary");
        return triggerLogDto;
    }

    public EventMsgVo newEventMsgVo(){
        EventMsgVo eventMsgVo = new EventMsgVo();
        eventMsgVo.setAppID(appID);
        eventMsgVo.setToken(token);
        eventMsgVo.setReplyFlag(EventMsgConstant.REPLY_FLAG_NO);
        eventMsgVo.setTimestamp(DateFormat.getDateTimeInstance().format(new Date()));
        eventMsgVo.setReplyToQueue(EventMsgConstant.SEND_TRIGGER_RESULT);
        eventMsgVo.setMessageID(UUID.randomUUID().toString().replace("-",""));
        //以下内容需要指定
        eventMsgVo.setTopic(EventMsgConstant.SEND_TOPIC_DOORACS);
        eventMsgVo.setDeviceID("10052011586858ABCDEE");
        eventMsgVo.setPayload("测试66");
        eventMsgVo.setEventTypeID(30000);
        return  eventMsgVo;
    }

}

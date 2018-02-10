/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.service;

import com.eg.egsc.scp.eventcomponent.EventComponentServiceApplication;
import com.eg.egsc.scp.eventcomponent.common.OperatorEnum;
import com.eg.egsc.scp.eventcomponent.dto.*;

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
 * @PackageName com.eg.egsc.scp.eventcomponent.service
 * @Description
 * @Author chenhao
 * @Date 2017/12/14 15:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EventComponentServiceApplication.class })
@Transactional
@Rollback
public class TriggerLogServiceTest {


    @Autowired
    private TriggerLogService triggerLogServiceImpl;

    @Test
    public void saveTriggerLog() {
        TriggerLogDto triggerDto = InstanceClassTest.getInstance().newTriggerLogDto();
        triggerLogServiceImpl.saveTriggerLog(triggerDto) ;
    }

    @Test
    public void saveTriggerLogWithNull() {
        triggerLogServiceImpl.saveTriggerLog(null) ;
    }

    @Test
    public void updateTriggerLog() {
        TriggerLogDto triggerDto = InstanceClassTest.getInstance().newTriggerLogDto();
        TriggerLogDto resDto = triggerLogServiceImpl.saveTriggerLog(triggerDto) ;
        resDto.setUpdateUser("UpdateUser");
        resDto.setCreateUser("UpdateUser");
        triggerLogServiceImpl.updateTriggerLog(triggerDto);
    }

    @Test
    public void updateTriggerLogWithNull() {
        triggerLogServiceImpl.updateTriggerLog(null);
    }

    @Test
    public void updateTriggerLogWithNullTriggerLogId() {
        TriggerLogDto triggerDto = InstanceClassTest.getInstance().newTriggerLogDto();
        triggerDto.setTriggerLogId(null);
        triggerLogServiceImpl.updateTriggerLog(triggerDto) ;
    }

    @Test
    public void testGetTriggerLogPageNormal(){
        PageQueryDto pageQueryInfo = new PageQueryDto();
        pageQueryInfo.setPageSize(10);
        pageQueryInfo.setPageNo(1);
        triggerLogServiceImpl.getTriggerLogPage(pageQueryInfo) ;
    }

    @Test
    public void testGetTriggerLogPagePageWithFilterAndOrder(){
        saveTriggerLog();
        String item = "triggerType" ;
        String separator = "and" ;
        PageQueryDto pageQueryInfo = new PageQueryDto();
        pageQueryInfo.setPageSize(10);
        pageQueryInfo.setPageNo(1);
        List<FilterDto> filterInfos = new ArrayList<>();
        FilterDto filterInfo = new FilterDto();
        filterInfo.setItem(item);
        filterInfo.setOperator(OperatorEnum.EQ.getOrginOperator());
        filterInfo.setSeparator(separator);
        filterInfo.setVal("TRIGGER_TES1T");
        filterInfos.add(filterInfo) ;

        filterInfo = new FilterDto();
        filterInfo.setItem(item);
        filterInfo.setOperator(OperatorEnum.GT.getOrginOperator());
        filterInfo.setSeparator(separator);
        filterInfo.setVal("TRIGGER_T2EST");
        filterInfos.add(filterInfo) ;

        filterInfo = new FilterDto();
        filterInfo.setItem(item);
        filterInfo.setOperator(OperatorEnum.GTE.getOrginOperator());
        filterInfo.setSeparator(separator);
        filterInfo.setVal("TRIGGER_3TEST");
        filterInfos.add(filterInfo) ;

        filterInfo = new FilterDto();
        filterInfo.setItem(item);
        filterInfo.setOperator(OperatorEnum.LT.getOrginOperator());
        filterInfo.setSeparator(separator);
        filterInfo.setVal("TRIGGER2_TEST");
        filterInfos.add(filterInfo) ;

        filterInfo = new FilterDto();
        filterInfo.setItem(item);
        filterInfo.setOperator(OperatorEnum.LTE.getOrginOperator());
        filterInfo.setSeparator(separator);
        filterInfo.setVal("TRIGGER1_TEST");
        filterInfos.add(filterInfo) ;

        filterInfo = new FilterDto();
        filterInfo.setItem(item);
        filterInfo.setOperator(OperatorEnum.NEQ.getOrginOperator());
        filterInfo.setSeparator(separator);
        filterInfo.setVal("TRIGGER3_TEST");
        filterInfos.add(filterInfo) ;

        filterInfo = new FilterDto();
        filterInfo.setItem(item);
        filterInfo.setOperator(OperatorEnum.LIKE.getOrginOperator());
        filterInfo.setSeparator(separator);
        filterInfo.setVal("TRIGGER4_TEST");
        filterInfos.add(filterInfo) ;

        filterInfo = new FilterDto();
        filterInfo.setItem("order");
        filterInfo.setOperator(OperatorEnum.LIKE.getOrginOperator());
        filterInfo.setSeparator(separator);
        filterInfo.setVal("1");
        filterInfos.add(filterInfo) ;

        pageQueryInfo.setFilters(filterInfos);

        OrderDto orderInfo = new OrderDto();
        orderInfo.setSort("desc");
        orderInfo.setOrderBy("triggerTime");
        pageQueryInfo.setOrder(orderInfo);
        triggerLogServiceImpl.getTriggerLogPage(pageQueryInfo) ;
    }

    @Test
    public void testGetTriggerLogPagePageInfoInvalidl(){
        PageQueryDto pageQueryInfo = new PageQueryDto();
        pageQueryInfo.setPageSize(0);
        pageQueryInfo.setPageNo(1);
        triggerLogServiceImpl.getTriggerLogPage(pageQueryInfo) ;
    }

    @Test
    public void testDeleteTriggerLogByTriggerTime(){
        triggerLogServiceImpl.deleteTriggerLogByTriggerTime(1515143520000L,1515229920000L);
    }

    @Test
    public void testDeleteTriggerLogByNullTriggerTime(){
        triggerLogServiceImpl.deleteTriggerLogByTriggerTime(null,1515229920001L);
    }

    @Test
    public void testGetTriggerLogsByEventLogIds(){
        List<String> eventLogIds = new ArrayList<>();
        eventLogIds.add("7629d0f286e24dd6bf59b35a07449d66");
        triggerLogServiceImpl.getTriggerLogsByEventIds(eventLogIds);
    }

    @Test
    public void testGetTriggerLogsByNullEventLogIds(){
        triggerLogServiceImpl.getTriggerLogsByEventIds(null);
    }

    @Test

    public void testGetTriggerLogByTriggerLogId(){
        TriggerLogDto result = triggerLogServiceImpl.getTriggerLogByTriggerLogId("testTriggerLogId100");
        Assert.assertNull(result);
        TriggerLogDto triggerDto = InstanceClassTest.getInstance().newTriggerLogDto();
        triggerLogServiceImpl.saveTriggerLog(triggerDto) ;
        triggerLogServiceImpl.getTriggerLogByTriggerLogId("testTriggerLogId100");
    }

    @Test
    public void testGetTriggerLogByNullTriggerLogId(){
        triggerLogServiceImpl.getTriggerLogByTriggerLogId(null);
    }

    @Test
    public void testDeleteTriggerLogById(){
        TriggerLogDto triggerDto = InstanceClassTest.getInstance().newTriggerLogDto();
        triggerLogServiceImpl.saveTriggerLog(triggerDto) ;
        triggerLogServiceImpl.deleteTriggerLogById(triggerDto.getUuid());
    }

}

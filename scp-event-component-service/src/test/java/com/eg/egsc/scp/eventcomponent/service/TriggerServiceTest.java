/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.service;

import com.eg.egsc.framework.paging.PageInfo;
import com.eg.egsc.scp.eventcomponent.EventComponentServiceApplication;
import com.eg.egsc.scp.eventcomponent.common.OperatorEnum;
import com.eg.egsc.scp.eventcomponent.dto.*;
import com.eg.egsc.scp.eventcomponent.mapper.entity.Trigger;
import com.eg.egsc.scp.eventcomponent.mapper.entity.TriggerDetail;

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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EventComponentServiceApplication.class})
@Transactional
@Rollback
public class TriggerServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(TriggerServiceTest.class);

    @Autowired
    private TriggerService triggerServiceImpl;

    @Autowired
    private TriggerDetailService triggerDetailServiceImpl;

    private static final String EVENT_SOURCE_CODE_TEST = "301058FCDB0000EB0111";
    private static final int EVENT_TYPE_TEST = 30000;
    private static final String ITEM_CREATE_TIME = "createTime";
    private static final String SEPARATOR_AND = "and";

    @Test
    public void testSaveTrigger() {
        Trigger trigger = InstanceClassTest.getInstance().triggerInstance();
        List<TriggerDetail> triggerDetails = new ArrayList<>();
        TriggerDetail detail = InstanceClassTest.getInstance().triggerDetailInstance();
        triggerDetails.add(detail);
        Trigger res = triggerServiceImpl.saveTrigger(trigger, triggerDetails);
        logger.debug(res.toString());
        Assert.assertNotNull(res);
    }

    /**
     * 根据事件类型以及事件源编码查询联动规则
     */
    @Test
    public void testQueryTriggerByEventKey() {
        testSaveTrigger();
        Trigger trigger = triggerServiceImpl.queryTriggerByEventKey(EVENT_TYPE_TEST, EVENT_SOURCE_CODE_TEST);
        Assert.assertNotNull(trigger);
    }

    /**
     * 根据主键返回事件规则信息
     */
    @Test
    public void testQueryTriggerByPrimaryKey() {
        testSaveTrigger();
        Trigger trigger = triggerServiceImpl.queryTriggerByEventKey(EVENT_TYPE_TEST, EVENT_SOURCE_CODE_TEST);
        String id = trigger.getUuid();
        triggerServiceImpl.queryTriggerByPrimaryKey(id);
    }

    /**
     * 修改联动规则
     */
    @Test
    public void testUpdateTrigger() {
        testSaveTrigger();
        Trigger trigger = triggerServiceImpl.queryTriggerByEventKey(EVENT_TYPE_TEST, EVENT_SOURCE_CODE_TEST);
        trigger.setUpdateUser("updateMoon");
        ArrayList<TriggerDetail> triggerDetails = (ArrayList<TriggerDetail>) triggerDetailServiceImpl.queryTriggerDetailByTriggerId(trigger.getUuid());
        for (TriggerDetail detail : triggerDetails) {
            detail.setUpdateUser("updateMoon");
        }
        Trigger result = triggerServiceImpl.updateTrigger(trigger, triggerDetails);
        logger.debug(result.toString());
        Assert.assertNotNull(result);
    }

    /**
     * 删除联动信息
     */
    @Test
    public void testDeleteTriggerByPrimaryKey() {
        testSaveTrigger();
        List<String> triggerIds = new ArrayList<>();
        Trigger trigger = triggerServiceImpl.queryTriggerByEventKey(EVENT_TYPE_TEST, EVENT_SOURCE_CODE_TEST);
        String id = trigger.getUuid();
        triggerIds.add(id);
        triggerServiceImpl.deleteTriggerByPrimaryKey(triggerIds);
        Trigger res = triggerServiceImpl.queryTriggerByPrimaryKey(id);
        Assert.assertNull(res);
    }

    /**
     * 删除联动信息
     */
    @Test
    public void testDeleteByTrigger() {
        Trigger trigger = triggerServiceImpl.queryTriggerByEventKey(EVENT_TYPE_TEST, EVENT_SOURCE_CODE_TEST);
        if (trigger != null) {
            triggerServiceImpl.deleteByTrigger(trigger);
            Trigger res = triggerServiceImpl.queryTriggerByPrimaryKey(trigger.getUuid());
            Assert.assertNull(res);
        }
    }


    @Test
    public void testHasTrigger() {
        testSaveTrigger();
        Boolean flag = triggerServiceImpl.hasTrigger(EVENT_TYPE_TEST, EVENT_SOURCE_CODE_TEST);
        Assert.assertEquals(true, flag);

    }


    /**
     * 分页查询联动规则
     */
    @Test
    public void testQueryTriggerPageNormal() {
        PageQueryDto pageQueryDto = new PageQueryDto();
        pageQueryDto.setPageNo(1);
        pageQueryDto.setPageSize(5);
        PageInfo<TriggerDto> pageInfo = triggerServiceImpl.queryTriggerPage(pageQueryDto);
        Assert.assertNotNull(pageInfo);

    }

    @Test
    public void testQueryTriggerPageWithFilterAndOrder(){
        testSaveTrigger();
        PageQueryDto pageQueryDto = new PageQueryDto();
        pageQueryDto.setPageNo(1);
        pageQueryDto.setPageSize(5);
        List<FilterDto> filterInfos = new ArrayList<>();
        FilterDto filterInfo = newFilterDto("eventSourceCode",OperatorEnum.EQ.getOrginOperator(),SEPARATOR_AND,EVENT_SOURCE_CODE_TEST);
        filterInfos.add(filterInfo);

        filterInfo =newFilterDto("isSequential",OperatorEnum.EQ.getOrginOperator(),SEPARATOR_AND,"true");
        filterInfos.add(filterInfo);

        filterInfo = newFilterDto(ITEM_CREATE_TIME,OperatorEnum.EQ.getOrginOperator(),SEPARATOR_AND,"2017-11-22 12:11:10");
        filterInfos.add(filterInfo);

        pageQueryDto.setFilters(filterInfos);

        OrderDto orderInfo = new OrderDto();
        orderInfo.setSort("desc");
        orderInfo.setOrderBy(ITEM_CREATE_TIME);
        pageQueryDto.setOrder(orderInfo);
        triggerServiceImpl.queryTriggerPage(pageQueryDto);
    }



    @Test
    public void testQueryTriggerPageWithFilterAndOrderTimeError(){
        try{
            PageQueryDto pageQueryDto = new PageQueryDto();
            pageQueryDto.setPageNo(1);
            pageQueryDto.setPageSize(5);
            List<FilterDto> filterInfos = new ArrayList<>();
            FilterDto filterInfo = newFilterDto(ITEM_CREATE_TIME,OperatorEnum.EQ.getOrginOperator(),SEPARATOR_AND,"2017-13-99:");
            filterInfos.add(filterInfo);
            pageQueryDto.setFilters(filterInfos);
            triggerServiceImpl.queryTriggerPage(pageQueryDto);

        }catch (Exception e){
            logger.error(e.getMessage());
        }

    }

    @Test
    public void testQueryTriggerPageWithFilterAndOrderOperatorError(){
        try{
            PageQueryDto pageQueryDto = new PageQueryDto();
            pageQueryDto.setPageNo(1);
            pageQueryDto.setPageSize(5);
            List<FilterDto> filterInfos = new ArrayList<>();
            FilterDto filterInfo = newFilterDto(ITEM_CREATE_TIME,"Q",SEPARATOR_AND,"2017-08-09");
            filterInfos.add(filterInfo);
            pageQueryDto.setFilters(filterInfos);
            triggerServiceImpl.queryTriggerPage(pageQueryDto);

        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    private FilterDto newFilterDto(String item,String operator,String separator,String val){
        FilterDto filterInfo = new FilterDto();
        filterInfo.setItem(item);
        filterInfo.setOperator(operator);
        filterInfo.setSeparator(separator);
        filterInfo.setVal(val);
        return filterInfo;

    }

}

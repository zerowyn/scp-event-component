/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.client;

import com.eg.egsc.common.component.utils.JsonUtil;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.scp.eventcomponent.EventComponentServiceApplication;
import com.eg.egsc.scp.eventcomponent.dto.EventLogDto;
import com.eg.egsc.scp.eventcomponent.dto.PageQueryDto;
import com.eg.egsc.scp.eventcomponent.mapper.entity.EventLog;
import com.eg.egsc.scp.eventcomponent.service.EventLogService;
import com.eg.egsc.scp.eventcomponent.service.InstanceClassTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 事件日志client层测试类
 *
 * @author shiweisen
 * @since 2018-01-16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EventComponentServiceApplication.class })
public class EventLogClientTest {

    private static final Logger logger = LoggerFactory.getLogger(EventLogClientTest.class) ;

    @Autowired
    private EventLogClient eventLogClientImpl;

    @Autowired
    private EventLogService eventLogServiceImpl;

    /**
     * 测试修改方法
     * 数据库中有eventLogId = 999777,所以是update
     */
    @Test
    public void updateLog()  {
        EventLogDto eventLogDto = InstanceClassTest.getInstance().newEventLogDto();
        eventLogClientImpl.saveOrUpdateEventLog(eventLogDto);
        EventLog eventLog = eventLogServiceImpl.selectByEventLogId(eventLogDto.getEventLogId());
        eventLogDto.setUuid(eventLog.getUuid());
        eventLogDto.setCreator("UpdateClient");
        eventLogClientImpl.saveOrUpdateEventLog(eventLogDto);
        eventLogServiceImpl.deleteEventLogByEventLogId(eventLogDto.getEventLogId());
    }

    /**
     * 测试新增方法
     * 数据库中没有eventLogId = '',所以是insert
     */
    @Test
    public void saveLog()  {
        EventLogDto eventLogDto = InstanceClassTest.getInstance().newEventLogDto();
        eventLogClientImpl.saveOrUpdateEventLog(eventLogDto);
        PageQueryDto dto = InstanceClassTest.getInstance().newPageQueryDto();
        ResponseDto responseDto = eventLogClientImpl.queryEventLog(dto);
        logger.debug(JsonUtil.toJsonString(responseDto));
        Assert.assertNotNull(responseDto.getData());
        eventLogServiceImpl.deleteEventLogByEventLogId(eventLogDto.getEventLogId());
    }

    /**
     * 测试分页查询方法
     * creator = 'admin' or eventType = '30000' order by id asc
     */
    @Test
    public void query() {
        PageQueryDto dto = InstanceClassTest.getInstance().newPageQueryDto();
        ResponseDto responseDto = eventLogClientImpl.queryEventLog(dto);
        logger.debug(JsonUtil.toJsonString(responseDto));
    }


}

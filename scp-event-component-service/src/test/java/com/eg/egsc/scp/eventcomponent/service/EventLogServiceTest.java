/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.service;

import com.eg.egsc.framework.paging.PageInfo;
import com.eg.egsc.scp.eventcomponent.EventComponentServiceApplication;
import com.eg.egsc.scp.eventcomponent.dto.*;
import com.eg.egsc.scp.eventcomponent.mapper.entity.EventLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EventComponentServiceApplication.class })
@Transactional
@Rollback
public class EventLogServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(EventLogServiceTest.class) ;

    @Autowired
    private EventLogService eventLogServiceImpl;

    /**
     *   测试保存方法
     */
    @Test
    public void saveEventLog(){
        EventLogDto eventLogDto = InstanceClassTest.getInstance().newEventLogDto();
        eventLogServiceImpl.insert(eventLogDto);
    }
    /**
     *  测试修改方法
     */
    @Test
    public void updateEventLog(){
        saveEventLog();
        EventLog eventLog = eventLogServiceImpl.selectByEventLogId("7629d0f286e24dd6bf59b35a07449d66");
        EventLogDto eventLogDto = InstanceClassTest.getInstance().newEventLogDto();
        eventLogDto.setUuid(eventLog.getUuid());
        eventLogDto.setUpdateUser("UpdateSer");
        eventLogServiceImpl.updateEventLog(eventLogDto);
    }
    /**
     *   根据id查询eventLog
     */
    @Test
    public void selectByEventLogId(){
        saveEventLog();
        EventLog eventLog = eventLogServiceImpl.selectByEventLogId("7629d0f286e24dd6bf59b35a07449d66");
        logger.debug(eventLog.toString());
    }
    /**
     *  条件查询
     */
    @Test
    public void deleteEventLog(){
        DeleteEventDto deleteEventDto = InstanceClassTest.getInstance().newDeleteEventDto();
        eventLogServiceImpl.deleteEventLog(deleteEventDto);
    }

    @Test
    public void deleteEventLogWithNull(){
        eventLogServiceImpl.deleteEventLog(null);
    }

    @Test
    public void deleteEventLogById(){
        EventLogDto eventLogDto = InstanceClassTest.getInstance().newEventLogDto();
        eventLogServiceImpl.insert(eventLogDto);
        eventLogServiceImpl.deleteEventLogByEventLogId(eventLogDto.getEventLogId());
    }

    @Test
    public void selectByExampleWithRowbounds(){
        PageQueryDto pageQueryDto = InstanceClassTest.getInstance().newPageQueryDto();
        PageInfo<EventLogDto> result = eventLogServiceImpl.selectByExampleWithRowbounds(pageQueryDto);
        List<EventLogDto> rows = result.getRows();
        if(result.getRows() != null && result.getRows().size()>0){
            for (EventLogDto row : rows) {
                logger.info(row.toString());
            }
        }

    }

}

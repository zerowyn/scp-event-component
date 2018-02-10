/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.api;

import com.eg.egsc.framework.client.dto.RequestDto;
import com.eg.egsc.scp.eventcomponent.dto.EventLogDto;
import com.eg.egsc.scp.eventcomponent.dto.PageQueryDto;

import org.junit.Test;


public class EventLogApiTest extends BaseApiTest{

    /**
     * 测试分页查询方法
     * creator = 'TEST' or eventType = '30000' order by createTime asc
     */
    @Test
    public void query() {
        saveLog();
        RequestDto<PageQueryDto> requestDto = InstanceApiTest.getInstance().newMockQueryEventDto();
        mockMvcPost(requestDto,"/api/eventlog/getEventLog");
    }

    /**
     * 测试新增方法
     */
    @Test
    public void saveLog()  {
        RequestDto<EventLogDto> requestDto = InstanceApiTest.getInstance().newMockEventLogDto();
        mockMvcPost(requestDto,"/api/eventlog/insertEventLog");
    }

    /**
     * 测试新增方法
     */
    @Test
    public void saveLogWithStatusNull()  {
        RequestDto<EventLogDto> requestDto = InstanceApiTest.getInstance().newMockEventLogDto();
        requestDto.getData().setStatus(null);
        mockMvcPost(requestDto,"/api/eventlog/insertEventLog");
    }

}

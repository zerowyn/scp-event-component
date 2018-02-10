/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.api;

import com.eg.egsc.framework.client.dto.Header;
import com.eg.egsc.framework.client.dto.RequestDto;
import com.eg.egsc.scp.eventcomponent.dto.DeleteEventDto;
import com.eg.egsc.scp.eventcomponent.dto.DictItemDto;
import com.eg.egsc.scp.eventcomponent.dto.EventLogDto;
import com.eg.egsc.scp.eventcomponent.dto.PageQueryDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerDelDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerLogDto;
import com.eg.egsc.scp.eventcomponent.service.InstanceClassTest;
import java.util.List;

/**
 * @Description
 * @Author shiweisen
 * @Date 2018/1/8 20:36
 */
public class InstanceApiTest {

    private static InstanceApiTest instance = null ;

    private InstanceApiTest(){}

    public static InstanceApiTest getInstance() {
        if(instance == null){
            instance = new InstanceApiTest();
        }
        return instance;
    }

    public Header newMockHeader(){
        Header header = new Header();
        header.setBusinessId("testid");
        header.setSourceSysId("env/test");
        header.setTargetSysId("13214");
        header.setContentType("json");
        header.setCharset("utf-8");
        header.setCreateTimestamp(Long.parseLong("1512109614834"));
        return header ;
    }

    public RequestDto<EventLogDto> newMockEventLogDto() {
        RequestDto requestDto = new RequestDto();
        EventLogDto eventLogDto = InstanceClassTest.getInstance().newEventLogDto();
        requestDto.setHeader(newMockHeader());
        requestDto.setData(eventLogDto);
        return requestDto;
    }

    public RequestDto<PageQueryDto> newMockQueryEventDto() {
        RequestDto requestDto = new RequestDto();
        PageQueryDto queryEventDto = InstanceClassTest.getInstance().newPageQueryDto();
        requestDto.setHeader(newMockHeader());
        requestDto.setData(queryEventDto);
        return requestDto;
    }

    public RequestDto<DeleteEventDto> newMockDeleteEventDto() {
        RequestDto requestDto = new RequestDto();
        DeleteEventDto deleteEventDto = InstanceClassTest.getInstance().newDeleteEventDto();
        requestDto.setHeader(newMockHeader());
        requestDto.setData(deleteEventDto);
        return requestDto;
    }

    public RequestDto<TriggerDto> newMockTriggerDto() {
        RequestDto requestDto = new RequestDto();
        TriggerDto triggerDto =  InstanceClassTest.getInstance().newTriggerDto();
        requestDto.setHeader(this.newMockHeader());
        requestDto.setData(triggerDto);
        return requestDto;
    }

    public RequestDto<TriggerDelDto> newMockTriggerDelDto(List<String> triggerIdList) {
        RequestDto requestDto = new RequestDto();
        TriggerDelDto epsTriggerDelDto = new TriggerDelDto();
        epsTriggerDelDto.setTriggerIdList(triggerIdList);
        requestDto.setHeader(this.newMockHeader());
        requestDto.setData(epsTriggerDelDto);
        return requestDto;
    }

    public RequestDto<PageQueryDto> newMockTriggerPageQueryDto() {
        RequestDto requestDto = new RequestDto();
        PageQueryDto pageQueryDto = InstanceClassTest.getInstance().newPageQueryDto();
        requestDto.setHeader(this.newMockHeader());
        requestDto.setData(pageQueryDto);
        return requestDto;
    }

    public RequestDto<TriggerLogDto> newMockTriggerLogDto()  {
        RequestDto requestDto = new RequestDto();
        TriggerLogDto triggerLogDto = InstanceClassTest.getInstance().newTriggerLogDto();
        requestDto.setHeader(this.newMockHeader());
        requestDto.setData(triggerLogDto);
        return requestDto;
    }

    public RequestDto<DictItemDto> newMockDictItemDto()  {
        RequestDto requestDto = new RequestDto();
        DictItemDto itemDto = new DictItemDto();
        requestDto.setHeader(this.newMockHeader());
        requestDto.setData(itemDto);
        return requestDto;
    }


}

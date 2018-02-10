/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.api;

import com.eg.egsc.framework.client.dto.RequestDto;
import com.eg.egsc.scp.eventcomponent.dto.PageQueryDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerLogDto;

import org.junit.Test;

/**
 *联动日志Api层测试类
 *
 * @author chenhao
 * @since  2017-12-14
 */
public class TriggerLogApiTest extends BaseApiTest {


    private static final String SAVE_TRIGGER_LOG_URL = "/api/triggerlog/insertTriggerLog";

    /**
     * 测试数据库不存在该数据情况下的保存方法
     * 保证triggerLogId是数据库中没有的
     */
    @Test
    public void testSave() {
        RequestDto<TriggerLogDto> requestDto = InstanceApiTest.getInstance().newMockTriggerLogDto();
        mockMvcPost(requestDto, SAVE_TRIGGER_LOG_URL);
    }

    /**
     * 测试要保存的数据为空时候的保存方法
     */
    @Test
    public void testSaveNull() {
        RequestDto<TriggerLogDto> requestDto = InstanceApiTest.getInstance().newMockTriggerLogDto();
        requestDto.setData(null);
        mockMvcPostError(requestDto, SAVE_TRIGGER_LOG_URL);
    }

    /**
     * 测试数据库已存在该数据情况下的保存方法，实际是更新数据
     */
    @Test
    public void saveOrUpdate() {
        testSave();
        RequestDto<TriggerLogDto> requestDto = InstanceApiTest.getInstance().newMockTriggerLogDto();
        requestDto.getData().setUpdateUser("UpdaterApi");
        mockMvcPost(requestDto, SAVE_TRIGGER_LOG_URL);
    }

    /**
     * 测试根据eventIds查询triggerLog方法
     */
    @Test
    public void getTriggerLogsByEventIds() {
        testSave();
        String eventIds = "7629d0f286e24dd6bf59b35a07449d66";
        mockMvcPost(eventIds, "/api/triggerlog/" + eventIds);
    }

    /**
     * 测试分页查询
     */
    @Test
    public void getTriggerLogsByCondition() {
        RequestDto<PageQueryDto> requestDto = new RequestDto<>();
        PageQueryDto pageQueryDto = new PageQueryDto();
        pageQueryDto.setPageNo(1);
        pageQueryDto.setPageSize(10);
        requestDto.setData(pageQueryDto);
        mockMvcPost(requestDto, "/api/triggerlog/listTriggerLogs");
    }

}

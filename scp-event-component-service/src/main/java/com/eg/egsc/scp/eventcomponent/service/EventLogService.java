/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.service;

import com.eg.egsc.framework.paging.PageInfo;
import com.eg.egsc.scp.eventcomponent.dto.DeleteEventDto;
import com.eg.egsc.scp.eventcomponent.dto.EventLogDto;
import com.eg.egsc.scp.eventcomponent.dto.PageQueryDto;
import com.eg.egsc.scp.eventcomponent.mapper.entity.EventLog;


/**
 * 事件日志
 *
 * @author xulei
 * @since 2017-12-8
 */
public interface EventLogService {

    /**
     *  分页查询事件日志
     *
     *@Param req 分页查询条件
     *@return 返回分页查询结果
     */
    PageInfo<EventLogDto> selectByExampleWithRowbounds(PageQueryDto req);

    /**
     * 按照时间范围删除事件日志
     *
     *@Param req
     *@return 返回删除成功的事件日志记录数
     */
    int deleteEventLog(DeleteEventDto req);

    /**
     * 保存事件日志
     *
     *@Param eventLogDto
     *@return 返回保存成功记录条数
     */
    int insert(EventLogDto record);

    /**
     * 更新事件日志
     *
     *@Param eventLogDto
     *@return 返回更新成功的记录条数
     */
    int updateEventLog(EventLogDto record);

    /**
     * 根据事件日志ID查询事件日志
     *
     *@Param eventLogId 事件日志ID
     *@return 返回事件日志查询结果
     */
    EventLog selectByEventLogId(String eventLogId);

    /**
     * 按照uuid删除事件日志
     *
     *@Param eventLogId 事件日志编码
     *@return 返回删除成功的事件日志记录数
     */
    int deleteEventLogByEventLogId(String eventLogId);

}

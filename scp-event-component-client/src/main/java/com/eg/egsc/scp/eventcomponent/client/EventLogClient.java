/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.client;

import com.eg.egsc.common.exception.CommonException;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.scp.eventcomponent.dto.EventLogDto;
import com.eg.egsc.scp.eventcomponent.dto.PageQueryDto;

/**
 * 提供的事件日志接口
 *
 * @author xulei
 * @since 2017-12-11
 */
public interface EventLogClient {

    /**
     * 事件日志不存在时新增数据，存在时更新数据
     *
     * @param logDto 要保存或者要更新的事件日志
     */
    void saveOrUpdateEventLog(EventLogDto logDto) throws CommonException;

    /**
     * 分页查询，根据查询条件查询事件日志
     *
     * @param logDto 查询事件日志的查询条件
     * @return 返回事件日志的查询结果
     */
    ResponseDto queryEventLog(PageQueryDto logDto) throws CommonException;
}

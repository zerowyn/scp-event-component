/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.client.impl;

import com.eg.egsc.common.exception.CommonException;
import com.eg.egsc.framework.client.core.BaseApiClient;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.scp.eventcomponent.client.EventLogClient;
import com.eg.egsc.scp.eventcomponent.dto.EventLogDto;
import com.eg.egsc.scp.eventcomponent.dto.PageQueryDto;
import org.springframework.stereotype.Component;


/**
 * 提供的事件日志的接口
 *
 * @author xulei
 * @since 2017-12-11
 */
@Component
public class EventLogClientImpl extends BaseApiClient implements EventLogClient {

    public EventLogClientImpl() {

    }

    public EventLogClientImpl(String gatewayUrl) {
        super(gatewayUrl);
    }

    @Override
    protected String getContextPath() {
        return "/scp-eventcomponent";
    }

    /**
     * 事件日志不存在时新增数据，存在时更新数据
     *
     * @param logDto 要保存或者要更新的事件日志
     */
    @Override
    public void saveOrUpdateEventLog(EventLogDto logDto) throws CommonException {
        post("/api/eventlog/insertEventLog", logDto);
    }

    /**
     * 分页查询，根据查询条件查询事件日志
     *
     * @param logDto 查询事件日志的查询条件
     * @return 返回事件日志的查询结果
     */
    @Override
    public ResponseDto queryEventLog(PageQueryDto logDto) throws CommonException {

        return post("/api/eventlog/getEventLog", logDto);
    }

}

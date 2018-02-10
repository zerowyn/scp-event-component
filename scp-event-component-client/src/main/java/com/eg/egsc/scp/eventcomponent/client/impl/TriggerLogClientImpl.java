/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.client.impl;

import com.eg.egsc.framework.client.core.BaseApiClient;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.scp.eventcomponent.client.TriggerLogClient;
import com.eg.egsc.scp.eventcomponent.dto.CommonECDto;
import com.eg.egsc.scp.eventcomponent.dto.PageQueryDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerLogDto;
import org.springframework.stereotype.Component;


/**
 * 提供的联动日志的接口
 *
 * @author chenhao
 * @since 2017-12-12
 */
@Component
public class TriggerLogClientImpl extends BaseApiClient implements TriggerLogClient {


    public TriggerLogClientImpl() {
    }

    public TriggerLogClientImpl(String gatewayUrl) {
        super(gatewayUrl);
    }

    @Override
    protected String getContextPath() {
        return "/scp-eventcomponent";
    }

    /**
     * 新增联动日志信息
     *
     * @param triggerLogDto 需要新增的联动日志信息
     * @return 返回新增结果
     */
    @Override
    public ResponseDto saveOrUpdate(TriggerLogDto triggerLogDto) {

       return post("/api/triggerlog/insertTriggerLog",triggerLogDto);
    }

    /**
     * 根据事件日志获取联动日志
     *
     * @param commonECDTO 事件日志信息
     * @return 返回对应的联动日志信息
     */
    @Override
    public ResponseDto getTriggerLogByEventIds(CommonECDto commonECDTO) {

        //参数在路径中体现
        return post("/api/triggerlog/"+commonECDTO.getMap().get("eventIds"),commonECDTO);
    }

    /**
     * 分页查询联动日志信息
     *
     * @param pageQueryDto 查询条件信息
     * @return 返回的查询结果
     */
    @Override
    public ResponseDto getTriggerLogByCondition(PageQueryDto pageQueryDto) {
        return post("/api/triggerlog/listTriggerLogs",pageQueryDto);
    }

}

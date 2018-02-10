/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.client;

import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.scp.eventcomponent.dto.CommonECDto;
import com.eg.egsc.scp.eventcomponent.dto.PageQueryDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerLogDto;

/**
 * 提供的联动日志的接口
 *
 * @author chenhao
 * @since 2017-12-12
 */
public interface TriggerLogClient {

    /**
     * 新增联动日志信息
     *
     * @param triggerLogDto 需要新增的联动日志信息
     * @return 返回新增结果
     */
    ResponseDto saveOrUpdate(TriggerLogDto triggerLogDto) ;

    /**
     * 根据事件日志获取联动日志
     * 将事件ID放入map中，key值为eventIds，多值以英文逗号分隔
     *
     * @param commonECDTO 事件日志信息
     * @return 返回对应的联动日志信息
     */
    ResponseDto getTriggerLogByEventIds(CommonECDto commonECDTO) ;

    /**
     * 分页查询联动日志信息
     *
     * @param pageQueryDto 查询条件信息
     * @return 返回的查询结果
     */
    ResponseDto getTriggerLogByCondition(PageQueryDto pageQueryDto);

}

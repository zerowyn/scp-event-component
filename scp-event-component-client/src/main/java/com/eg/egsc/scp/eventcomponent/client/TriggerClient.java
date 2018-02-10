/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.client;

import com.eg.egsc.common.exception.CommonException;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.scp.eventcomponent.dto.PageQueryDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerDelDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerDto;

/**
 * 提供的联动规则的接口
 *
 * @author tangsuwen
 * @since 2017-12-11
 */
public interface TriggerClient {

    /**
     * 新增联动规则信息
     *
     * @param epsTriggerDto 需要保存的联动规则信息
     */
    void saveTrigger(TriggerDto epsTriggerDto) throws CommonException;

    /**
     * 修改联动规则信息
     *
     * @param epsTriggerDto 需要修改的联动信息
     */
    void updateTrigger(TriggerDto epsTriggerDto) throws CommonException;

    /**
     * 根据Ids批量删除联动规则信息
     *
     * @param epsTriggerDelDto 需要删除的联动规则
     * @return 返回删除结果
     */
    void deleteTrigger(TriggerDelDto epsTriggerDelDto) throws CommonException;

    /**
     * 根据uuid查询联动规则信息
     *
     * @param epsTriggerDto 要查询的联动规则
     * @return 返回联动规则的详细信息
     */
    ResponseDto queryTriggerById(TriggerDto epsTriggerDto) throws CommonException;

    /**
     * 分页查询联动规则信息
     *
     * @param pageQueryDto 查询的联动规则的条件信息
     * @return 返回查询结果
     */
    ResponseDto queryTriggerPage(PageQueryDto pageQueryDto) ;
}

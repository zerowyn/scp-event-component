/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.service;

import com.eg.egsc.framework.paging.PageInfo;
import com.eg.egsc.scp.eventcomponent.dto.PageQueryDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerLogDto;

import java.util.List;

/**
 * 联动日志service接口
 *
 * @Author chenhao
 * @since 2017-12-12
 */
public interface TriggerLogService {

    /**
     * 保存联动日志
     *
     *@Param triggerLogDto 需保存的联动日志信息
     *@return 返回保存成功联动日志信息
     */
    TriggerLogDto saveTriggerLog(TriggerLogDto triggerLogDto) ;

    /**
     * 修改联动日志
     *
     *@Param triggerLogDto 需修改的联动日志信息
     *@return 返回修改成功后的联动日志
     */
    TriggerLogDto updateTriggerLog(TriggerLogDto triggerLogDto) ;

    /**
     * 根据事件ID查询联动日志
     *
     *@Param eventLogIds 需查询的联动事件日志ID值集合
     *@return 返回查询出的联动日志结果集
     */
    List<TriggerLogDto> getTriggerLogsByEventIds(List<String> eventIds) ;

    /**
     * 根据联动日志唯一标识查询联动日志
     *
     *@Param triggerLogId 联动事件日志ID值
     *@return 返回查询出的对应联动日志结果
     */
    TriggerLogDto getTriggerLogByTriggerLogId(String triggerLogId) ;

    /**
     * 分页查询联动日志
     *
     *@Param pageQuery 分页查询条件
     *@return 返回对应联动日志分页查询结果
     */
    PageInfo<TriggerLogDto> getTriggerLogPage(PageQueryDto pageQuery) ;

    /**
     * 根据联动时间删除联动日志
     *
     *@Param startTimestamp 开始时间
     *@Param endTimestamp 结束时间
     *@return
     */
    void deleteTriggerLogByTriggerTime(Long startTime,Long endTime) ;

    /**
     * 通过Id删除联动日志记录
     *
     *@Param uuid 开始时间
     *@return 返回删除的数量
     */
    int deleteTriggerLogById(String uuid);

}

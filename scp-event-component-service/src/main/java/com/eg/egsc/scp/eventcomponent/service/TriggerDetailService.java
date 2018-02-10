/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.service;

import com.eg.egsc.scp.eventcomponent.mapper.entity.TriggerDetail;

import java.util.List;

/**
 * 联动规则Service类
 *
 * @author tangsuwen
 * @since 2017-12-12
 */
public interface TriggerDetailService {

    /**
     * 保存联动明细
     *
     * @Param triggerDetails 需保存的联动规则数据集合
     * @return
     */
    void saveTriggerDetail(List<TriggerDetail> triggerDetails);

    /**
     * 根据联动规则ID删除联动明细
     *
     * @Param triggerId 联动规则ID值
     * @return
     */
    void deleteTriggerDetailByTriggerId(String triggerId);

    /**
     * 根据联动规则ID批量删除联动明细
     *
     * @Param triggerIds 需批量删除的联动规则ID值
     * @return
     */
    void deleteTriggerDetailByTriggerId(List<String> triggerIds);

    /**
     * 根据联动规则ID查询联动规则明细
     *
     * @Param triggerId 联动规则ID值
     * @return 返回查询出的所有联动规则明细结果
     */
    List<TriggerDetail> queryTriggerDetailByTriggerId(String triggerId);
}

/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.service;

import com.eg.egsc.framework.paging.PageInfo;
import com.eg.egsc.scp.eventcomponent.dto.PageQueryDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerDto;
import com.eg.egsc.scp.eventcomponent.mapper.entity.Trigger;
import com.eg.egsc.scp.eventcomponent.mapper.entity.TriggerDetail;

import java.util.List;

/**
 * 联动规则Service类
 *
 * @author tangsuwen
 * @since 2017-12-12
 */
public interface TriggerService {
    /**
     * 保存联动规则
     *
     * @Param trigger 需保存的联动规则信息
     * @Param triggerDetails 需保存的对应联动明细信息
     * @return 返回保存成功后的联动规则
     */
    Trigger saveTrigger(Trigger trigger, List<TriggerDetail> triggerDetails);

    /**
     * 根据事件类型以及事件源编码查询联动规则
     *
     * @Param eventType 事件类型值
     * @Param eventSourceCode 事件源编码值
     * @return 返回查询出的对应联动规则
     */
    Trigger queryTriggerByEventKey(Integer eventType, String eventSourceCode);

    /**
     * 根据联动规则唯一标识查询联动规则
     *
     * @Param uuid Trigger的主键uuid值
     * @return 返回查询出的对应联动规则
     */
    Trigger queryTriggerByPrimaryKey(String uuid);

    /**
     * 修改联动规则
     *
     * @Param trigger 需更新的联动规则信息
     * @Param triggerDetails 需更新的对应联动明细
     * @return 返回更新后的联动规则
     */
    Trigger updateTrigger(Trigger trigger, List<TriggerDetail> triggerDetails);

    /**
     * 批量删除联动规则
     *
     * @Param triggerIds 需删除的联动规则triggerId集合
     * @return
     */
    void deleteTriggerByPrimaryKey( List<String> triggerIds);

    /**
     * 删除联动规则
     *
     * @Param trigger 需删除的联动规则
     * @return
     */
    void deleteByTrigger(Trigger trigger);

    /**
     * 通过事件类型，事件源编码查询是否存在对应的联动规则
     *
     * @Param eventType 事件类型值
     * @Param eventSourceCode 事件源编码
     * @return 返回查询的对应联动规则是否存在
     */
    boolean hasTrigger(Integer eventType,String eventSourceCode) ;


    /**
     * 分页查询联动规则
     *
     * @Param pageQueryInfo 联动规则分页查询条件
     * @return 返回对应的联动规则分页结果
     */
    PageInfo<TriggerDto> queryTriggerPage(PageQueryDto pageQueryInfo);

}

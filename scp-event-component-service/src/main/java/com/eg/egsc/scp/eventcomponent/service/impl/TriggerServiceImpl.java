/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.service.impl;

import com.eg.egsc.common.component.utils.BeanUtils;
import com.eg.egsc.framework.paging.PageInfo;
import com.eg.egsc.framework.paging.PageUtils;
import com.eg.egsc.scp.eventcomponent.common.ExpireTimeConstant;
import com.eg.egsc.scp.eventcomponent.dto.FilterDto;
import com.eg.egsc.scp.eventcomponent.dto.PageQueryDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerDto;
import com.eg.egsc.scp.eventcomponent.mapper.TriggerMapper;
import com.eg.egsc.scp.eventcomponent.mapper.entity.Trigger;
import com.eg.egsc.scp.eventcomponent.mapper.entity.TriggerDetail;
import com.eg.egsc.scp.eventcomponent.mapper.entity.TriggerExample;
import com.eg.egsc.scp.eventcomponent.service.TriggerDetailService;
import com.eg.egsc.scp.eventcomponent.service.TriggerService;
import com.eg.egsc.scp.eventcomponent.utils.EcStringUtils;
import com.eg.egsc.scp.eventcomponent.utils.ValidateParamUtils;
import com.eg.egsc.scp.eventcomponent.utils.redis.annotation.RedisCacheEvict;
import com.eg.egsc.scp.eventcomponent.utils.redis.annotation.RedisCachePut;
import com.eg.egsc.scp.eventcomponent.utils.redis.annotation.RedisCacheable;
import com.eg.egsc.scp.eventcomponent.utils.reflect.ReflectCriteria;
import com.eg.egsc.scp.eventcomponent.utils.reflect.Tool;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 联动规则保存、查询、修改、删除
 *
 * @author tangsuwen
 * @since 2017-12-12
 */
@Service
public class TriggerServiceImpl implements TriggerService {

    private static final Logger logger = LoggerFactory.getLogger(TriggerServiceImpl.class);

    @Autowired
    private TriggerMapper triggerMapper;

    @Autowired
    private TriggerDetailService triggerDetailServiceImpl;

    /**
     * 保存联动规则
     *
     * @Param trigger 需保存的联动规则信息
     * @Param triggerDetails 需保存的对应联动明细信息
     * @return 返回保存成功后的联动规则
     */
    @Override
    @Transactional
    public Trigger saveTrigger(Trigger trigger, List<TriggerDetail> triggerDetails) {
        trigger.setUuid(EcStringUtils.generateUuid());
        this.triggerMapper.insert(trigger);
        final String triggerId = trigger.getUuid();
        if(null != triggerDetails && !triggerDetails.isEmpty()) {
            for (TriggerDetail triggerDetail : triggerDetails) {
                triggerDetail.setTriggerId(triggerId);
            }
            this.triggerDetailServiceImpl.saveTriggerDetail(triggerDetails);
        }
        return trigger;
    }

    /**
     * 根据事件类型以及事件源编码查询联动规则
     *
     * @Param eventType 事件类型值
     * @Param eventSourceCode 事件源编码值
     * @return 返回查询出的对应联动规则
     */
    @Override
    @RedisCacheable(key = "#eventType+#eventSourceCode",value = "CACHE.TRIGGER",expire = ExpireTimeConstant.EXPIRE_CACHE_TRIGGER )
    public Trigger queryTriggerByEventKey(Integer eventType, String eventSourceCode) {
        TriggerExample example = new TriggerExample();
        TriggerExample.Criteria criteria = example.createCriteria();
        criteria.andEventSourceCodeEqualTo(eventSourceCode);
        criteria.andEventTypeEqualTo(eventType);
        List<Trigger> triggers = triggerMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(triggers)) {
            return triggers.get(0);
        }
        return null;
    }

    /**
     * 根据联动规则唯一标识查询联动规则
     *
     * @Param uuid Trigger的主键uuid值
     * @return 返回查询出的对应联动规则
     */
    @Override
    public Trigger queryTriggerByPrimaryKey(String uuid) {
        return this.triggerMapper.selectByPrimaryKey(uuid);
    }

    /**
     * 修改联动规则
     *
     * @Param trigger 需更新的联动规则信息
     * @Param triggerDetails 需更新的对应联动明细
     * @return 返回更新后的联动规则
     */
    @Override
    @Transactional
    @RedisCachePut(key = "#trigger.eventType+#trigger.eventSourceCode", value = "CACHE.TRIGGER",expire =  ExpireTimeConstant.EXPIRE_CACHE_TRIGGER )
    public Trigger updateTrigger(Trigger trigger, List<TriggerDetail> triggerDetails) {
        this.triggerMapper.updateByPrimaryKeySelective(trigger);
        this.triggerDetailServiceImpl.deleteTriggerDetailByTriggerId(trigger.getUuid());
        if(null != triggerDetails && !triggerDetails.isEmpty()) {
            this.triggerDetailServiceImpl.saveTriggerDetail(triggerDetails);
        }
        return trigger;
    }

    /**
     * 删除联动规则
     *
     * @Param trigger 需删除的联动规则
     * @return
     */
    @Override
    @RedisCacheEvict(key = "#trigger.eventType+#trigger.eventSourceCode",value = "CACHE.TRIGGER")
    public void deleteByTrigger(Trigger trigger){
        triggerMapper.deleteByPrimaryKey(trigger.getUuid());
    }

    /**
     * 批量删除联动规则
     *
     * @Param triggerIds 需批量删除的联动规则集合
     * @return
     */
    @Override
    @Transactional
    public void deleteTriggerByPrimaryKey(List<String> triggerIds) {
        for (String id : triggerIds) {
            Trigger trigger = queryTriggerByPrimaryKey(id);
            if (trigger != null){
                this.deleteByTrigger(trigger);
            }
        }
        this.triggerDetailServiceImpl.deleteTriggerDetailByTriggerId(triggerIds);
    }

    /**
     * 通过事件类型，事件源编码查询是否存在对应的联动规则
     *
     * @Param eventType 事件类型值
     * @Param eventSourceCode 事件源编码
     * @return 返回查询的对应联动规则是否存在
     */
    @Override
    public boolean hasTrigger(Integer eventType, String eventSourceCode) {
        return queryTriggerByEventKey(eventType,eventSourceCode) != null;
    }

    /**
     * 分页查询联动规则
     *
     * @Param pageQueryInfo 联动规则分页查询条件
     * @return 返回对应的联动规则分页结果
     */
    @Override
    public PageInfo<TriggerDto> queryTriggerPage(PageQueryDto pageQueryInfo) {
        if(!ValidateParamUtils.isPageLegal(pageQueryInfo)){
            logger.info("Page params is invalid");
            return null ;
        }
        TriggerExample example = new TriggerExample();
        List<FilterDto> filters = pageQueryInfo.getFilters();
        if(CollectionUtils.isNotEmpty(filters)) {
            try {
                ReflectCriteria.invoke(example,filters,Trigger.class);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        if( pageQueryInfo.getOrder() != null && StringUtils.isNotEmpty(pageQueryInfo.getOrder().getSort())
                && StringUtils.isNotEmpty(pageQueryInfo.getOrder().getOrderBy())){
            example.setOrderByClause(Tool.humpToLine(pageQueryInfo.getOrder().getOrderBy())+" "+
                    pageQueryInfo.getOrder().getSort());
        }

        int pageNo = pageQueryInfo.getPageNo();
        int pageSize = pageQueryInfo.getPageSize();

        Long total =this.triggerMapper.countByExample(example);
        PageInfo<TriggerDto> pageResult = new PageInfo<>();
        pageResult.setCount(total);
        pageResult.setCurrentPage(pageNo);
        pageResult.setPageSize(pageSize);
        pageResult.setTotal(total);
        if(total > 0){
            RowBounds rowBounds = new RowBounds(PageUtils.getOffset(pageNo,pageSize), pageSize);
            List<Trigger> triggerList = this.triggerMapper.selectByExampleWithRowbounds(example, rowBounds);
            if(CollectionUtils.isNotEmpty(triggerList)) {
                List<TriggerDto> triggerDtoList = new ArrayList<>();
                TriggerDto triggerDto = null;
                for (Trigger trigger : triggerList) {
                    triggerDto = new TriggerDto();
                    BeanUtils.copyProperties(triggerDto, trigger);
                    triggerDto.setIsSequential(trigger.getIsSequential());
                    Integer eventType = trigger.getEventType();
                    triggerDto.setEventType(eventType);
                    triggerDto.setDeviceTypeName(EcStringUtils.qryDeviceTypeName(eventType.intValue()));
                    triggerDtoList.add(triggerDto);
                }
                pageResult.setRows(triggerDtoList);
            }
        }

        return pageResult;

    }


}

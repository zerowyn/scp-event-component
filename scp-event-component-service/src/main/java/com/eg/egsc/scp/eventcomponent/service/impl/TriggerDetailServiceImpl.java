/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.service.impl;

import com.eg.egsc.scp.eventcomponent.common.ExpireTimeConstant;
import com.eg.egsc.scp.eventcomponent.mapper.TriggerDetailMapper;
import com.eg.egsc.scp.eventcomponent.mapper.entity.TriggerDetail;
import com.eg.egsc.scp.eventcomponent.mapper.entity.TriggerDetailExample;
import com.eg.egsc.scp.eventcomponent.service.TriggerDetailService;
import com.eg.egsc.scp.eventcomponent.utils.EcStringUtils;
import com.eg.egsc.scp.eventcomponent.utils.redis.annotation.RedisCacheEvict;
import com.eg.egsc.scp.eventcomponent.utils.redis.annotation.RedisCacheable;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 联动明细保存、查询、删除
 *
 * @author tangsuwen
 * @since 2017-12-12
 */
@Service
public class TriggerDetailServiceImpl implements TriggerDetailService {

    private static final Logger logger = LoggerFactory.getLogger(TriggerDetailServiceImpl.class) ;

    @Autowired
    private TriggerDetailMapper triggerDetailMapper;

    /**
     * 批量保存联动明细
     *
     * @Param triggerDetails 需保存的联动明细数据集合
     * @return
     */
    @Override
    @Transactional
    public void saveTriggerDetail(List<TriggerDetail> triggerDetails) {
        logger.info("Save triggerDetail  ");
        for (TriggerDetail trigger : triggerDetails) {
            //先手动生成，以后是框架自动生成
            if (StringUtils.isEmpty(trigger.getUuid())){
                String uuid = EcStringUtils.generateUuid();
                trigger.setUuid(uuid);
            }
            this.triggerDetailMapper.insert(trigger);
        }
        logger.info("Save triggerDetail end ");
    }

    /**
     * 根据联动规则ID删除联动明细
     *
     * @Param triggerId 联动规则ID值
     * @return
     */
    @Override
    @RedisCacheEvict(key = "#triggerId",value = "CACHE.TRIGGERDETAIL")
    public void deleteTriggerDetailByTriggerId(String triggerId) {
        logger.info("deleteTriggerDetailByTriggerId,triggerId{} ",triggerId);
        TriggerDetailExample example = new TriggerDetailExample();
        example.createCriteria().andTriggerIdEqualTo(triggerId);
        this.triggerDetailMapper.deleteByExample(example);
        logger.info("deleteTriggerDetailByTriggerId,triggerId end");
    }

    /**
     * 根据联动规则ID批量删除联动明细
     *
     * @Param triggerIds 需批量删除的联动规则ID值
     * @return
     */
    @Override
    @Transactional
    public void deleteTriggerDetailByTriggerId(List<String> triggerIds) {
        logger.info("deleteTriggerDetailByTriggerId start ");
        if(CollectionUtils.isNotEmpty(triggerIds)){
            for(String id:triggerIds){
                deleteTriggerDetailByTriggerId(id);
            }
        }
        logger.info("deleteTriggerDetailByTriggerId end ");
    }

    /**
     * 根据联动规则ID查询联动规则明细
     *
     * @Param triggerId 联动规则ID值
     * @return 返回查询出的所有联动规则明细结果
     */
    @Override
    @RedisCacheable(key = "#triggerId",value = "CACHE.TRIGGERDETAIL",expire = ExpireTimeConstant.EXPIRE_CACHE_TRIGGER_DETAIL)
    public List<TriggerDetail> queryTriggerDetailByTriggerId(String triggerId) {
        logger.info("Query triggerDetail By triggerId: {}",triggerId);
        TriggerDetailExample example = new TriggerDetailExample();
        example.createCriteria().andTriggerIdEqualTo(triggerId);
        return this.triggerDetailMapper.selectByExample(example);
    }
}

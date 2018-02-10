/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.service.impl;

import com.eg.egsc.framework.paging.PageInfo;
import com.eg.egsc.framework.paging.PageUtils;
import com.eg.egsc.scp.eventcomponent.mapper.entity.TriggerLogExample;
import com.eg.egsc.scp.eventcomponent.utils.EcStringUtils;
import com.eg.egsc.scp.eventcomponent.utils.ValidateParamUtils;
import com.eg.egsc.scp.eventcomponent.mapper.TriggerLogMapper;
import com.eg.egsc.scp.eventcomponent.dto.FilterDto;
import com.eg.egsc.scp.eventcomponent.dto.PageQueryDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerLogDto;
import com.eg.egsc.scp.eventcomponent.mapper.entity.TriggerLog;
import com.eg.egsc.scp.eventcomponent.service.TriggerLogService;
import com.eg.egsc.scp.eventcomponent.utils.adaptor.TriggerLogAdaptor;
import com.eg.egsc.scp.eventcomponent.utils.reflect.ReflectCriteria;
import com.eg.egsc.scp.eventcomponent.utils.reflect.Tool;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 联动日志记录保存、查询、修改、删除
 *
 * @Author chenhao
 * @since 2017-12-12
 */
@Service
public class TriggerLogServiceImpl implements TriggerLogService {

    private static final Logger logger = LoggerFactory.getLogger(TriggerLogServiceImpl.class);

    @Autowired
    private TriggerLogMapper triggerLogMapper;

    /**
     * 保存联动日志
     *
     *@Param triggerLogDto 需保存的联动日志信息
     *@return 返回保存成功联动日志信息
     */
    @Override
    public TriggerLogDto saveTriggerLog(TriggerLogDto triggerLogDto) {
        if (triggerLogDto == null ){
            logger.info("TriggerLog is null");
            return null ;
        }

        //先手动生成，以后是框架自动生成
        if (StringUtils.isEmpty(triggerLogDto.getUuid())){
            String uuid = EcStringUtils.generateUuid();
            triggerLogDto.setUuid(uuid);
        }

        TriggerLog triggerLog = new TriggerLog();
        TriggerLogAdaptor.toEntity(triggerLogDto,triggerLog);
        triggerLog.setTriggerResult(triggerLogDto.getTriggerResult());
        triggerLogMapper.insert(triggerLog) ;
        triggerLogDto.setUuid(triggerLog.getUuid());

        logger.info("TriggerLog save successful");
        return triggerLogDto;
    }

    /**
     * 修改联动日志
     *
     *@Param triggerLogDto 需修改的联动日志信息
     *@return 返回修改成功后的联动日志
     */
    @Override
    public TriggerLogDto updateTriggerLog(TriggerLogDto triggerLogDto) {
        if (triggerLogDto == null || StringUtils.isEmpty(triggerLogDto.getTriggerLogId())){
            logger.info("TriggerLog or tirggerLogId is null");
            return null ;
        }

        TriggerLog triggerLogEntity = new TriggerLog();
        TriggerLogAdaptor.toEntity(triggerLogDto,triggerLogEntity);
        triggerLogEntity.setTriggerResult(triggerLogDto.getTriggerResult());

        TriggerLogExample example = new TriggerLogExample();
        example.createCriteria().andTriggerLogIdEqualTo(triggerLogEntity.getTriggerLogId()) ;
        triggerLogMapper.updateByExampleSelective(triggerLogEntity,example);
        logger.info("TriggerLog update successful");
        return triggerLogDto;
    }

    /**
     * 根据事件ID查询联动日志
     *
     *@Param eventLogIds 需查询的联动事件日志ID值集合
     *@return 返回查询出的联动日志结果集
     */
    @Override
    public List<TriggerLogDto> getTriggerLogsByEventIds(List<String> eventLogIds) {
        List<TriggerLogDto> triggerLogDtos = new ArrayList<>();
        if (CollectionUtils.isEmpty(eventLogIds)){
            logger.info("eventLogIds is empty");
            return triggerLogDtos ;
        }


        TriggerLogExample example = new TriggerLogExample();
        example.createCriteria().andEventLogIdIn(eventLogIds);
        List<TriggerLog> triggerLogList = triggerLogMapper.selectByExample(example);

        TriggerLogDto triggerLogDto ;
        for (TriggerLog triggerLog : triggerLogList){
            triggerLogDto = new TriggerLogDto();
            TriggerLogAdaptor.toDto(triggerLogDto,triggerLog);
            triggerLogDtos.add(triggerLogDto);
        }
        logger.info("TriggerLog query by eventLogId successful");
        return triggerLogDtos;
    }

    /**
     * 根据联动日志唯一标识查询联动日志
     *
     *@Param triggerLogId 联动事件日志ID值
     *@return 返回查询出的对应联动日志结果
     */
    @Override
    public TriggerLogDto getTriggerLogByTriggerLogId(String triggerLogId) {
        logger.info("Get triggerLog by triggerLogId:{}",triggerLogId);
        if(StringUtils.isEmpty(triggerLogId)){
            logger.info("triggerLogId is empty");
            return null ;
        }

        // step1 根据triggerLogId 查询
        TriggerLogExample example = new TriggerLogExample();
        example.createCriteria().andTriggerLogIdEqualTo(triggerLogId) ;
        List<TriggerLog> triggerLogList = triggerLogMapper.selectByExample(example);
        TriggerLog triggerLog = null ;
        if(CollectionUtils.isNotEmpty(triggerLogList)){
            triggerLog = triggerLogList.get(0);
        }

        if(null == triggerLog){
            logger.info("No triggerLog by triggerLogId:{}",triggerLogId);
            return null ;
        }
        // step2 对象转换为DTO
        TriggerLogDto triggerLogDto = new TriggerLogDto();
        TriggerLogAdaptor.toDto(triggerLogDto,triggerLog);
        return triggerLogDto;
    }

    /**
     * 分页查询联动日志
     *
     *@Param pageQuery 分页查询条件
     *@return 返回对应联动日志分页查询结果
     */
    @Override
    public PageInfo<TriggerLogDto> getTriggerLogPage(PageQueryDto pageQuery) {

        logger.info("Query triggerLog paging");
        if(!ValidateParamUtils.isPageLegal(pageQuery)){
            logger.info("Page params is invalid");
            return null ;
        }

        //step1 查询条件组装,反射获取条件
        List<FilterDto> filters = pageQuery.getFilters() ;
        TriggerLogExample oredCriteria = new TriggerLogExample();
        if (CollectionUtils.isNotEmpty(filters)){
            logger.info("Parse filerInfo");
            try {
                ReflectCriteria.invoke(oredCriteria,filters,TriggerLog.class);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        //step2 排序规则处理
        if( pageQuery.getOrder() != null && StringUtils.isNotEmpty(pageQuery.getOrder().getOrderBy()) && StringUtils.isNotEmpty(pageQuery.getOrder().getSort())){
            logger.info("Parse orderInfo");
            oredCriteria.setOrderByClause(Tool.humpToLine(pageQuery.getOrder().getOrderBy())+" "+pageQuery.getOrder().getSort());
        }

        Integer pageSize = pageQuery.getPageSize();
        Integer pageNo = pageQuery.getPageNo();
        Long total =triggerLogMapper.countByExample(oredCriteria);
        PageInfo<TriggerLogDto> result = new PageInfo<>();
        result.setTotal(total);
        result.setPageSize(pageSize);
        result.setCurrentPage(pageNo);
        logger.info("Query triggerLog by condition ,total is {}",total);
        if(total > 0){
            RowBounds rowBounds =
                    new RowBounds(PageUtils.getOffset(pageNo,pageSize), pageSize);
            List<TriggerLog> triggerLogList = triggerLogMapper.selectByExampleWithRowbounds(oredCriteria, rowBounds);
            if (CollectionUtils.isNotEmpty(triggerLogList)){
                List<TriggerLogDto> triggerLogDtos = new ArrayList<>();
                TriggerLogDto triggerLogDto ;
                for(TriggerLog triggerLog :triggerLogList){
                    triggerLogDto = new TriggerLogDto();
                    TriggerLogAdaptor.toDto(triggerLogDto,triggerLog);
                    triggerLogDtos.add(triggerLogDto) ;
                }
                result.setRows(triggerLogDtos);
            }
        }
        logger.info("Query triggerLog paging end");
        return result;

    }

    /**
     * 通过联动时间删除联动日志记录
     *
     *@Param startTimestamp 开始时间
     *@Param endTimestamp 结束时间
     *@return
     */
    @Override
    public void deleteTriggerLogByTriggerTime(Long startTimestamp, Long endTimestamp) {

        logger.info("Delete triggerLog By triggerTime");
        if(null == endTimestamp || null == startTimestamp){
            logger.info("Time param is invalid");
            return;
        }
        Date startTime = new Date(startTimestamp);
        Date endTime = new Date(endTimestamp);
        logger.info("Delete triggerLog by triggerTime between {} and {}",startTime,endTime);

        TriggerLogExample example = new TriggerLogExample();
        example.createCriteria().andTriggerTimeBetween(startTime,endTime) ;
        triggerLogMapper.deleteByExample(example) ;
    }

    /**
     * 通过Id删除联动日志记录
     *
     *@Param uuid 开始时间
     *@return 返回删除的数量
     */
    @Override
    public int deleteTriggerLogById(String uuid) {
        return triggerLogMapper.deleteByPrimaryKey(uuid);
    }

}

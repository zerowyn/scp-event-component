/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.service.impl;


import com.eg.egsc.framework.paging.PageInfo;
import com.eg.egsc.framework.paging.PageUtils;
import com.eg.egsc.scp.eventcomponent.dto.*;
import com.eg.egsc.scp.eventcomponent.mapper.EventLogMapper;
import com.eg.egsc.scp.eventcomponent.mapper.entity.EventLog;
import com.eg.egsc.scp.eventcomponent.mapper.entity.EventLogExample;
import com.eg.egsc.scp.eventcomponent.utils.EcStringUtils;
import com.eg.egsc.scp.eventcomponent.utils.adaptor.EpsEventLogAdaptor;
import com.eg.egsc.scp.eventcomponent.utils.reflect.ReflectCriteria;
import com.eg.egsc.scp.eventcomponent.utils.reflect.Tool;
import com.eg.egsc.scp.eventcomponent.service.EventLogService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * 完成事件日志的增删改查的业务处理
 *
 * @author xulei
 * @since 2017-12-8
 */
@Service
public class EventLogServiceImpl implements EventLogService {

    private static final Logger logger = LoggerFactory.getLogger(EventLogServiceImpl.class);

    @Autowired
    private EventLogMapper eventLogMapper;

    /**
     *  分页查询事件日志
     *
     *@Param req 分页查询条件
     *@return 返回分页查询结果
     */
    @Override
    public PageInfo<EventLogDto> selectByExampleWithRowbounds(PageQueryDto req) {
        logger.info("Query eventLog by page");
        //step1,初始化分页
        RowBounds rowBounds =
                new RowBounds(PageUtils.getOffset(req.getPageNo(),req.getPageSize()), req.getPageSize());
        EventLogExample example=new EventLogExample();
        List<FilterDto> filters = req.getFilters();
        if(CollectionUtils.isNotEmpty(filters)){
            try {
                //step2 通过反射构造查询条件
                ReflectCriteria.invoke(example,filters,EventLog.class);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        //step3 通过当前条件查询总数
        PageInfo<EventLogDto> result = new PageInfo<>() ;
        Long total = eventLogMapper.countByExample(example) ;


        result.setTotal(total);
        result.setCurrentPage(req.getPageNo());
        result.setPageSize(req.getPageSize());
        logger.info("Query eventLog total {}",total);
        //step4总数小于1，返回结果
        if(total <= 0){
            return result ;
        }
        //step5 构造排序
        if( req.getOrder() != null && StringUtils.isNotEmpty(req.getOrder().getSort()) && StringUtils.isNotEmpty(req.getOrder().getOrderBy())){
            example.setOrderByClause(Tool.humpToLine(req.getOrder().getOrderBy())+" "+req.getOrder().getSort());
        }
        //step6 查询数据，并封装结果
        List<EventLog> eventLogs = eventLogMapper.selectByExampleWithRowbounds(example,rowBounds);
        List<EventLogDto> rows = new ArrayList<>();
        EventLogDto eventLogDto ;
        for (EventLog eventLog : eventLogs) {
            eventLogDto = new EventLogDto();
            EpsEventLogAdaptor.toDto(eventLogDto, eventLog);
            rows.add(eventLogDto);
        }
        result.setRows(rows);
        return result;
    }

    /**
     * 保存事件日志
     *
     *@Param eventLogDto
     *@return 返回保存成功记录条数
     */
    @Override
    public int insert(EventLogDto eventLogDto) {

        eventLogDto.setUuid(EcStringUtils.generateUuid());
        logger.info("Insert eventLog,{}",eventLogDto);
        EventLog eventLog = new EventLog();

        // step1 将数据转换成entity，插入数据库
        EpsEventLogAdaptor.toEntity(eventLog,eventLogDto);
        logger.info("Insert eventLogDto,{}",eventLog);
        return eventLogMapper.insert(eventLog);
    }

    /**
     * 更新事件日志，为空的数据不做更新
     *
     *@Param eventLogDto
     *@return 返回更新成功的记录条数
     */
    @Override
    public int updateEventLog(EventLogDto eventLogDto) {

        logger.info("Update eventLog");

        EventLog record = new EventLog();
        // step1 转换成数据库对象
        EpsEventLogAdaptor.toEntity(record,eventLogDto);
        return eventLogMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据事件日志ID查询事件日志
     *
     *@Param eventLogId 事件日志ID
     *@return 返回事件日志查询结果
     */
    @Override
    public EventLog selectByEventLogId(String eventLogId) {
        logger.info("Query eventLog by eventLogId");
        EventLogExample example = new EventLogExample();
        EventLogExample.Criteria criteria = example.createCriteria();
        criteria.andEventLogIdEqualTo(eventLogId);
        List<EventLog> eventLogs = eventLogMapper.selectByExample(example);
        EventLog eventLog=null;
        if(CollectionUtils.isNotEmpty(eventLogs)){
             eventLog = eventLogs.get(0);
        }
        return eventLog;
    }

    /**
     * 按照时间范围删除事件日志
     *
     *@Param req
     *@return 返回删除成功的事件日志记录数
     */
    @Override
    public int deleteEventLog(DeleteEventDto req) {
        logger.info("Delete eventLog by time");
        if (req == null ) {
            return 0;
        }
        logger.info("Delete eventLog startTime{},endTime {}",req.getStartTime(),req.getEndTime());
        //1校验参数合法性
        //权限校验,校验起止时间
        EventLogExample example = new EventLogExample();
        EventLogExample.Criteria criteria = example.createCriteria();
        criteria.andEndTimeBetween(req.getStartTime(),req.getEndTime());
        return eventLogMapper.deleteByExample(example);
    }

    /**
     * 按照uuid删除事件日志
     *
     *@Param eventLogId 事件日志编码
     *@return 返回删除成功的事件日志记录数
     */
    @Override
    public int deleteEventLogByEventLogId(String eventLogId) {
        EventLogExample example = new EventLogExample();
        example.createCriteria().andEventLogIdEqualTo(eventLogId);
        return eventLogMapper.deleteByExample(example);
    }
}

package com.eg.egsc.scp.eventcomponent.mapper;

import com.eg.egsc.scp.eventcomponent.mapper.entity.EventLog;
import com.eg.egsc.scp.eventcomponent.mapper.entity.EventLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

@Component
public interface EventLogMapper {
    long countByExample(EventLogExample example);

    int deleteByExample(EventLogExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(EventLog record);

    int insertSelective(EventLog record);

    List<EventLog> selectByExampleWithRowbounds(EventLogExample example, RowBounds rowBounds);

    List<EventLog> selectByExample(EventLogExample example);

    EventLog selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") EventLog record, @Param("example") EventLogExample example);

    int updateByExample(@Param("record") EventLog record, @Param("example") EventLogExample example);

    int updateByPrimaryKeySelective(EventLog record);

    int updateByPrimaryKey(EventLog record);
}
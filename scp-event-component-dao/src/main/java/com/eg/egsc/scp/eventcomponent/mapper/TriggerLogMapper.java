package com.eg.egsc.scp.eventcomponent.mapper;

import com.eg.egsc.scp.eventcomponent.mapper.entity.TriggerLog;
import com.eg.egsc.scp.eventcomponent.mapper.entity.TriggerLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TriggerLogMapper {
    long countByExample(TriggerLogExample example);

    int deleteByExample(TriggerLogExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(TriggerLog record);

    int insertSelective(TriggerLog record);

    List<TriggerLog> selectByExample(TriggerLogExample example);

    List<TriggerLog> selectByExampleWithRowbounds(TriggerLogExample example, RowBounds rowBounds);

    TriggerLog selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") TriggerLog record, @Param("example") TriggerLogExample example);

    int updateByExample(@Param("record") TriggerLog record, @Param("example") TriggerLogExample example);

    int updateByPrimaryKeySelective(TriggerLog record);

    int updateByPrimaryKey(TriggerLog record);
}
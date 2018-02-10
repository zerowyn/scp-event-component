package com.eg.egsc.scp.eventcomponent.mapper;

import com.eg.egsc.scp.eventcomponent.mapper.entity.Trigger;
import com.eg.egsc.scp.eventcomponent.mapper.entity.TriggerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

@Component
public interface TriggerMapper {
    long countByExample(TriggerExample example);

    int deleteByExample(TriggerExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(Trigger record);

    int insertSelective(Trigger record);

    List<Trigger> selectByExample(TriggerExample example);

    List<Trigger> selectByExampleWithRowbounds(TriggerExample example, RowBounds rowBounds);

    Trigger selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") Trigger record, @Param("example") TriggerExample example);

    int updateByExample(@Param("record") Trigger record, @Param("example") TriggerExample example);

    int updateByPrimaryKeySelective(Trigger record);

    int updateByPrimaryKey(Trigger record);
}
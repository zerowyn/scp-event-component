package com.eg.egsc.scp.eventcomponent.mapper;

import com.eg.egsc.scp.eventcomponent.mapper.entity.TriggerDetail;
import com.eg.egsc.scp.eventcomponent.mapper.entity.TriggerDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface TriggerDetailMapper {
    long countByExample(TriggerDetailExample example);

    int deleteByExample(TriggerDetailExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(TriggerDetail record);

    int insertSelective(TriggerDetail record);

    List<TriggerDetail> selectByExample(TriggerDetailExample example);

    TriggerDetail selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") TriggerDetail record, @Param("example") TriggerDetailExample example);

    int updateByExample(@Param("record") TriggerDetail record, @Param("example") TriggerDetailExample example);

    int updateByPrimaryKeySelective(TriggerDetail record);

    int updateByPrimaryKey(TriggerDetail record);
}
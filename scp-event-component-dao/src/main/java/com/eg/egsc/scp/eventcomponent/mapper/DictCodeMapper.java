package com.eg.egsc.scp.eventcomponent.mapper;

import com.eg.egsc.scp.eventcomponent.mapper.entity.DictCode;
import com.eg.egsc.scp.eventcomponent.mapper.entity.DictCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface DictCodeMapper {
    long countByExample(DictCodeExample example);

    int deleteByExample(DictCodeExample example);

    int insert(DictCode record);

    int insertSelective(DictCode record);

    List<DictCode> selectByExample(DictCodeExample example);

    int updateByExampleSelective(@Param("record") DictCode record, @Param("example") DictCodeExample example);

    int updateByExample(@Param("record") DictCode record, @Param("example") DictCodeExample example);
}
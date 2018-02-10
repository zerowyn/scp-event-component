package com.eg.egsc.scp.eventcomponent.mapper;

import com.eg.egsc.scp.eventcomponent.mapper.entity.DictType;
import com.eg.egsc.scp.eventcomponent.mapper.entity.DictTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface DictTypeMapper {
    long countByExample(DictTypeExample example);

    int deleteByExample(DictTypeExample example);

    int insert(DictType record);

    int insertSelective(DictType record);

    List<DictType> selectByExample(DictTypeExample example);

    int updateByExampleSelective(@Param("record") DictType record, @Param("example") DictTypeExample example);

    int updateByExample(@Param("record") DictType record, @Param("example") DictTypeExample example);
}
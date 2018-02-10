/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.service.impl;

import com.eg.egsc.common.component.utils.JsonUtil;
import com.eg.egsc.scp.eventcomponent.common.CommonConstant;
import com.eg.egsc.scp.eventcomponent.common.DictEnableFlagEnum;
import com.eg.egsc.scp.eventcomponent.dto.DictItemDto;
import com.eg.egsc.scp.eventcomponent.dto.trigger.TriggerDeviceDto;
import com.eg.egsc.scp.eventcomponent.mapper.DictCodeMapper;
import com.eg.egsc.scp.eventcomponent.mapper.DictTypeMapper;
import com.eg.egsc.scp.eventcomponent.mapper.entity.DictCode;
import com.eg.egsc.scp.eventcomponent.mapper.entity.DictCodeExample;
import com.eg.egsc.scp.eventcomponent.mapper.entity.DictType;
import com.eg.egsc.scp.eventcomponent.mapper.entity.DictTypeExample;
import com.eg.egsc.scp.eventcomponent.mq.constants.EventMsgConstant;
import com.eg.egsc.scp.eventcomponent.service.DictService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通过相关条件查询字典
 *
 * @author wangjun
 * @since 2018-01-04
 */
@Service
public class DictServiceImpl implements DictService {

    private static final Logger logger = LoggerFactory.getLogger(DictServiceImpl.class);

    @Autowired
    private DictCodeMapper dictCodeMapper;

    @Autowired
    private DictTypeMapper dictTypeMapper;

    /**
     * 查询联动方式，中文，设备类型编码
     *
     * @Param
     * @return 返回查询联动方式，中文，设备类型编码的结果
     */
    @Override
    public List<TriggerDeviceDto> getTriggerDeviceList(){
        List<TriggerDeviceDto> triggerDeviceDtoList = new ArrayList<>();

        DictType dictTypeTopic = getDictTypeByTypeName(CommonConstant.TRIGGER_TYPE_TOPIC);
        List<DictCode> dictCodeTopicList = null;
        if(!ObjectUtils.isEmpty(dictTypeTopic)){
            dictCodeTopicList = getDictCodeByItemType(dictTypeTopic.getTypeCode());
        }

        DictType dictTypeDevice = getDictTypeByTypeName(CommonConstant.TRIGGER_TYPE_DEVICE);

        if(null != dictCodeTopicList && !dictCodeTopicList.isEmpty()){
            dictCodeTopicList.forEach(dictCode -> {
                TriggerDeviceDto triggerDeviceDto = new TriggerDeviceDto();
                triggerDeviceDto.setTriggerName(dictCode.getItemName());
                triggerDeviceDto.setTriggerType(dictCode.getItemCode());
                triggerDeviceDto.setTo(dictCode.getItemValue());

                //再去查询联动方式对应组件设备类型编码
                String dictCodeTypeCode = null;
                if(!ObjectUtils.isEmpty(dictTypeDevice)){
                    dictCodeTypeCode = dictTypeDevice.getTypeCode();
                }
                List<DictCode> dictCodeDeviceList = getDictCodeByItemTypeAndCode(dictCodeTypeCode, dictCode.getItemCode());
                if(!ObjectUtils.isEmpty(dictCodeDeviceList)){
                    triggerDeviceDto.setDeviceTypeCode(dictCodeDeviceList.get(0).getItemValue());
                }
                triggerDeviceDtoList.add(triggerDeviceDto);
            });
        }

        return triggerDeviceDtoList;
    }

    /**
     * 根据字典主表type_name查询
     *
     * @Param typeName 字典主表type_name值
     * @return 返回根据type_name查询字典主表结果
     */
    @Override
    public DictType getDictTypeByTypeName(String typeName){
        DictTypeExample example = new DictTypeExample();
        DictTypeExample.Criteria criteria = example.createCriteria();
        criteria.andTypeNameEqualTo(typeName);
        criteria.andEnableFlagEqualTo(DictEnableFlagEnum.YES.getValue());
        List<DictType> dictTypeList = dictTypeMapper.selectByExample(example);

        if(CollectionUtils.isNotEmpty(dictTypeList)){
            return dictTypeList.get(0);
        }
        return null;
    }

    /**
     * 根据字典子表itemType查询字典子表
     *
     * @Param itemType 字典主表itemType值
     * @return 返回根据itemType查询字典主表结果
     */
    @Override
    public List<DictCode> getDictCodeByItemType(String itemType){
        DictCodeExample example = new DictCodeExample();
        DictCodeExample.Criteria criteria = example.createCriteria();
        criteria.andItemTypeEqualTo(itemType);
        criteria.andEnableFlagEqualTo(DictEnableFlagEnum.YES.getValue());
        List<DictCode> dictCodeList = dictCodeMapper.selectByExample(example);

        if(CollectionUtils.isNotEmpty(dictCodeList)){
            return dictCodeList;
        }
        return Collections.emptyList();
    }

    /**
     * 根据字典子表itemType和item_code查询字典子表
     *
     * @Param itemType 字典主表itemType值
     * @Param itemCode 字典主表itemCode值
     * @return 返回根据itemType,itemCode查询字典主表结果
     */
    @Override
    public List<DictCode> getDictCodeByItemTypeAndCode(String itemType,String itemCode){
        DictCodeExample example = new DictCodeExample();
        DictCodeExample.Criteria criteria = example.createCriteria();
        criteria.andItemTypeEqualTo(itemType);
        criteria.andEnableFlagEqualTo(DictEnableFlagEnum.YES.getValue());
        criteria.andItemCodeEqualTo(itemCode);
        List<DictCode> dictCodeList = dictCodeMapper.selectByExample(example);

        if(CollectionUtils.isNotEmpty(dictCodeList)){
            return dictCodeList;
        }
        return Collections.emptyList();
    }

    /**
     * 查询出业务组件的项目名和编号值，并放到map中以供使用
     *
     * @Param
     * @return
     */
    @Override
    public void fillTopicEventType(){
        DictType dictType = getDictTypeByTypeName(EventMsgConstant.DICT_TYPE_NAME);
        if (dictType != null) {
            DictCodeExample example = new DictCodeExample();
            example.createCriteria().andItemTypeEqualTo(dictType.getTypeCode())
                    .andEnableFlagEqualTo(DictEnableFlagEnum.YES.getValue());
            List<DictCode> dictCodes = dictCodeMapper.selectByExample(example);
            Map<String, Integer> topicEventTypeMap = EventMsgConstant.getTopicEventTypeMap();
            if (topicEventTypeMap == null) {
                topicEventTypeMap = new HashMap<>();
            }
            topicEventTypeMap.clear();
            for (DictCode dictCode : dictCodes) {
                String itemCode = dictCode.getItemCode();
                Integer itemValue = Integer.parseInt(dictCode.getItemValue());
                topicEventTypeMap.put(itemCode, itemValue);
            }
            logger.info(JsonUtil.toJsonString(topicEventTypeMap));
        }
    }

    /**
     * 获取组件列表
     *
     * @return 返回组件列表
     */
    @Override
    public List<DictItemDto> listComponent() {
        DictType dictType = getDictTypeByTypeName(CommonConstant.COMPONENT_LIST);
        if (dictType != null) {
            return listDictItemByCode(dictType.getTypeCode(),null);
        }
        return Collections.emptyList();
    }

    /**
     * 根据编码获取设备分类（组件）和事件类型的对应列表
     *
     * @return 返回组件和对应的事件类型
     */
    @Override
    public List<DictItemDto> listComponentWithEventType(String itemCode) {
        DictType dictType = getDictTypeByTypeName(CommonConstant.COMPONENT_EVENT_TYPE);
        if (dictType != null) {
            return listDictItemByCode(dictType.getTypeCode(),itemCode);
        }
        return Collections.emptyList();
    }

    /**
     * 根据编码获取设备分类（组件）和事件类型的对应列表
     *
     * @return 返回组件和对应的事件类型
     */
    @Override
    public List<DictItemDto> listComponentWithDeviceType(String itemCode) {
        DictType dictType = getDictTypeByTypeName(CommonConstant.COMPONENT_DEVICE_TYPE);
        if (dictType != null) {
            return listDictItemByCode(dictType.getTypeCode(),itemCode);
        }
        return Collections.emptyList();
    }

    /**
     * 根据字典子表itemType和parent_code查询字典子表,并将itemCode和itemName放到DictItemDto中
     *
     * @Param itemType 字典主表itemType值
     * @Param parentCode 字典主表parentCode值
     * @return 返回根据itemType,parentCode查询字典主表结果,并将itemCode和itemName放到DictItemDto中
     */
    @Override
    public List<DictItemDto> listDictItemByCode(String itemType, String parentCode){
        DictCodeExample example = new DictCodeExample();
        DictCodeExample.Criteria criteria = example.createCriteria();
        criteria.andItemTypeEqualTo(itemType)
                .andEnableFlagEqualTo(DictEnableFlagEnum.YES.getValue());
        if (!StringUtils.isEmpty(parentCode)){
            criteria.andParentCodeEqualTo(parentCode);
        }
        example.setOrderByClause("item_code asc");
        List<DictCode> dictCodeList = dictCodeMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(dictCodeList)){
            List<DictItemDto> itemDtoList = new ArrayList<>(dictCodeList.size());
            for (DictCode dictCode : dictCodeList){
                DictItemDto itemDto = new DictItemDto();
                itemDto.setCode(dictCode.getItemCode());
                itemDto.setName(dictCode.getItemName());
                itemDtoList.add(itemDto);
            }
            return itemDtoList;
        }
        return Collections.emptyList();
    }

}

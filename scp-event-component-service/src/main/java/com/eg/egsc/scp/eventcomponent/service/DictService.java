/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.service;

import com.eg.egsc.scp.eventcomponent.dto.DictItemDto;
import com.eg.egsc.scp.eventcomponent.dto.trigger.TriggerDeviceDto;
import com.eg.egsc.scp.eventcomponent.mapper.entity.DictCode;
import com.eg.egsc.scp.eventcomponent.mapper.entity.DictType;

import java.util.List;

/**
 * 通过相关条件查询字典
 *
 * @author wangjun
 * @since 2018-01-04
 */
public interface DictService {

    /**
     * 查询联动方式，中文，设备类型编码
     *
     * @Param
     * @return 返回查询联动方式，中文，设备类型编码的结果
     */
    List<TriggerDeviceDto> getTriggerDeviceList();

    /**
     * 根据字典主表type_name查询
     *
     * @Param typeName 字典主表type_name值
     * @return 返回根据type_name查询字典主表结果
     */
    DictType getDictTypeByTypeName(String typeName);

    /**
     * 根据字典子表itemType查询字典子表
     *
     * @Param itemType 字典主表itemType值
     * @return 返回根据itemType查询字典主表结果
     */
    List<DictCode> getDictCodeByItemType(String itemType);

    /**
     * 根据字典子表itemType和item_code查询字典子表
     *
     * @Param itemType 字典主表itemType值
     * @Param itemCode 字典主表itemCode值
     * @return 返回根据itemType,itemCode查询字典主表结果
     */
    List<DictCode> getDictCodeByItemTypeAndCode(String itemType,String itemCode);

    /**
     * 查询出业务组件的项目名和编号值，并放到map中以供使用
     */
    void fillTopicEventType();

    /**
     * 获取组件列表
     *
     * @return 返回组件列表
     */
    List<DictItemDto> listComponent();

    /**
     * 根据编码获取设备分类（组件）和事件类型的对应列表
     *
     * @return 返回组件和对应的事件类型
     */
    List<DictItemDto> listComponentWithEventType(String itemCode);

    /**
     * 根据编码获取设备分类（组件）和设备类型的对应列表
     *
     * @return 返回组件和对应的设备类型编码
     */
    List<DictItemDto> listComponentWithDeviceType(String itemCode);

    /**
     * 根据字典子表itemType和parent_code查询字典子表
     *
     * @Param itemType 字典主表itemType值
     * @Param parentCode 字典主表parentCode值
     * @return 返回根据itemType,parentCode查询字典主表结果
     */
    List<DictItemDto> listDictItemByCode(String itemType, String parentCode);
}

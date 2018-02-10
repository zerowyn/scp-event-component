/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.service;

import com.eg.egsc.scp.eventcomponent.EventComponentServiceApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @PackageName com.eg.egsc.scp.eventcomponent.service
 * @Description
 * @Author zhouxing
 * @Date 2018/01/10 17:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EventComponentServiceApplication.class })
public class DictServiceTest {

    @Autowired
    DictService dictServiceImpl;

    /**
     * 测试查询联动方式，中文，设备类型编码
     */
    @Test
    public void getTriggerDeviceList(){
        dictServiceImpl.getTriggerDeviceList();
    }

    /**
     * 测试根据字典主表type_name查询
     */
    @Test
    public void getDictTypeByTypeName(){
        dictServiceImpl.getDictTypeByTypeName("component_topic");
    }

    /**
     * 测试根据字典子表itemType查询字典子表
     *
     */
    @Test
    public void getDictCodeByItemType(){
        dictServiceImpl.getDictCodeByItemType("01");
    }

    /**
     * 测试根据字典子表itemType和item_code查询字典子表
     *
     */
    @Test
    public void getDictCodeByItemTypeAndCode(){
        dictServiceImpl.getDictCodeByItemTypeAndCode("03","FAC_CALLING");
    }


    public void fillTopicEventType(){
        dictServiceImpl.fillTopicEventType();
    }

    /**
     * deviceTypeCode无值时 获取设备分类（组件）和与设备类型编码的对应列表
     *
     */
    @Test
    public void listComponent() {
        dictServiceImpl.listComponent();
    }

    /**
     * deviceTypeCode有值时 获取设备分类（组件）和事件类型的对应列表
     *
     */
    @Test
    public void listComponentWithEventType() {
        dictServiceImpl.listComponentWithEventType("02");
    }

    /**
     * deviceTypeCode有值时 获取设备分类（组件）和设备类型的对应列表
     *
     */
    @Test
    public void listComponentWithDeviceType() {
        dictServiceImpl.listComponentWithDeviceType("02");
    }

}

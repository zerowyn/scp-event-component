/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.api;

import com.eg.egsc.framework.client.dto.RequestDto;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.framework.service.base.api.BaseApiController;
import com.eg.egsc.scp.eventcomponent.common.CommonConstant;
import com.eg.egsc.scp.eventcomponent.dto.DictItemDto;
import com.eg.egsc.scp.eventcomponent.dto.trigger.TriggerDeviceDto;
import com.eg.egsc.scp.eventcomponent.service.DictService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 联动方式的API接口类
 *
 * @author wangjun
 * @since 2018-01-05
 */
@RestController
@RequestMapping(value = "/api/triggerType")
@Api(description = "联动方式")
public class TriggerTypeApi extends BaseApiController {

    @Autowired
    private DictService dictServiceImpl;

    /**
     * 获取联动方式列表
     *
     * @return 返回联动方式列表
     */
    @RequestMapping(value = "/listTriggerTypes", method = RequestMethod.POST)
    @ApiOperation(value = "联动方式列表",notes = "联动方式列表")
    public ResponseDto getTriggerTypeList(){
        ResponseDto responseDto = new ResponseDto();

        List<TriggerDeviceDto> triggerDeviceList = dictServiceImpl.getTriggerDeviceList();
        if(!ObjectUtils.isEmpty(triggerDeviceList)){
            responseDto.setCode(CommonConstant.RESPONSE_CODE_SUCCESS);
            responseDto.setMessage("获取联动方式列表 成功");
            responseDto.setData(triggerDeviceList);
            return responseDto;
        }
        responseDto.setCode(CommonConstant.RESPONSE_CODE_NOT_EXIST);
        responseDto.setMessage("查询结果失败");
        return responseDto;
    }

    /**
     * 获取组件列表
     *
     * @return 返回组件列表信息
     */
    @RequestMapping(value = "/listComponent", method = RequestMethod.POST)
    @ApiOperation(value = "组件列表",notes = "组件列表")
    public ResponseDto listComponent(){
        ResponseDto res = new ResponseDto();
        List<DictItemDto> itemDtoList = dictServiceImpl.listComponent();
        res.setData(itemDtoList);
        res.setCode(CommonConstant.RESPONSE_CODE_SUCCESS);
        res.setMessage("获取组件列表");
        return res;
    }

    /**
     * 根据编码，获取设备分类（组件）和事件类型的对应列表
     *
     * @return 返回列表信息
     */
    @RequestMapping(value = "/listComponentWithEventType", method = RequestMethod.POST)
    @ApiOperation(value = "组件和事件类型对应列表",notes = "组件和事件类型对应列表")
    public ResponseDto listComponentWithEventType(@RequestBody RequestDto<DictItemDto> requestDto){
        ResponseDto res = new ResponseDto();
        DictItemDto dictItemDto = requestDto.getData();
        if (dictItemDto != null && !StringUtils.isEmpty(dictItemDto.getCode())){
            String itemCode = dictItemDto.getCode();
            List<DictItemDto> itemDtoList = dictServiceImpl.listComponentWithEventType(itemCode);
            res.setData(itemDtoList);
            res.setCode(CommonConstant.RESPONSE_CODE_SUCCESS);
            res.setMessage("获取设备分类（组件）和事件类型的对应列表");
            return res;
        }
        res.setCode(CommonConstant.RESPONSE_CODE_NOT_EXIST);
        res.setMessage("参数编码为空，查询结果失败");
        return res;
    }

    /**
     * 根据编码，获取设备分类（组件）和设备类型的对应列表
     *
     * @return 返回列表信息
     */
    @RequestMapping(value = "/listComponentWithDeviceType", method = RequestMethod.POST)
    @ApiOperation(value = "组件和设备类型对应列表",notes = "组件和设备类型对应列表")
    public ResponseDto listComponentWithDeviceType(@RequestBody RequestDto<DictItemDto> requestDto){
        ResponseDto res = new ResponseDto();
        DictItemDto itemDto = requestDto.getData();
        if (itemDto != null && !StringUtils.isEmpty(itemDto.getCode())){
            String itemCode = itemDto.getCode();
            List<DictItemDto> itemDtoList = dictServiceImpl.listComponentWithDeviceType(itemCode);
            res.setData(itemDtoList);
            res.setCode(CommonConstant.RESPONSE_CODE_SUCCESS);
            res.setMessage("获取设备分类（组件）和设备类型的对应列表");
            return res;
        }
        res.setCode(CommonConstant.RESPONSE_CODE_NOT_EXIST);
        res.setMessage("参数编码为空，查询结果失败");
        return res;
    }

}

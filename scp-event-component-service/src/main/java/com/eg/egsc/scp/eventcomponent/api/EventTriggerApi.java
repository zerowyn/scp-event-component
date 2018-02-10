/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.api;

import com.eg.egsc.framework.client.dto.RequestDto;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.framework.paging.PageInfo;
import com.eg.egsc.framework.service.base.api.BaseApiController;
import com.eg.egsc.scp.eventcomponent.common.CommonConstant;
import com.eg.egsc.scp.eventcomponent.dto.PageQueryDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerDelDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerDetailDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerDto;
import com.eg.egsc.scp.eventcomponent.mapper.entity.Trigger;
import com.eg.egsc.scp.eventcomponent.mapper.entity.TriggerDetail;
import com.eg.egsc.scp.eventcomponent.service.TriggerService;
import com.eg.egsc.scp.eventcomponent.service.impl.TriggerDetailServiceImpl;
import com.eg.egsc.scp.eventcomponent.utils.EcStringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 联动规则的API接口类
 *
 * @author tangsuwen
 * @since 2017-12-14
 */
@RestController
@RequestMapping(value = "/api/trigger")
@Api(description = "联动规则")
public class EventTriggerApi extends BaseApiController {

    @Autowired
    TriggerService triggerServiceImpl;
    @Autowired
    TriggerDetailServiceImpl triggerDetailServiceImpl;

    /**
     * 新增联动规则信息
     *
     * @param req 需要保存的联动规则信息
     * @return 返回保存结果
     */
    @RequestMapping(value = "/insertTrigger", method = RequestMethod.POST)
    @ApiOperation(value = "新增",notes = "新增")
    public ResponseDto saveTrigger(@RequestBody @Valid RequestDto<TriggerDto> req) {
        ResponseDto res = new ResponseDto();
        TriggerDto epsTriggerDto = req.getData();
        Trigger trigger;
        // 根据eventType和eventSourceCode查询是否已经匹配过联动规则
        trigger = this.triggerServiceImpl.queryTriggerByEventKey(epsTriggerDto.getEventType(),
                epsTriggerDto.getEventSourceCode());
        if (trigger != null) {
            res.setCode(CommonConstant.RESPONSE_CODE_SAVE_FAILURE);
            res.setMessage("保存联动规则失败：联动规则存在重复");
            return res;
        }
        // 不存在就进行新增
        trigger = new Trigger();
        trigger.setEventType(epsTriggerDto.getEventType());
        trigger.setEventSourceCode(epsTriggerDto.getEventSourceCode());
        trigger.setIsSequential(epsTriggerDto.getIsSequential());
        trigger.setCreator(epsTriggerDto.getCreator());
        //允许存在联动细则为空的情况，用户可以后续再配联动细则
        List<TriggerDetail> triggerDetails = new ArrayList<>();
        List<TriggerDetailDto> triggerDetailDtos = epsTriggerDto.getTriggers();
        if(null != triggerDetailDtos && !triggerDetailDtos.isEmpty()) {
            triggerDetailDtos.forEach(detailDto -> {
                TriggerDetail triggerDetail = new TriggerDetail();
                triggerDetail.setTriggerParams(detailDto.getParams());
                triggerDetail.setTriggerType(detailDto.getMethod());
                triggerDetail.setTo(detailDto.getTo());
                triggerDetail.setOrder(detailDto.getOrder());

                triggerDetails.add(triggerDetail);
            });
        }
        this.triggerServiceImpl.saveTrigger(trigger,triggerDetails);
        res.setMessage("保存联动规则成功");
        res.setCode(CommonConstant.RESPONSE_CODE_SUCCESS);
        return res;
    }

    /**
     * 修改联动规则信息
     *
     * @param req 需要修改的联动信息
     * @return 返回修改结果
     */
    @RequestMapping(value = "/updateTrigger", method = RequestMethod.POST)
    @ApiOperation(value = "修改",notes = "修改")
    public ResponseDto updateTrigger(@RequestBody RequestDto<TriggerDto> req) {
        ResponseDto res = new ResponseDto();
        TriggerDto epsTriggerDto = req.getData();
        if(null == epsTriggerDto.getUuid()){
            res.setCode(CommonConstant.RESPONSE_CODE_SAVE_FAILURE);
            res.setMessage("修改联动规则失败：联动规则uuid为空");
            return res;
        }
        Trigger trigger;
        // 根据情况查询是否存在联动规则
        trigger = this.triggerServiceImpl.queryTriggerByEventKey(epsTriggerDto.getEventType(),
                epsTriggerDto.getEventSourceCode());
        List<TriggerDetail> triggerDetails;
        if (trigger != null && trigger.getUuid().equals(epsTriggerDto.getUuid())) {
            //如果存在就提示用户，且update
            trigger.setIsSequential(epsTriggerDto.getIsSequential());
            trigger.setCreator(epsTriggerDto.getCreator());
            //允许存在联动细则为空的情况，即修改时去掉联动细则
            triggerDetails = new ArrayList<>(epsTriggerDto.getTriggers().size());
            final String triggerId = trigger.getUuid();
            List<TriggerDetailDto> detailDtos = epsTriggerDto.getTriggers();
            if(null != detailDtos && !detailDtos.isEmpty()) {
                epsTriggerDto.getTriggers().forEach(detailDto -> {
                    TriggerDetail triggerDetail = new TriggerDetail();
                    triggerDetail.setTriggerId(triggerId);
                    triggerDetail.setTriggerParams(detailDto.getParams());
                    triggerDetail.setTriggerType(detailDto.getMethod());
                    triggerDetail.setTo(detailDto.getTo());
                    triggerDetail.setOrder(detailDto.getOrder());
                    triggerDetails.add(triggerDetail);
                });
            }
            this.triggerServiceImpl.updateTrigger(trigger,triggerDetails);
            res.setMessage("修改联动规则成功");
            res.setCode(CommonConstant.RESPONSE_CODE_SUCCESS);
        }else {
            res.setMessage("修改联动规则失败，联动规则不存在");
            res.setCode(CommonConstant.RESPONSE_CODE_NOT_EXIST);
        }

        return res;
    }

    /**
     * 根据uuid查询联动规则信息
     *
     * @param req 要查询的联动规则
     * @return 返回联动规则的详细信息
     */
    @RequestMapping(value = "/getTrigger", method = RequestMethod.POST)
    @ApiOperation(value = "获取",notes = "获取")
    public ResponseDto queryTriggerById(@RequestBody RequestDto<TriggerDto> req) {
        ResponseDto res = new ResponseDto();
        TriggerDto epsTriggerDto = req.getData();
        String uuid = epsTriggerDto.getUuid();
        // 根据情况查询联动规则
        Trigger trigger = this.triggerServiceImpl.queryTriggerByPrimaryKey(uuid);
        epsTriggerDto = null;
        if (trigger != null){
            epsTriggerDto = new TriggerDto();
            epsTriggerDto.setEventSourceCode(trigger.getEventSourceCode());
            Integer eventType = trigger.getEventType();
            epsTriggerDto.setEventType(eventType);
            epsTriggerDto.setDeviceTypeName(EcStringUtils.qryDeviceTypeName(eventType.intValue()));
            epsTriggerDto.setIsSequential(trigger.getIsSequential());
            epsTriggerDto.setCreator(trigger.getCreator());
            epsTriggerDto.setUuid(trigger.getUuid());
            epsTriggerDto.setCourtUuid(trigger.getCourtUuid());
            epsTriggerDto.setCreateTime(trigger.getCreateTime());
            epsTriggerDto.setUpdateTime(trigger.getUpdateTime());
            epsTriggerDto.setCreateUser(trigger.getCreateUser());
            epsTriggerDto.setUpdateUser(trigger.getUpdateUser());
            //查询联动细则
            List<TriggerDetail> triggerDetailList = this.triggerDetailServiceImpl.queryTriggerDetailByTriggerId(uuid);
            List<TriggerDetailDto> epsTriggerDetailDtoList = new ArrayList<>();
            if(null != triggerDetailList && !triggerDetailList.isEmpty()) {
                triggerDetailList.forEach(epsTriggerDetail -> {
                    TriggerDetailDto dto = new TriggerDetailDto();
                    dto.setMethod(epsTriggerDetail.getTriggerType());
                    dto.setParams(epsTriggerDetail.getTriggerParams());
                    dto.setTo(epsTriggerDetail.getTo());
                    dto.setOrder(epsTriggerDetail.getOrder());
                    dto.setUuid(epsTriggerDetail.getUuid());
                    dto.setCourtUuid(epsTriggerDetail.getCourtUuid());
                    dto.setCreateTime(epsTriggerDetail.getCreateTime());
                    dto.setUpdateTime(epsTriggerDetail.getUpdateTime());
                    dto.setCreateUser(epsTriggerDetail.getCreateUser());
                    dto.setUpdateUser(epsTriggerDetail.getUpdateUser());
                    epsTriggerDetailDtoList.add(dto);
                });
                epsTriggerDto.setTriggers(epsTriggerDetailDtoList);
            }
        }
        res.setMessage("查询联动规则成功");
        res.setCode(CommonConstant.RESPONSE_CODE_SUCCESS);
        res.setData(epsTriggerDto);
        return res;
    }

    /**
     * 根据Ids批量删除联动规则信息
     *
     * @param req 需要删除的联动规则
     * @return 返回删除结果
     */
    @RequestMapping(value = "/deleteTrigger", method = RequestMethod.POST)
    @ApiOperation(value = "删除",notes = "删除")
    public ResponseDto deleteEventTrigger(@RequestBody RequestDto<TriggerDelDto> req) {
        ResponseDto res = new ResponseDto();
        TriggerDelDto epsTriggerDelDto = req.getData();
        List<String> triggerIds = epsTriggerDelDto.getTriggerIdList();
        this.triggerServiceImpl.deleteTriggerByPrimaryKey(triggerIds);
        res.setMessage("删除联动规则成功");
        res.setCode(CommonConstant.RESPONSE_CODE_SUCCESS);
        return res;
    }

    /**
     * 分页查询联动规则信息
     *
     * @param req 查询的联动规则的条件信息
     * @return 返回查询结果
     */
    @RequestMapping(value = "/listTriggers", method = RequestMethod.POST)
    @ApiOperation(value = "列表",notes = "列表")
    public ResponseDto queryTriggerPage(@RequestBody RequestDto<PageQueryDto> req){
        ResponseDto res = new ResponseDto();
        PageQueryDto pageQueryDto = req.getData();
        PageInfo<TriggerDto> triggerPage = this.triggerServiceImpl.queryTriggerPage(pageQueryDto);
        res.setData(triggerPage);
        res.setMessage("查询联动规则成功");
        res.setCode(CommonConstant.RESPONSE_CODE_SUCCESS);
        return res;

    }

}
